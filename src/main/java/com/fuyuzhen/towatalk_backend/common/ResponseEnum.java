package com.fuyuzhen.towatalk_backend.common;

/**
 * 系统响应码枚举
 * 规则：
 * 20xxx - 成功相关
 * 30xxx - 失败相关
 * 40xxx - 客户端错误
 * 50xxx - 服务端错误
 */
public enum ResponseEnum {
    // 成功响应码 (20xxx)
    LOGIN_SUCCESS(20000, "登录成功"),
    REGISTER_SUCCESS(20001, "注册成功"),
    UPDATE_SUCCESS(20002, "修改成功"),
    GET_SUCCESS(20003, "数据获取成功"),
    ADD_SUCCESS(20004, "添加成功"),
    DELETE_SUCCESS(20005, "删除成功"),

    // 失败响应码 (30xxx)
    LOGIN_FAILED(30001, "登录失败，账号或密码错误"),
    REGISTER_FAILED(30002, "注册失败"),
    UPDATE_FAILED(30003, "修改失败"),
    DELETE_FAILED(30004, "删除失败"),
    USERNAME_EXISTS(30005, "用户名已存在"),
    CLASS_EXISTS(30006, "班级已存在"),

    // 客户端错误 (40xxx)
    INVALID_PARAM(40001, "参数非法"),
    INVALID_REQUEST(40002, "无效的请求"),
    UNAUTHORIZED(40003, "未授权的访问"),
    FORBIDDEN(40004, "禁止访问"),

    // 服务端错误 (50xxx)
    INNER_ERROR(50001, "服务器内部错误"),
    ACCOUNT_NOT_FOUND(50002, "账号不存在，请先注册"),
    ACCOUNT_NOT_LOGIN(50003, "账号未登录"),
    SERVICE_UNAVAILABLE(50004, "服务不可用"),
    DATABASE_ERROR(50005, "数据库操作错误"),
    ;

    public final Integer code;
    public final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
