package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 登录请求
 */
@Setter
@Getter
@Schema(name = "LoginRequest", description = "登录请求")
public class LoginRequest {
    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;
    @Schema(description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

}