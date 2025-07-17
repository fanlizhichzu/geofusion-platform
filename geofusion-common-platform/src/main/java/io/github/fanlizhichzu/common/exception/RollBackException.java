package io.github.fanlizhichzu.common.exception;

/**
 * @author WL
 * @Description: 自定义回滚
 * @date 2021/6/1 9:20
 */
public class RollBackException extends RuntimeException {
    public RollBackException(String message) {
        super(message);
    }
}
