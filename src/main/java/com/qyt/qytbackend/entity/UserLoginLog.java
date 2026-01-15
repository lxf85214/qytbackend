package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserLoginLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID：关联用户表的用户唯一标识
     */
    private Long userId;
    /**
     * 登录时间：记录用户成功登录系统的具体日期和时间
     */
    private LocalDateTime loginTime;
    /**
     * 登出时间：记录用户主动登出或系统判定其登出的具体日期和时间
     */
    private LocalDateTime logoutTime;
    /**
     * 登录IP地址：记录用户登录时使用的网络IP地址
     */
    private String loginIp;
    /**
     * 登录设备信息：记录用户登录时使用的设备类型及相关信息
     */
    private String loginDevice;
    /**
     * 登录状态：0表示登出，1表示登录成功
     */
    private Integer loginStatus;
    /**
     * 登出类型：可选项如"主动登出"、"超时登出"、"异常退出"等
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