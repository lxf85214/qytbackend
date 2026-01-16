package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 专区数据对象
 * 用于表示display_position=0的专区数据，只包含专区名称和默认图片
 */
@Data
@Schema(name = "ZoneData", description = "专区数据")
public class ZoneData {
    
    @Schema(description = "专区名称", example = "限时特惠")
    private String zoneName;
    
    @Schema(description = "专区默认图片URL", example = "https://example.com/banner1.jpg")
    private String defaultImage;
}