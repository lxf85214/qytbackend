package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单号：关联订单信息表
     */
    private Long orderId;
    /**
     * 商品ID：关联商品信息表
     */
    private Long productId;
    /**
     * 商品名称：下单时快照
     */
    private String productName;
    /**
     * 商品图片：下单时快照
     */
    private String productImage;
    /**
     * 商品SKU：如有规格
     */
    private String productSku;
    /**
     * 购买单价
     */
    private BigDecimal price;
    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 折扣
     */
    private BigDecimal discountRate;
    /**
     * 小计金额：price*quantity
     */
    private BigDecimal subtotal;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}