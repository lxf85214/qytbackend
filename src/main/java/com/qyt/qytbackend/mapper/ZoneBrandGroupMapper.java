package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ZoneBrandGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneBrandGroupMapper {
    int insert(ZoneBrandGroup zoneBrandGroup);

    int deleteById(@Param("id") Long id);

    int update(ZoneBrandGroup zoneBrandGroup);

    ZoneBrandGroup selectById(@Param("id") Long id);

    List<ZoneBrandGroup> selectByBrandId(@Param("brandId") Long brandId);

    List<ZoneBrandGroup> selectAll();
}