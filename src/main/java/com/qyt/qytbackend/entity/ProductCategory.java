package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductCategory extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 父分类ID：0-顶级分类
     */
    private Integer parentId;
    /**
     * 分类层级：1-一级，2-二级等
     */
    private Integer level;
    /**
     * 排序优先级：数字越小越靠前
     */
    private Integer sortOrder;
    /**
     * 状态：0-禁用，1-启用
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