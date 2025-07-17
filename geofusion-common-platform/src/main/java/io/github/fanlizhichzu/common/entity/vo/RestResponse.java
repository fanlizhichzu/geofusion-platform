package io.github.fanlizhichzu.common.entity.vo;


import io.github.fanlizhichzu.common.enums.EnumMsgCode;
import lombok.Data;

@Data
public class RestResponse<T> {

    private boolean isSuccess;

    private String code;

    private String description;

    private T result;

    private T extendResult;

    public RestResponse() {
    }

    public void setResultInfo(EnumMsgCode code) {
        if (code == null) {
            this.code = EnumMsgCode.MSG_SYS_ERROR.getCode();
            this.description = EnumMsgCode.MSG_SYS_ERROR.getDescription();
        } else {
            this.code = code.getCode();
            this.description = code.getDescription();
        }
    }

    public RestResponse(String code, String description, T result, T extendResult) {
        this.code = code;
        this.description = description;
        this.result = result;
        this.extendResult = extendResult;
        if (code == EnumMsgCode.MSG_SUCCESS.getCode()) {
            this.isSuccess = true;
        } else {
            this.isSuccess = false;
        }
    }

    public RestResponse(String code, String description, T result) {
        this.code = code;
        this.description = description;
        this.result = result;
        if (code == EnumMsgCode.MSG_SUCCESS.getCode()) {
            this.isSuccess = true;
        } else {
            this.isSuccess = false;
        }
    }

    public RestResponse(String code, String description) {
        this.code = code;
        this.description = description;
        if (code == EnumMsgCode.MSG_SUCCESS.getCode()) {
            this.isSuccess = true;
        } else {
            this.isSuccess = false;
        }
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse<T>(EnumMsgCode.MSG_SUCCESS.getCode(), "成功");
    }

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<T>(EnumMsgCode.MSG_SUCCESS.getCode(), "成功", data, null);
    }

    public static <T> RestResponse<T> success(T data, T extentData) {
        return new RestResponse<T>(EnumMsgCode.MSG_SUCCESS.getCode(), "成功", data, extentData);
    }

    public static <T> RestResponse<T> success(String message, T data) {
        return new RestResponse<T>(EnumMsgCode.MSG_SUCCESS.getCode(), message, data, null);
    }

    public static <T> RestResponse<T> error(String errorCode, String errorMessage) {
        return new RestResponse<T>(errorCode, errorMessage);
    }

    public static <T> RestResponse<T> error(EnumMsgCode resultEnum) {
        return new RestResponse<T>(resultEnum.getCode(), resultEnum.getDescription());
    }
}
