package com.qyt.qytbackend.rpc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 短信发送请求DTO
 *
 * @author qyt
 * @date 2024
 */
@Data
public class SmsRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 短信模板ID
     */
    private String templateId;

    /**
     * 短信内容（如果使用模板，则为模板参数）
     */
    private String content;

    /**
     * 模板参数（JSON格式）
     */
    private String templateParams;
}
