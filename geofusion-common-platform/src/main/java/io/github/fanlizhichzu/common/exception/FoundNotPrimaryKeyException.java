package io.github.fanlizhichzu.common.exception;

/**
 * @author WL
 * @Description: 待操作的表缺少主键
 * @date 2021/6/17 10:44
 */
public class FoundNotPrimaryKeyException extends RuntimeException {
    public FoundNotPrimaryKeyException(String message) {
        super(message);
    }
}
