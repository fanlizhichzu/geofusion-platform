package io.github.fanlizhichzu.common.exception;

import lombok.Data;
import io.github.fanlizhichzu.common.result.ResultCode;

/**
 * @Description:
 * @Auther: fanlz
 * @Date: 2021/12/8 15:25
 */
@Data
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    public BizException() {
        super();
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BizException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BizException(String message) {
        super(message);
        this.code = "B0001";
        this.message = message;
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
