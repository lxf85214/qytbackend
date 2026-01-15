package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类创建请求DTO
 */
@Data
@Schema(name = "CategoryCreateRequestDTO", description = "分类创建请求参数")
public class CategoryCreateRequestDTO {
    @Schema(description = "分类名称", required = true)
    private String categoryName;
    @Schema(description = "父分类ID", required = true)
    private Integer parentId;
    @Schema(description = "分类层级", required = true)
    private Integer level;
    @Schema(description = "排序号")
    private Integer sortOrder;
    @Schema(description = "用户信息", required = true)
    private UserInfoDTO userInfo;
}
