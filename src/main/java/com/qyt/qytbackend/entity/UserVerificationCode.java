package com.qyt.qytbackend.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户验证码记录表实体类
 */
@Data
public class UserVerificationCode extends BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 手机号
     */
    private String phoneNumber;
    
    /**
     * 验证码
     */
    private String verificationCode;
    
    /**
     * 创建人
     */
    private String createBy;
    
    /**
     * 更新人
     */
    private String updateBy;
    
    /**
     * 逻辑删除标记（0-未删除，1-已删除）
     */
    private Integer isDelete;
}