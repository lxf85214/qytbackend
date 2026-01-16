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

    /**
     * 根据group_id查询商品组下的所有商品
     * @param groupId 商品组ID
     * @return 商品列表
     */
    List<ZoneProductGroup> selectByGroupId(@Param("groupId") Long groupId);
}