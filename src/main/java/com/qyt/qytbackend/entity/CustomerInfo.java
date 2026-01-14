package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerInfo extends BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户唯一标识
     */
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码（加密存储）
     */
    private String password;
    
    /**
     * 手机号码
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
     * 身份证号
     */
    private String idCard;
    
    /**
     * 头像URL
     */
    private String avatar;
    
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
     * 账号状态（0-禁用，1-正常）
     */
    private Integer status;
    
    /**
     * 会员等级（0-普通，1-白银，2-黄金等）
     */
    private Integer memberLevel;
    
    /**
     * 创建人
     */
    private String createPin;
    
    /**
     * 更新人
     */
    private String updatePin;
}