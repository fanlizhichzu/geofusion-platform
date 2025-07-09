package io.github.fanlzhichzu.common.result;

/**
 * @Description:
 * @Auther: fanlz
 * @Date: 2021/12/7 16:26
 */
public enum ResultCode {
    /*
        成功状态码
     */
    SUCCESS("0000", "成功"),
    USER_ERROR("A0001", "用户端错误"),
    USER_PARAM_IS_INVALID("A0101", "参数无效"),
    USER_REGISTER_ERROR("A0100", "用户注册错误"),
    USER_REGISTER_PRIVACY_ERROR("A0101", "用户未同意隐私协议"),
    USER_REGISTER_REGION_ERROR("A0102", "注册国家或地区受限"),
    USER_NAME_CHECK_ERROR("A0110", "用户校验失败"),
    USER_NAME_IS_EXISTS("A0111", "用户名已存在"),
    USER_NAME_CONTAIN_SENSITIVE_GENERATION("A0112", "用户名包含敏感词"),
    USER_NAME_CONTAIN_SPECIAL_BYTES("A0112", "用户名包含特殊字符"),
    USER_PASSWORD_CHECK_ERROR("A0120", "密码校验失败"),
    USER_PASSWORD_LENGTH_ERROR("A0121", "密码长度不够"),
    USER_PASSWORD_STRENGTH_ERROR("A0122", "密码强度不够"),
    USER_CHECKSUM_ERROR("A0130", "校验码输入错误"),
    USER_CHECKSUM_SMS_ERROR("A0131", "短信校验码输入错误"),
    USER_CHECKSUM_EMAIL_ERROR("A0132", "邮件校验码输入错误"),
    USER_CHECKSUM_VOICE_ERROR("A0133", "语音校验码输入错误"),
    USER_CERTIFICATES_ERROR("A0140", "用户证件异常"),
    USER_CERTIFICATES_TYPE_IS_NOT_SELECTED("A0141", "用户证件类型未选择"),
    USER_IDCARD_CODE_CHECKED_ERROR("A0142", "大陆身份证编号校验非法"),
    USER_PASSPORT_CODE_CHECKED_ERROR("A0143", "护照编号校验非法"),
    USER_OFFICER_CODE_CHECKED_ERROR("A0143", "护照编号校验非法"),
    USER_INFO_CHECKED_ERROR("A0150", "用户基本信息校验失败"),
    USER_INFO_MOBILE_CHECKED_ERROR("A0151", "手机格式校验失败"),
    USER_INFO_ADDRESS_CHECKED_ERROR("A0152", "地址格式校验失败"),
    USER_INFO_EMAIL_CHECKED_ERROR("A0153", "邮箱格式校验失败"),
    SYSTEM_ERROR("B0001", "系统执行出错"),
    SYSTEM_NEP_ERROR("B0002","空指针异常"),
    SYSTEM_SQL_ERROR("B0100","psqlException异常"),
    SYSTEM_MYBATIS_ERROR("B0200","数据库异常"),
    SYSTEM_MYBATIS_BAD_SQL_GRAMMAR_ERROR("B0201","mybatis参数绑定失败"),
    SYSTEM_MYBATIS_BIND_ERROR("B0202","xml和mapper文件绑定异常"),
    SYSTEM_MYBATIS_TABLE_NOT_EXISTS_ERROR("B0203","数据表不存在"),
    SYSTEM_MYBATIS_DUPLICATE_KEY_ERROR("B0204","重复键违反唯一约束"),
    SYSTEM_MYBATIS_BATCH_UPDATE_ERROR("B0205","批量更新错误"),
    SYSTEM_MYBATIS_TOO_MANY_RESULT_ERROR("B0206","数据库存在多条数据"),
    SYSTEM_CONNECT_ERROR("B0300","连接超时"),
    SYSTEM_JSON_ERROR("B0400","json序列化错误"),
    SYSTEM_REQUEST_NOT_SUPPORT("B0500","请求方法不支持"),
    SYSTEM_REDIS_SETEXPIRE_ERROR("B7001", "[REDIS]设置过期时间失败"),
    SYSTEM_REDIS_HASKEY_ERROR("B7002", "不存在此key"),
    SYSTEM_REDIS_SETVALUE_ERROR("B7003", "插入值失败"),
    SYSTEM_REDIS_FACTORLESS0_ERROR("B7004", "递增因子必须大于0"),
    SYSTEM_ILLEGAL_ERROR("B0900", "系统不合法参数"),
    SYSTEM_ILLEGAL_ARGUMENT_ERROR("B0901", "方法传入一个非法的或者不合适的参数"),

    MSG_SYS_ERROR("9999", "system unknown error");
    ;
    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
