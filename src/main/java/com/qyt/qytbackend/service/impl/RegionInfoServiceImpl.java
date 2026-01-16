package com.qyt.qytbackend.service.impl;

import com.qyt.qytbackend.entity.RegionInfo;
import com.qyt.qytbackend.mapper.RegionInfoMapper;
import com.qyt.qytbackend.service.RegionInfoService;
import com.qyt.qytbackend.dto.RegionPageRequestDTO;
import com.qyt.qytbackend.dto.RegionPageResponseDTO;
import com.qyt.qytbackend.dto.RegionCreateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 地区信息服务实现类
 */
@Slf4j
@Service
public class RegionInfoServiceImpl implements RegionInfoService {

    @Autowired
    private RegionInfoMapper regionInfoMapper;

    @Override
    public RegionInfo createRegion(RegionCreateRequestDTO requestDTO) {
        log.info("开始创建地区: {}", requestDTO.getRegionName());
        log.info("父地区ID: {}, 层级: {}, 编码: {}", requestDTO.getParentId(), requestDTO.getLevel(), requestDTO.getRegionCode());
        log.info("操作人: {}", requestDTO.getCreatePin());

        // 创建地区实体
        RegionInfo regionInfo = new RegionInfo();
        regionInfo.setRegionName(requestDTO.getRegionName());
        regionInfo.setParentId(requestDTO.getParentId());
        regionInfo.setLevel(requestDTO.getLevel());
        regionInfo.setRegionCode(requestDTO.getRegionCode());
        regionInfo.setIsDelete(0); // 默认未删除

        // 设置创建人和更新人
        String username = requestDTO.getCreatePin();
        regionInfo.setCreatePin(username);
        regionInfo.setUpdatePin(username);

        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        regionInfo.setCreateTime(now);
        regionInfo.setUpdateTime(now);

        // 执行数据库插入操作
        regionInfoMapper.insert(regionInfo);

        return regionInfo;
    }

    @Override
    public boolean deleteRegion(Integer id) {
        log.info("开始删除地区: ID={}", id);

        // 根据ID查询地区
        RegionInfo regionInfo = regionInfoMapper.selectById(id);
        if (regionInfo == null) {
            log.warn("地区不存在: ID={}", id);
            return false;
        }

        // 设置为已删除
        regionInfo.setIsDelete(1);
        regionInfo.setUpdateTime(LocalDateTime.now());

        // 执行更新操作
        int result = regionInfoMapper.update(regionInfo);
        log.info("删除地区结果: {}", result > 0 ? "成功" : "失败");

        return result > 0;
    }

    @Override
    public RegionPageResponseDTO pageQueryRegions(RegionPageRequestDTO requestDTO) {
        log.info("开始分页查询地区: 层级={}, 地区名称={}, 页码={}, 页大小={}",
                requestDTO.getLevel(), requestDTO.getRegionName(), requestDTO.getPageNum(), requestDTO.getPageSize());

        // 设置默认值
        if (requestDTO.getPageNum() == null || requestDTO.getPageNum() <= 0) {
            requestDTO.setPageNum(1);
        }
        if (requestDTO.getPageSize() == null || requestDTO.getPageSize() <= 0) {
            requestDTO.setPageSize(10);
        }

        // 构建查询参数
        Map<String, Object> params = new HashMap<>();
        params.put("regionName", requestDTO.getRegionName());
        params.put("regionType", requestDTO.getLevel()); // 注意：XML中使用的是regionType，实体类中使用的是level
        params.put("parentId", requestDTO.getParentId());
        params.put("offset", (requestDTO.getPageNum() - 1) * requestDTO.getPageSize());
        params.put("limit", requestDTO.getPageSize());

        // 查询分页数据
        List<RegionInfo> regions = regionInfoMapper.selectByPage(params);

        // 查询总记录数
        int total = regionInfoMapper.selectCount(params);

        // 构建响应数据
        RegionPageResponseDTO responseDTO = new RegionPageResponseDTO();
        responseDTO.setTotal(total);
        responseDTO.setPageNum(requestDTO.getPageNum());
        responseDTO.setPageSize(requestDTO.getPageSize());

        // 为每个地区添加下级地区列表
        List<RegionPageResponseDTO.RegionWithChildrenDTO> regionWithChildrenList = regions.stream()
                .map(region -> {
                    RegionPageResponseDTO.RegionWithChildrenDTO regionWithChildren = new RegionPageResponseDTO.RegionWithChildrenDTO();
                    regionWithChildren.setRegion(region);
                    // 查询下级地区
                    regionWithChildren.setChildren(buildRegionTree(region.getId().intValue()));
                    return regionWithChildren;
                })
                .collect(Collectors.toList());

        responseDTO.setList(regionWithChildrenList);

        return responseDTO;
    }

    @Override
    public List<RegionInfo> getRegionsByLevel(Integer level) {
        log.info("开始根据层级查询地区: 层级={}", level);

        // 查询指定层级的地区
        List<RegionInfo> regions = regionInfoMapper.selectByRegionType(level);

        // 为每个地区添加下级地区
        // 这里可以根据需要构建完整的树形结构
        return new ArrayList<>(regions);
    }

    @Override
    public List<RegionInfo> getRegionIdAndNameByLevel(Integer level) {
        log.info("开始根据层级查询地区ID和名称: 层级={}", level);

        // 查询指定层级的地区
        List<RegionInfo> regions = regionInfoMapper.selectByRegionType(level);

        // 只保留ID和名称
        return regions.stream().map(region -> {
            RegionInfo result = new RegionInfo();
            result.setId(region.getId());
            result.setRegionName(region.getRegionName());
            return result;
        }).collect(Collectors.toList());
    }

    /**
     * 构建地区树
     *
     * @param parentId 父地区ID
     * @return 地区树
     */
    private List<RegionPageResponseDTO.RegionWithChildrenDTO> buildRegionTree(Integer parentId) {
        List<RegionInfo> childRegions = regionInfoMapper.selectByParentId(parentId);
        List<RegionPageResponseDTO.RegionWithChildrenDTO> result = new ArrayList<>();

        for (RegionInfo childRegion : childRegions) {
            RegionPageResponseDTO.RegionWithChildrenDTO regionWithChildren = new RegionPageResponseDTO.RegionWithChildrenDTO();
            regionWithChildren.setRegion(childRegion);
            // 递归查询下级地区
            regionWithChildren.setChildren(buildRegionTree(childRegion.getId().intValue()));
            result.add(regionWithChildren);
        }

        return result;
    }
}