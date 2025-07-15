package io.github.fanlzhichzu.cache.exception;

import io.github.fanlzhichzu.cache.exception.CacheAccessException;

// 锁获取失败异常
public class CacheLockAcquisitionException extends CacheAccessException {
    private final Object cacheKey;
    
    public CacheLockAcquisitionException(String message, Object cacheKey) {
        super(message + ", key: " + cacheKey);
        this.cacheKey = cacheKey;
    }
    
    public Object getCacheKey() {
        return cacheKey;
    }
}
