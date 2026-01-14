package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.UserLoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLoginLogMapper {
    int insert(UserLoginLog userLoginLog);

    int deleteById(@Param("id") Integer id);

    int update(UserLoginLog userLoginLog);

    UserLoginLog selectById(@Param("id") Integer id);

    List<UserLoginLog> selectByUserId(@Param("userId") Long userId);

    List<UserLoginLog> selectByLoginStatus(@Param("loginStatus") Integer loginStatus);

    List<UserLoginLog> selectAll();
}