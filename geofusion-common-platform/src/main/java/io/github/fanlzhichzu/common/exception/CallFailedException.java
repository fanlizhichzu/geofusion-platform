package io.github.fanlzhichzu.common.exception;

/**
 * @author WL
 * @Description: 远程调用获取数据失败
 * @date 2021/6/17 10:44
 */
public class CallFailedException extends RuntimeException {
    public CallFailedException(String message) {
        super(message);
    }
}
