package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.qyt.qytbackend.entity.ProductCategory;

import java.util.List;

/**
 * 分类分页查询响应DTO
 */
@Data
public class CategoryPageResponseDTO {
    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private long total;
    /**
     * 页码
     */
    @Schema(description = "页码")
    private int pageNum;
    /**
     * 页大小
     */
    @Schema(description = "页大小")
    private int pageSize;
    /**
     * 分类列表
     */
    @Schema(description = "分类列表")
    private List<CategoryWithChildrenDTO> list;

    /**
     * 带子分类的分类DTO
     */
    @Data
    public static class CategoryWithChildrenDTO {
        /**
         * 分类信息
         */
        @Schema(description = "分类信息")
        private ProductCategory category;
        /**
         * 子分类列表
         */
        @Schema(description = "子分类列表")
        private List<ProductCategory> children;
    }
}
