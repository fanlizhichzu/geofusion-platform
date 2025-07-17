package io.github.fanlizhichzu.common.enums;


/**
 * response msg code
 *
 * @author zsb
 * @version 20200416
 */
public enum EnumMsgCode {

    MSG_SUCCESS("0000", "success!"),
    MSG_1("1", "目标复制表名为空"),
    MSG_2("2", "复制到的表名为空"),
    MSG_3("3", "集合类型不一致"),
    MSG_4("4", "没有集合字段"),
    MSG_5("5", "复制条件为空"),
    MSG_6("6", "获取字段信息失败"),
    MSG_2001("2001", "传入参数异常，请检查参数"),
    MSG_2002("2002", "服务Id已存在"),
    MSG_2003("2003", "该工作区不是空的工作区，无法删除"),
    MSG_2004("2004", "工作区下已存在同名数据存储"),
    MSG_2005("2005", "该数据存储下已发布图层，无法删除"),
    MSG_2006("2006", "文件上传失败，请重试"),
    MSG_2007("2007", "获取属性失败，请重试"),
    MSG_2008("2008", "本地创建文件失败，请重试"),
    MSG_2009("2009", "sld和图片文件压缩失败，请重试"),
    MSG_2011("2011", "您没有访问权限"),
    MSG_2010("2010", "Login failed,The account or password is incorrect"),
    MSG_2012("2012", "图层名称和数据库表名不能相同!"),
    MSG_2013("2013", "name中不能包含中文、空格、+/?%#&=等字符"),
    MSG_2014("2014","sql 语句中字段名与自定义的column不符"),
    MSG_2015("2015","后台报空指针"),
    MSG_2016("2016","方法参数类型不匹配"),
    MSG_2017("2017","json序列化错误"),
    MSG_2018("2018", "参数解析失败"),
    MSG_2019("2019","用户信息过期"),
    MSG_2020("2020","逻辑目录已存在"),
    MSG_2021("2021","被叠加图层名称错误，图层名称必须带有模式信息"),
    SHINEGA_EXPORT_REPORT("S0010","导出报表失败"),
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
    SYSTEM_MYBATIS_PRIMARYKEY_NOT_EXISTS_ERROR("B0207","表没有设置主键"),
    SYSTEM_CONNECT_ERROR("B0300","连接超时"),
    SYSTEM_JSON_ERROR("B0400","json序列化错误"),
    SYSTEM_REQUEST_NOT_SUPPORT("B0500","请求方法不支持"),
    SYSTEM_REDIS_SETEXPIRE_ERROR("B7001", "[REDIS]设置过期时间失败"),
    SYSTEM_REDIS_HASKEY_ERROR("B7002", "不存在此key"),
    SYSTEM_REDIS_SETVALUE_ERROR("B7003", "插入值失败"),
    SYSTEM_REDIS_FACTORLESS0_ERROR("B7004", "递增因子必须大于0"),
    SYSTEM_ILLEGAL_ERROR("B0900", "系统不合法参数"),
    SYSTEM_ILLEGAL_ARGUMENT_ERROR("B0901", "方法传入一个非法的或者不合适的参数"),
    SYSTEM_FILE_NOTFOUND_ERROR("B1000", "文件不存在"),
    SYSTEM_FILE_READ_ERROR("B1001", "文件错误，无法读取"),
    MSG_SYS_ERROR("9999", "未知错误");
	
	

    private String code;

    private String description;

    EnumMsgCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isSuccess(EnumMsgCode code) {
        if (code == MSG_SUCCESS) {
            return true;
        }
        return false;
    }
}
