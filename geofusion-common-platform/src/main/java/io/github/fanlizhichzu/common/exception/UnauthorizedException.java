package io.github.fanlizhichzu.common.exception;

/**
 * 401
 *
 * @author fanlz
 * @date 2022/11/29 11:12
 **/
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
