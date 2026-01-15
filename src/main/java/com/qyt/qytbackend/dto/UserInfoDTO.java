package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户信息DTO
 */
@Data
@Schema(name = "UserInfoDTO", description = "用户信息参数")
public class UserInfoDTO {
    @Schema(description = "登录用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "手机号")
    private String phoneNumber;
    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "用户类型")
    private Integer userType;
}
