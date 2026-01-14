package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String phone;
    private String email;
    private String realName;
    private String idCard;
    private String avatar;
    private Integer userType;
    private LocalDateTime registerTime;
    private Integer loginStatus;
    private LocalDateTime lastLoginTime;
    private Integer status;
    private String createPin;
    private String updatePin;
}