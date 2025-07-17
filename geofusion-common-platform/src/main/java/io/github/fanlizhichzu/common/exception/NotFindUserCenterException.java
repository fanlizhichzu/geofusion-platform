package io.github.fanlizhichzu.common.exception;

/**
 * @author WL
 * @Description: 电子政务中心地址未配置
 * @date 2021/6/10 15:07
 */
public class NotFindUserCenterException extends RuntimeException {
    public NotFindUserCenterException(String message) {
        super(message);
    }
}
