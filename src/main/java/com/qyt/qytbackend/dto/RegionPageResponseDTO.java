package com.qyt.qytbackend.dto;

import com.qyt.qytbackend.entity.RegionInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 地区分页查询响应DTO
 */
@Data
public class RegionPageResponseDTO {
    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private int total;

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
     * 地区列表
     */
    @Schema(description = "地区列表")
    private List<RegionWithChildrenDTO> list;

    /**
     * 地区带下级列表DTO
     */
    @Data
    public static class RegionWithChildrenDTO {
        /**
         * 地区信息
         */
        @Schema(description = "地区信息")
        private RegionInfo region;

        /**
         * 下级地区列表
         */
        @Schema(description = "下级地区列表")
        private List<RegionWithChildrenDTO> children;
    }
}