package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 登录响应
 */
@Setter
@Getter
@Schema(name = "LoginResponse", description = "登录响应")
public class LoginResponse {
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "登录状态")
    private boolean loginStatus;
    @Schema(description = "JWT token")
    private String token;

}