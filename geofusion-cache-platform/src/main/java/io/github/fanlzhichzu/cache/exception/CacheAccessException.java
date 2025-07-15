package io.github.fanlzhichzu.cache.exception;

// 缓存访问基础异常
public class CacheAccessException extends RuntimeException {
    public CacheAccessException(String message) {
        super(message);
    }
    public CacheAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

