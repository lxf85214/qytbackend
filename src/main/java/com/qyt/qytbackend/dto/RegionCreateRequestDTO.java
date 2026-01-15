package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 地区创建请求DTO
 */
@Data
public class RegionCreateRequestDTO {
    /**
     * 地区名称
     */
    @Schema(description = "地区名称")
    private String regionName;

    /**
     * 父地区ID
     */
    @Schema(description = "父地区ID")
    private Integer parentId;

    /**
     * 地区层级
     */
    @Schema(description = "地区层级")
    private Integer level;

    /**
     * 地区编码
     */
    @Schema(description = "地区编码")
    private String regionCode;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createPin;
}