package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductBrand extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 品牌logo URL
     */
    private String brandLogo;
    /**
     * 品牌描述
     */
    private String brandDesc;
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