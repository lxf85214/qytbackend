package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String productName;
    /**
     * 所在区
     */
    private String district;
    /**
     * 商品一级分类ID（关联分类表）
     */
    private Integer categoryFirstId;
    /**
     * 商品二级分类ID（关联分类表）
     */
    private Integer categorySecondId;
    /**
     * 商品三级分类ID（关联分类表）
     */
    private Integer categoryThirdId;
    /**
     * 品牌ID（关联品牌表）
     */
    private Integer brandId;
    /**
     * 主图URL
     */
    private String mainImage;
    /**
     * 预览图URL
     */
    private String thumbnailImage;
    /**
     * 列表图URL
     */
    private String listingImage;
    /**
     * 详情图URL（多个用逗号分隔）
     */
    private String detailImages;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品规格（JSON格式存储）
     */
    private String specifications;
    /**
     * 状态：0-下架，1-上架
     */
    private Integer status;
    /**
     * 创建人
     */
    private String createPin;
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
     * 可用库存数量：可被销售的实际可用库存
     */
    private Integer availableQuantity;

    /**
     * 累计销量
     */
    private Integer sales;

    /**
     * 更新人
     */
    private String updatePin;
}