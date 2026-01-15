package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 登出请求
 */
@Setter
@Getter
@Schema(name = "LogoutRequest", description = "登出请求")
public class LogoutRequest {
    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

}