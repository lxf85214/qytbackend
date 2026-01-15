package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 地区分页查询请求DTO
 */
@Data
public class RegionPageRequestDTO {
    /**
     * 页码
     */
    @Schema(description = "页码")
    private Integer pageNum;

    /**
     * 页大小
     */
    @Schema(description = "页大小")
    private Integer pageSize;

    /**
     * 地区名称
     */
    @Schema(description = "地区名称")
    private String regionName;

    /**
     * 地区层级
     */
    @Schema(description = "地区层级")
    private Integer level;

    /**
     * 父地区ID
     */
    @Schema(description = "父地区ID")
    private Integer parentId;
}