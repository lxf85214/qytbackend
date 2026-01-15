package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品发布响应DTO
 */
@Data
public class ProductPublishResponseDTO {
    /**
     * 商品ID
     */
    @Schema(description = "商品ID", example = "1")
    private Long id;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称", example = "iPhone 15 Pro")
    private String productName;

    /**
     * 状态：0-下架，1-上架
     */
    @Schema(description = "状态：0-下架，1-上架", example = "0")
    private Integer status;
}