package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    int insert(Resource resource);

    int deleteById(@Param("id") Integer id);

    int update(Resource resource);

    Resource selectById(@Param("id") Integer id);

    List<Resource> selectByPid(@Param("pid") Integer pid);

    List<Resource> selectByType(@Param("type") String type);

    List<Resource> selectByStatus(@Param("status") Integer status);

    List<Resource> selectAll();
}