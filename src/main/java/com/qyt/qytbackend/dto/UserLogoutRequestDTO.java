package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户登出请求DTO
 */
@Data
public class UserLogoutRequestDTO {
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;
}
