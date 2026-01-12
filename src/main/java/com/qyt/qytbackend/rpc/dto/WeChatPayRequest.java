package com.qyt.qytbackend.rpc.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信支付请求DTO
 *
 * @author qyt
 * @date 2024
 */
@Data
public class WeChatPayRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付金额（单位：分）
     */
    private BigDecimal amount;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 用户openid
     */
    private String openid;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 通知回调地址
     */
    private String notifyUrl;
}
