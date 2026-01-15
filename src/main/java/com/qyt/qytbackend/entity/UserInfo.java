package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名：唯一
     */
    private String username;
    /**
     * 密码（加密存储）
     */
    private String password;
    /**
     * 手机号码：唯一
     */
    private String phone;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号：唯一
     */
    private String idCard;
    /**
     * 头像URL
     */
    private String avatar;
    /**
     * 用户类型：0-全域通平台;1-供应商
     */
    private Integer userType;
    /**
     * 注册时间
     */
    private LocalDateTime registerTime;
    /**
     * 登录状态：0-已登出；1-已登录
     */
    private Integer loginStatus;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 账号状态：0-禁用，1-正常
     */
    private Integer status;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}