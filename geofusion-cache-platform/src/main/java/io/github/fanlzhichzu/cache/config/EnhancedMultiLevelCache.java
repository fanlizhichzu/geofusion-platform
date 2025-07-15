package io.github.fanlzhichzu.cache.config;

import io.github.fanlzhichzu.cache.exception.CacheAccessException;
import io.github.fanlzhichzu.cache.exception.CacheLoadingException;
import io.github.fanlzhichzu.cache.exception.CacheLoadingInterruptedException;
import io.github.fanlzhichzu.cache.exception.CacheLockAcquisitionException;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
@Slf4j
public class EnhancedMultiLevelCache extends AbstractValueAdaptingCache {
    private final String name;
    private final Cache localCache;
    private final RMapCache<Object, Object> redisCache;
    private final RedissonClient redissonClient;
    private final CacheUpdatePublisher cacheUpdatePublisher;

    // 空值标记对象
    private static final Object NULL_VALUE = new NullValue();

    public EnhancedMultiLevelCache(String name, 
                                 Cache localCache,
                                 RMapCache<Object, Object> redisCache,
                                 RedissonClient redissonClient,
                                 CacheUpdatePublisher cacheUpdatePublisher) {
        super(true);
        this.name = name;
        this.localCache = localCache;
        this.redisCache = redisCache;
        this.redissonClient = redissonClient;
        this.cacheUpdatePublisher = cacheUpdatePublisher;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Object getNativeCache() {
        return this;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(Object key, Callable<T> valueLoader) {
        
        // 2. 查本地缓存
        ValueWrapper localValue = localCache.get(key);
        if (localValue != null) {
            return (T) fromStoreValue(localValue.get());
        }
        
        // 3. 查Redis缓存
        Object redisValue = redisCache.get(key);
        if (redisValue != null) {
            Object value = fromStoreValue(redisValue);
            localCache.put(key, value);
            return (T) value;
        }
        
        // 4. 加锁加载数据
        return loadWithLock(key, valueLoader);
    }

    private <T> T loadWithLock(Object key, Callable<T> valueLoader) {
        RLock lock = redissonClient.getLock("cache:lock:" + name + ":" + key);
        boolean locked;

        try {
            // 尝试获取锁，等待100ms，锁超时30秒
            locked = lock.tryLock(10, 3000, TimeUnit.MILLISECONDS);

            if (locked) {
                try {
                    // 双重检查
                    ValueWrapper localValue = localCache.get(key);
                    if (localValue != null) {
                        return (T) fromStoreValue(localValue.get());
                    }

                    Object redisValue = redisCache.get(key);
                    if (redisValue != null) {
                        Object value = fromStoreValue(redisValue);
                        localCache.put(key, value);
                        return (T) value;
                    }

                    // 真正加载数据
                    T value = valueLoader.call();
                    put(key, value);
                    return value;
                } finally {
                    lock.unlock();
                }
            } else {
                // 获取锁失败时的降级策略
                return handleLockFailure(key, valueLoader);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // 中断情况下的降级处理
            return handleInterrupted(key, valueLoader);
        } catch (RuntimeException e) {
            // 明确捕获RuntimeException，避免捕获非受检异常
            throw new CacheAccessException("Failed to load cache value for key: " + key, e);
        } catch (Exception e) {
            // 处理受检异常
            return handleLoaderException(key, valueLoader, e);
        }
    }

    // 获取锁失败时的降级策略
    private <T> T handleLockFailure(Object key, Callable<T> valueLoader) {
        // 方案1：返回旧值（如果有）
        ValueWrapper wrapper = localCache.get(key);
        if (wrapper != null) {
            return (T) wrapper.get();
        }

        // 方案2：返回预定义的默认值
        // return getDefaultValue();

        // 方案3：抛出特定的降级异常
        throw new CacheLockAcquisitionException("Unable to acquire lock for cache loading, key: ", key);
    }

    // 中断异常处理
    private <T> T handleInterrupted(Object key, Callable<T> valueLoader) {
        // 记录中断日志
        log.warn("Cache loading interrupted for key: {}", key);

        // 方案1：尝试快速失败
        throw new CacheLoadingInterruptedException("Cache loading interrupted", key);

        // 方案2：返回保守值
        // return getConservativeValue(key);
    }

    // 加载器异常处理
    private <T> T handleLoaderException(Object key, Callable<T> valueLoader, Exception e) {

        // 记录加载失败
        log.error("Failed to load cache value for key: {}", key, e);

        // 方案1：抛出包装异常
        throw new CacheLoadingException("Failed to load value for key: ", key, e);

        // 方案2：返回降级值
        // return getFallbackValue(key);
    }
    
    @Override
    public void put(Object key, @Nullable Object value) {
        if (value != null) {

            Object storeValue = toStoreValue(value);
            localCache.put(key, storeValue);
            
            // Redis缓存设置随机TTL (30-60分钟)
            int ttl = 1800 + new Random().nextInt(1800);
            redisCache.fastPut(key, storeValue, ttl, TimeUnit.SECONDS);
        } else {
            // 缓存空值，设置较短TTL (5-10分钟)
            localCache.put(key, NULL_VALUE);
            int ttl = 300 + new Random().nextInt(300);
            redisCache.fastPut(key, NULL_VALUE, ttl, TimeUnit.SECONDS);
        }
        
        // 发布更新通知
        // cacheUpdatePublisher.publishEvict(name, key);
    }
    
    @Override
    public void evict(Object key) {
        localCache.evict(key);
        redisCache.fastRemove(key);
        cacheUpdatePublisher.publishEvict(name, key);
    }
    
    @Override
    public void clear() {
        localCache.clear();
        redisCache.clear();
        cacheUpdatePublisher.publishClear(name);
    }

    @Override
    protected Object lookup(Object key) {

        // 1. 查本地缓存
        ValueWrapper localValue = localCache.get(key);
        if (localValue != null) {
            return fromStoreValue(localValue.get());
        }

        // 2. 查Redis缓存
        try {
            Object redisValue = redisCache.get(key);
            if (redisValue != null) {
                Object value = fromStoreValue(redisValue);
                // 异步回填本地缓存
                CompletableFuture.runAsync(() -> localCache.put(key, value));
                return value;
            }
        } catch (Exception e) {
            log.error("Redis access error for key: {}", key, e);
        }

        return null;
    }
    
    private static class NullValue implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
    }
}