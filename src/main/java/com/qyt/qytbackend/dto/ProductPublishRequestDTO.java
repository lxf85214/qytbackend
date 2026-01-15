package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品发布请求DTO
 */
@Data
public class ProductPublishRequestDTO {
    /**
     * 商品名称
     */
    @Schema(description = "商品名称", example = "iPhone 15 Pro")
    private String productName;

    /**
     * 所在区
     */
    @Schema(description = "所在区", example = "北京市朝阳区")
    private String district;

    /**
     * 商品一级分类ID（关联分类表）
     */
    @Schema(description = "商品一级分类ID", example = "1")
    private Integer categoryFirstId;

    /**
     * 商品二级分类ID（关联分类表）
     */
    @Schema(description = "商品二级分类ID", example = "2")
    private Integer categorySecondId;

    /**
     * 商品三级分类ID（关联分类表）
     */
    @Schema(description = "商品三级分类ID", example = "3")
    private Integer categoryThirdId;

    /**
     * 品牌ID（关联品牌表）
     */
    @Schema(description = "品牌ID", example = "1")
    private Integer brandId;

    /**
     * 主图URL（OSS路径）
     */
    @Schema(description = "主图URL（OSS路径）", example = "12345678-1234-1234-1234-1234567890ab.jpg")
    private String mainImage;

    /**
     * 预览图URL（OSS路径）
     */
    @Schema(description = "预览图URL（OSS路径）", example = "12345678-1234-1234-1234-1234567890ab.jpg")
    private String thumbnailImage;

    /**
     * 列表图URL（OSS路径）
     */
    @Schema(description = "列表图URL（OSS路径）", example = "12345678-1234-1234-1234-1234567890ab.jpg")
    private String listingImage;

    /**
     * 详情图URL（OSS路径，多个用逗号分隔）
     */
    @Schema(description = "详情图URL（OSS路径，多个用逗号分隔）", example = "12345678-1234-1234-1234-1234567890ab.jpg,87654321-4321-4321-4321-0987654321ba.jpg")
    private String detailImages;

    /**
     * 商品描述
     */
    @Schema(description = "商品描述", example = "全新iPhone 15 Pro，搭载A17 Pro芯片，支持USB-C接口")
    private String description;

    /**
     * 商品规格（JSON格式存储）
     */
    @Schema(description = "商品规格（JSON格式存储）", example = "{\"颜色\":[\"深空黑色\",\"白色\",\"银色\",\"金色\"],\"容量\":[\"128GB\",\"256GB\",\"512GB\",\"1TB\"]}")
    private String specifications;

    /**
     * 创建人
     */
    @Schema(description = "创建人", example = "admin")
    private String createPin;
}