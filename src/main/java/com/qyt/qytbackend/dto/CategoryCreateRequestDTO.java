package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类创建请求DTO
 */
@Data
@Schema(name = "CategoryCreateRequestDTO", description = "分类创建请求参数")
public class CategoryCreateRequestDTO {
    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoryName;
    @Schema(description = "父分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer parentId;
    @Schema(description = "分类层级", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer level;
    @Schema(description = "排序号")
    private Integer sortOrder;
}
