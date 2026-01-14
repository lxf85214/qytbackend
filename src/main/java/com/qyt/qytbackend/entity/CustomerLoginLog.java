package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户登录日志表实体类
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerLoginLog extends BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志ID，自增主键
     */
    private Long id;
    
    /**
     * 用户ID，关联用户表
     */
    private Long userId;
    
    /**
     * 登录时间，格式：YYYY-MM-DD HH:MM:SS
     */
    private LocalDateTime loginTime;
    
    /**
     * 登出时间，未登出时可为NULL
     */
    private LocalDateTime logoutTime;
    
    /**
     * 登录IP地址
     */
    private String loginIp;
    
    /**
     * 登录设备信息
     */
    private String loginDevice;
    
    /**
     * 登录状态：0-登出，1-登录成功
     */
    private Integer loginStatus;
    
    /**
     * 登出类型：主动登出、超时登出、异常退出等
     */
    private String logoutType;
    
    /**
     * 创建人
     */
    private String createPin;
    
    /**
     * 更新人
     */
    private String updatePin;
}