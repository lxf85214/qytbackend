package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZoneConfig extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 配置项类型：0-类目，1-品牌
     */
    private Integer itemType;
    /**
     * 配置项名称
     */
    private String itemName;
    /**
     * 专区名称
     */
    private String zoneName;
    /**
     * 是否展示：0-否，1-是
     */
    private Integer isDisplay;
    /**
     * 默认图片
     */
    private String defaultImage;
    /**
     * 专区描述
     */
    private String description;
    /**
     * 展示位置：0-第一行，1-其他
     */
    private Integer displayPosition;
    /**
     * 是否同步展示商品：0-否，1-是
     */
    private Integer displayProduct;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}