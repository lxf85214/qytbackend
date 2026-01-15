package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.entity.RegionInfo;
import com.qyt.qytbackend.service.RegionInfoService;
import com.qyt.qytbackend.dto.RegionPageRequestDTO;
import com.qyt.qytbackend.dto.RegionPageResponseDTO;
import com.qyt.qytbackend.dto.RegionCreateRequestDTO;
import com.qyt.qytbackend.dto.ApiResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 地区信息控制器
 */
@RestController
@RequestMapping("/api/region")
@Tag(name = "地区信息管理", description = "地区信息相关的API接口")
@Slf4j
public class RegionInfoController {

    @Autowired
    private RegionInfoService regionInfoService;

    /**
     * 创建地区
     *
     * @param requestDTO 地区创建请求DTO
     * @return API响应
     */
    @PostMapping("/create")
    @Operation(summary = "创建地区", description = "根据请求参数创建新的地区")
    public ApiResponseDTO<RegionInfo> createRegion(@Parameter(description = "地区创建请求参数") @RequestBody RegionCreateRequestDTO requestDTO) {
        try {
            // 验证请求参数
            if (requestDTO.getRegionName() == null || requestDTO.getRegionName().trim().isEmpty()) {
                return ApiResponseDTO.error(400, "地区名称不能为空");
            }
            if (requestDTO.getLevel() == null || requestDTO.getLevel() <= 0) {
                return ApiResponseDTO.error(400, "地区层级必须大于0");
            }
            if (requestDTO.getParentId() == null) {
                return ApiResponseDTO.error(400, "父地区ID不能为空");
            }
            if (requestDTO.getCreatePin() == null || requestDTO.getCreatePin().trim().isEmpty()) {
                return ApiResponseDTO.error(400, "创建人不能为空");
            }

            // 创建地区
            RegionInfo regionInfo = regionInfoService.createRegion(requestDTO);

            return ApiResponseDTO.success(regionInfo);
        } catch (Exception e) {
            log.error("创建地区失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 删除地区
     *
     * @param id 地区ID
     * @return API响应
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除地区", description = "根据地区ID进行软删除，将is_delete设置为1")
    public ApiResponseDTO<Boolean> deleteRegion(@Parameter(description = "地区ID") @PathVariable("id") Integer id) {
        try {
            // 验证ID参数
            if (id == null || id <= 0) {
                return ApiResponseDTO.error(400, "地区ID不能为空且必须大于0");
            }

            // 执行删除操作
            boolean result = regionInfoService.deleteRegion(id);

            if (result) {
                return ApiResponseDTO.success(true);
            } else {
                return ApiResponseDTO.error(404, "地区不存在");
            }
        } catch (Exception e) {
            log.error("删除地区失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 分页查询地区
     *
     * @param requestDTO 地区分页查询请求DTO
     * @return API响应
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询地区", description = "根据地区层级、地区名称模糊匹配进行分页查询，返回列表时包含下级所有数据")
    public ApiResponseDTO<RegionPageResponseDTO> pageQueryRegions(@Parameter(description = "地区分页查询请求参数") @RequestBody RegionPageRequestDTO requestDTO) {
        try {
            // 设置默认值
            if (requestDTO.getPageNum() == null || requestDTO.getPageNum() <= 0) {
                requestDTO.setPageNum(1);
            }
            if (requestDTO.getPageSize() == null || requestDTO.getPageSize() <= 0) {
                requestDTO.setPageSize(10);
            }

            // 分页查询
            RegionPageResponseDTO responseDTO = regionInfoService.pageQueryRegions(requestDTO);

            return ApiResponseDTO.success(responseDTO);
        } catch (Exception e) {
            log.error("分页查询地区失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 根据层级查询地区
     *
     * @param level 地区层级
     * @return API响应
     */
    @GetMapping("/by-level/{level}")
    @Operation(summary = "根据层级查询地区", description = "根据地区层级查询当前层级的地区记录，返回ID和名称")
    public ApiResponseDTO<List<RegionInfo>> getRegionsByLevel(@Parameter(description = "地区层级") @PathVariable("level") Integer level) {
        try {
            // 验证层级参数
            if (level == null || level <= 0) {
                return ApiResponseDTO.error(400, "地区层级必须大于0");
            }

            // 查询地区
            List<RegionInfo> regions = regionInfoService.getRegionIdAndNameByLevel(level);

            return ApiResponseDTO.success(regions);
        } catch (Exception e) {
            log.error("根据层级查询地区失败: {}", e.getMessage(), e);
            throw e;
        }
    }
}