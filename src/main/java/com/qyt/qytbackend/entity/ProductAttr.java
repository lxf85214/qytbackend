package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductAttr extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品id：关联的商品唯一标识符
     */
    private Long productId;
    /**
     * 属性名称：具体的属性值
     */
    private String attrName;
    /**
     * 属性值
     */
    private String attrValue;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}