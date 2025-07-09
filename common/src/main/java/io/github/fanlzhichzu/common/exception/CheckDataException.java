package io.github.fanlzhichzu.common.exception;

/**
 * @author WL
 * @Description: 校验数据异常
 * @date 2021/6/23 10:01
 */
public class CheckDataException extends RuntimeException {
    public CheckDataException(String message) {
        super(message);
    }
}
