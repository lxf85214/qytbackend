package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole userRole);

    int deleteById(@Param("id") Integer id);

    int update(UserRole userRole);

    UserRole selectById(@Param("id") Integer id);

    List<UserRole> selectByUserId(@Param("userId") Integer userId);

    List<UserRole> selectByRoleId(@Param("roleId") Integer roleId);

    List<UserRole> selectAll();
}