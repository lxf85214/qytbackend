package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.UserLoginLog;

/**
 * 用户登录日志Mapper接口
 */
public interface UserLoginLogMapper {
    /**
     * 插入登录日志
     * @param userLoginLog 登录日志对象
     * @return 插入结果
     */
    int insert(UserLoginLog userLoginLog);
}
