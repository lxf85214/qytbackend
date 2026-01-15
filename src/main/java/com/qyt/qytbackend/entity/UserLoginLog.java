package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户登录日志实体类
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserLoginLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录时间
     */
    private LocalDateTime loginTime;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录状态：0-失败；1-成功
     */
    private Integer loginStatus;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 登录设备
     */
    private String device;
    /**
     * 浏览器
     */
    private String browser;
}
