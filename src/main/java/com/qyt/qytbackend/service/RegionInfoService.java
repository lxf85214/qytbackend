package com.qyt.qytbackend.service;

import com.qyt.qytbackend.entity.RegionInfo;
import com.qyt.qytbackend.dto.RegionPageRequestDTO;
import com.qyt.qytbackend.dto.RegionPageResponseDTO;
import com.qyt.qytbackend.dto.RegionCreateRequestDTO;

import java.util.List;

/**
 * 地区信息服务接口
 */
public interface RegionInfoService {
    /**
     * 创建地区
     *
     * @param requestDTO 地区创建请求DTO
     * @return 创建的地区对象
     */
    RegionInfo createRegion(RegionCreateRequestDTO requestDTO);

    /**
     * 删除地区（软删除）
     *
     * @param id 地区ID
     * @return 是否删除成功
     */
    boolean deleteRegion(Integer id);

    /**
     * 分页查询地区
     *
     * @param requestDTO 地区分页查询请求DTO
     * @return 地区分页查询响应DTO
     */
    RegionPageResponseDTO pageQueryRegions(RegionPageRequestDTO requestDTO);

    /**
     * 根据层级查询地区
     *
     * @param level 地区层级
     * @return 地区列表，包含下级所有数据
     */
    List<RegionInfo> getRegionsByLevel(Integer level);

    /**
     * 根据层级查询地区（仅返回ID和名称）
     *
     * @param level 地区层级
     * @return 地区列表，仅包含ID和名称
     */
    List<RegionInfo> getRegionIdAndNameByLevel(Integer level);
}