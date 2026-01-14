package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ZoneProductGroupRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneProductGroupRelationMapper {
    int insert(ZoneProductGroupRelation zoneProductGroupRelation);

    int deleteById(@Param("id") Long id);

    int update(ZoneProductGroupRelation zoneProductGroupRelation);

    ZoneProductGroupRelation selectById(@Param("id") Long id);

    List<ZoneProductGroupRelation> selectByZoneId(@Param("zoneId") Long zoneId);

    List<ZoneProductGroupRelation> selectByGroupId(@Param("groupId") Long groupId);

    List<ZoneProductGroupRelation> selectAll();
}