package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer roleId;
    private String createPin;
    private String updatePin;
}