package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ZoneProductGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneProductGroupMapper {
    int insert(ZoneProductGroup zoneProductGroup);

    int deleteById(@Param("id") Long id);

    int update(ZoneProductGroup zoneProductGroup);

    ZoneProductGroup selectById(@Param("id") Long id);

    List<ZoneProductGroup> selectByProductId(@Param("productId") Long productId);

    List<ZoneProductGroup> selectAll();
}