package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int insert(Role role);

    int deleteById(@Param("id") Integer id);

    int update(Role role);

    Role selectById(@Param("id") Integer id);

    List<Role> selectByStatus(@Param("status") Integer status);

    List<Role> selectAll();
}