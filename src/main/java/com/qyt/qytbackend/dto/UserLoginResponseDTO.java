package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.qyt.qytbackend.entity.UserInfo;

/**
 * 用户登录响应DTO
 */
@Data
public class UserLoginResponseDTO {
    /**
     * 令牌
     */
    @Schema(description = "令牌")
    private String token;
    /**
     * 用户信息
     */
    @Schema(description = "用户信息")
    private UserInfo userInfo;
}
