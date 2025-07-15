package io.github.fanlzhichzu.cache.exception;

import io.github.fanlzhichzu.cache.exception.CacheAccessException;

// 加载过程异常
public class CacheLoadingException extends CacheAccessException {
    private final Object cacheKey;
    
    public CacheLoadingException(String message, Object cacheKey, Throwable cause) {
        super(message + ", key: " + cacheKey, cause);
        this.cacheKey = cacheKey;
    }
    
    public Object getCacheKey() {
        return cacheKey;
    }
}
