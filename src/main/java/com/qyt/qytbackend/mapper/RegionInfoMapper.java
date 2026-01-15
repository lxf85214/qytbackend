package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.RegionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RegionInfoMapper {
    int insert(RegionInfo regionInfo);

    int deleteById(@Param("id") Integer id);

    int update(RegionInfo regionInfo);

    RegionInfo selectById(@Param("id") Integer id);

    List<RegionInfo> selectByParentId(@Param("parentId") Integer parentId);

    List<RegionInfo> selectByRegionType(@Param("regionType") Integer regionType);

    List<RegionInfo> selectByStatus(@Param("status") Integer status);

    List<RegionInfo> selectAll();

    /**
     * 分页查询
     * @param params 查询参数
     * @return 地区列表
     */
    List<RegionInfo> selectByPage(Map<String, Object> params);

    /**
     * 查询总数
     * @param params 查询参数
     * @return 总数
     */
    int selectCount(Map<String, Object> params);
}