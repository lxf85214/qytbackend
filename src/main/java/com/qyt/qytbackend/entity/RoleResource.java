package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class RoleResource extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private Integer resourceId;
    private String createPin;
    private String updatePin;
}