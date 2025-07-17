package io.github.fanlizhichzu.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
    private final RedissonClient redissonClient;

    @Bean(name = "caffeineCacheManager")
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager();
        manager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats());
        return manager;
    }

    @Bean
    @Primary
    @DependsOn("caffeineCacheManager")
    public CacheManager cacheManager(CaffeineCacheManager caffeineCacheManager) {
        return new CacheManager() {
            private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

            @Override
            public Cache getCache(String name) {
                return cacheMap.computeIfAbsent(name, cacheName ->
                        new EnhancedMultiLevelCache(
                                cacheName,
                                caffeineCacheManager.getCache(cacheName),
                                redissonClient.getMapCache(cacheName),
                                redissonClient,
                                new CacheUpdatePublisher(redissonClient)
                        )
                );
            }

            @Override
            public Collection<String> getCacheNames() {
                return Collections.unmodifiableSet(cacheMap.keySet());
            }
        };
    }

    @Bean
    public CacheUpdateSubscriber cacheUpdateSubscriber(CacheManager cacheManager) {
        return new CacheUpdateSubscriber(cacheManager, redissonClient);
    }
}