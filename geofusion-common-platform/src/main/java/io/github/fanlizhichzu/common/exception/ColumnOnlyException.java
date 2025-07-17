package io.github.fanlizhichzu.common.exception;

/**
 * @author WL
 * @Description: 字段唯一性校验异常
 * @date 2021/7/15 13:52
 */
public class ColumnOnlyException extends RuntimeException {
    public ColumnOnlyException(String message) {
        super(message);
    }
}
