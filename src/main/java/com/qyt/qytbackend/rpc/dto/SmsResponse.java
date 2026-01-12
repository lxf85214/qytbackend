package com.qyt.qytbackend.rpc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 短信发送响应DTO
 *
 * @author qyt
 * @date 2024
 */
@Data
public class SmsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 消息ID（用于查询发送状态）
     */
    private String messageId;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;
}
