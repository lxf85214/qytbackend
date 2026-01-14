package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserLoginLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private String loginIp;
    private String loginDevice;
    private Integer loginStatus;
    private String logoutType;
    private String createPin;
    private String updatePin;
}