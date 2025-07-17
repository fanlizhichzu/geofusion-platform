package io.github.fanlizhichzu.cache.exception;

// 加载中断异常
public class CacheLoadingInterruptedException extends CacheAccessException {
    private final Object cacheKey;
    
    public CacheLoadingInterruptedException(String message, Object cacheKey) {
        super(message + ", key: " + cacheKey);
        this.cacheKey = cacheKey;
    }
    
    public Object getCacheKey() {
        return cacheKey;
    }
}
