package com.qyt.qytbackend.rpc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付响应DTO
 *
 * @author qyt
 * @date 2024
 */
@Data
public class WeChatPayResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 预支付交易会话ID
     */
    private String prepayId;

    /**
     * 支付二维码URL
     */
    private String codeUrl;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;
}
