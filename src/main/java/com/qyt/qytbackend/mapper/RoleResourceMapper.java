package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.RoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceMapper {
    int insert(RoleResource roleResource);

    int deleteById(@Param("id") Integer id);

    int update(RoleResource roleResource);

    RoleResource selectById(@Param("id") Integer id);

    List<RoleResource> selectByRoleId(@Param("roleId") Integer roleId);

    List<RoleResource> selectByResourceId(@Param("resourceId") Integer resourceId);

    List<RoleResource> selectAll();
}