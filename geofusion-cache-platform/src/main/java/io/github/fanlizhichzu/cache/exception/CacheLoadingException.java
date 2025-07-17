package io.github.fanlizhichzu.cache.exception;

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
