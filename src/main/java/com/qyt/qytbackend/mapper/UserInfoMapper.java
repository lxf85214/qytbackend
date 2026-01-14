package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int insert(UserInfo userInfo);

    int deleteById(@Param("id") Integer id);

    int update(UserInfo userInfo);

    UserInfo selectById(@Param("id") Integer id);

    UserInfo selectByUsername(@Param("username") String username);

    UserInfo selectByPhone(@Param("phone") String phone);

    List<UserInfo> selectByUserType(@Param("userType") Integer userType);

    List<UserInfo> selectByStatus(@Param("status") Integer status);

    List<UserInfo> selectAll();
}