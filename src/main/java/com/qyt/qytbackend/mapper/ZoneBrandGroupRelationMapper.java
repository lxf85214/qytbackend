package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ZoneBrandGroupRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneBrandGroupRelationMapper {
    int insert(ZoneBrandGroupRelation zoneBrandGroupRelation);

    int deleteById(@Param("id") Long id);

    int update(ZoneBrandGroupRelation zoneBrandGroupRelation);

    ZoneBrandGroupRelation selectById(@Param("id") Long id);

    List<ZoneBrandGroupRelation> selectByZoneId(@Param("zoneId") Long zoneId);

    List<ZoneBrandGroupRelation> selectByBrandGroupId(@Param("brandGroupId") Long brandGroupId);

    List<ZoneBrandGroupRelation> selectAll();
}