package io.github.fanlzhichzu.common.exception;

/**
 * @author WL
 * @Description: 登录异常信息
 * @date 2021/6/15 9:42
 */
public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
