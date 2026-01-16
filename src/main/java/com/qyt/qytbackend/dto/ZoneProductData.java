package com.qyt.qytbackend.dto;

import com.qyt.qytbackend.entity.ProductInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 专区商品数据对象
 * 用于表示display_position=1的专区数据，包含专区名称和商品列表
 */
@Data
@Schema(name = "ZoneProductData", description = "专区商品数据")
public class ZoneProductData {
    
    @Schema(description = "专区名称", example = "热销商品")
    private String zoneName;
    
    @Schema(description = "商品列表")
    private List<ProductInfo> products;
}