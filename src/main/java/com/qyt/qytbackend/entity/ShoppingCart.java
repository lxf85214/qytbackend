package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class ShoppingCart extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID：关联客户信息表
     */
    private Integer userId;
    /**
     * 商品ID：关联商品信息表
     */
    private Integer productId;
    /**
     * 商品SKU：如有规格
     */
    private String productSku;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 是否选中：0-未选中，1-选中
     */
    private Integer selected;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}