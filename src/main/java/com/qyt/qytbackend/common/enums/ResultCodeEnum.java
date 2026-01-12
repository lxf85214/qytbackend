package com.qyt.qytbackend.common.enums;

import lombok.Getter;

/**
 * 响应状态码枚举
 *
 * @author qyt
 * @date 2024
 */
@Getter
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 参数错误
     */
    BAD_REQUEST(400, "请求参数错误"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),

    /**
     * 无权限
     */
    FORBIDDEN(403, "无权限"),

    /**
     * 资源不存在
     */
    NOT_FOUND(404, "资源不存在"),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    /**
     * 业务异常
     */
    BUSINESS_ERROR(5001, "业务处理失败"),

    /**
     * 数据不存在
     */
    DATA_NOT_FOUND(5002, "数据不存在"),

    /**
     * 数据已存在
     */
    DATA_ALREADY_EXISTS(5003, "数据已存在");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
