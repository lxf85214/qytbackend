package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class Resource extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父id
     */
    private Integer pid;
    /**
     * MENU,ELEM
     */
    private String type;
    /**
     * 菜单地址
     */
    private String url;
    /**
     * 层级，从0开始
     */
    private Integer level;
    /**
     * 序号
     */
    private Integer orderNum;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 状态（0-停用，1-启用）
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