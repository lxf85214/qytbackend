package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 首页推荐数据对象
 * 包含不同展示位置的专区数据
 */
@Data
@Schema(name = "HomeRecommendData", description = "首页推荐数据")
public class HomeRecommendData {
    
    @Schema(description = "第一行专区列表（display_position=0）", example = "[{\"zoneName\": \"限时特惠\", \"defaultImage\": \"https://example.com/banner1.jpg\"}]\n")
    private List<ZoneData> firstRowZones;
    
    @Schema(description = "其他行专区列表（display_position=1）", example = "[{\"zoneName\": \"热销商品\", \"products\": [...]}]\n")
    private List<ZoneProductData> otherRowZones;
}