package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 状态（0-下架，1-上架）
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