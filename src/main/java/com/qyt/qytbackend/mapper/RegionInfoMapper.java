package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.RegionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionInfoMapper {
    int insert(RegionInfo regionInfo);

    int deleteById(@Param("id") Integer id);

    int update(RegionInfo regionInfo);

    RegionInfo selectById(@Param("id") Integer id);

    List<RegionInfo> selectByParentId(@Param("parentId") Integer parentId);

    List<RegionInfo> selectByRegionType(@Param("regionType") Integer regionType);

    List<RegionInfo> selectByStatus(@Param("status") Integer status);

    List<RegionInfo> selectAll();
}