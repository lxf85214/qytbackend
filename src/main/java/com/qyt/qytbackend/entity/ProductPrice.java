package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductPrice extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品原价：商品的原始定价，不包含任何折扣
     */
    private BigDecimal originalPrice;
    /**
     * 商品售价：商品当前的销售价格，可能包含日常折扣
     */
    private BigDecimal sellingPrice;
    /**
     * 集采价格：商品的集中采购价格，适用于批量采购场景
     */
    private BigDecimal bulkPurchasePrice;
    /**
     * 商品ID：关联的商品唯一标识
     */
    private Long productId;
    /**
     * 生效时间：价格生效的起始时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 失效时间：价格失效的结束时间，为空表示该价格长期有效
     */
    private LocalDateTime expiryTime;
    /**
     * 价格类型：区分价格的类型，例如：日常售价、促销价、会员价等
     */
    private String priceType;
    /**
     * 备注：对价格信息的补充说明
     */
    private String remarks;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}