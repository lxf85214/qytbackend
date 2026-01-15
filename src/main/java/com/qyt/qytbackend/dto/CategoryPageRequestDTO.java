package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类分页查询请求DTO
 */
@Data
public class CategoryPageRequestDTO {
    /**
     * 分类层级
     */
    @Schema(description = "分类层级")
    private Integer level;
    /**
     * 分类名称（模糊匹配）
     */
    @Schema(description = "分类名称（模糊匹配）")
    private String categoryName;
    /**
     * 页码（默认1）
     */
    @Schema(description = "页码（默认1）", defaultValue = "1")
    private Integer pageNum = 1;
    /**
     * 页大小（默认10）
     */
    @Schema(description = "页大小（默认10）", defaultValue = "10")
    private Integer pageSize = 10;
}
