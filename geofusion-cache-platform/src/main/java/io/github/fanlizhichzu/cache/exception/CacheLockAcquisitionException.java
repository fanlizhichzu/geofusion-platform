package io.github.fanlizhichzu.cache.exception;

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
