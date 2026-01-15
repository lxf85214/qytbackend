package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户新增请求DTO
 */
@Data
public class UserAddRequestDTO {
    /**
     * 用户名
     */
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    /**
     * 密码（前端加密后传输）
     */
    @Schema(description = "密码（前端加密后传输）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    /**
     * 手机号码
     */
    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;
    /**
     * 电子邮箱
     */
    @Schema(description = "电子邮箱")
    private String email;
    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;
    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idCard;
    /**
     * 头像URL
     */
    @Schema(description = "头像URL")
    private String avatar;
    /**
     * 用户类型：0-全域通平台;1-供应商
     */
    @Schema(description = "用户类型：0-全域通平台;1-供应商", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userType;
    /**
     * 账号状态：0-禁用，1-正常
     */
    @Schema(description = "账号状态：0-禁用，1-正常", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createPin;
}
