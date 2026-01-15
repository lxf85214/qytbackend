package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class RoleResource extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer resourceId;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}