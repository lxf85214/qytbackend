package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 发送短信请求
 */
@Setter
@Getter
@Schema(name = "SendSmsRequest", description = "发送短信请求")
public class SendSmsRequest {
    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;
}