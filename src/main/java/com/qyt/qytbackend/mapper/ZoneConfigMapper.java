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

    /**
     * 查询首页推荐专区
     * @param itemType 类目类型
     * @return 专区列表
     */
    List<ZoneConfig> selectHomeRecommendZones(@Param("itemType") Integer itemType);
}