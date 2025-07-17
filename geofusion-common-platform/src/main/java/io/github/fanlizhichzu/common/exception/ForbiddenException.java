package io.github.fanlizhichzu.common.exception;

/**
 * 403
 *
 * @author fanlz
 * @date 2022/11/29 11:14
 **/
public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message) {
        super(message);
    }
}
