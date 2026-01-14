package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ZoneConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneConfigMapper {
    int insert(ZoneConfig zoneConfig);

    int deleteById(@Param("id") Long id);

    int update(ZoneConfig zoneConfig);

    ZoneConfig selectById(@Param("id") Long id);

    List<ZoneConfig> selectByItemType(@Param("itemType") Integer itemType);

    List<ZoneConfig> selectByIsDisplay(@Param("isDisplay") Integer isDisplay);

    List<ZoneConfig> selectAll();
}