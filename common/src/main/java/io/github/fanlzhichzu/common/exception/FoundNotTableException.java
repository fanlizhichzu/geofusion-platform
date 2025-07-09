package io.github.fanlzhichzu.common.exception;

/**
 * @author WL
 * @Description: 待操作的表不存在
 * @date 2021/6/17 10:44
 */
public class FoundNotTableException extends RuntimeException {
    public FoundNotTableException(String message) {
        super(message);
    }
}
