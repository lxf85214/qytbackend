package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.CustomerLoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户登录日志Mapper接口
 *
 * @author qyt
 * @date 2024
 */
public interface CustomerLoginLogMapper {
    /**
     * 插入登录日志
     *
     * @param customerLoginLog 登录日志实体
     * @return 影响行数
     */
    int insert(CustomerLoginLog customerLoginLog);

    /**
     * 根据ID删除登录日志
     *
     * @param loginLogId 主键ID
     * @return 影响行数
     */
    int deleteById(@Param("loginLogId") Integer loginLogId);

    /**
     * 更新登录日志
     *
     * @param customerLoginLog 登录日志实体
     * @return 影响行数
     */
    int update(CustomerLoginLog customerLoginLog);

    /**
     * 根据ID查询登录日志
     *
     * @param loginLogId 主键ID
     * @return 登录日志实体
     */
    CustomerLoginLog selectById(@Param("loginLogId") Integer loginLogId);

    /**
     * 查询所有登录日志
     *
     * @return 登录日志列表
     */
    List<CustomerLoginLog> selectAll();

    /**
     * 根据用户ID查询登录日志列表
     *
     * @param userId 用户ID
     * @return 登录日志列表
     */
    List<CustomerLoginLog> selectByUserId(@Param("userId") String userId);

    /**
     * 根据用户ID查询最新的登录日志
     *
     * @param userId 用户ID
     * @return 登录日志实体
     */
    CustomerLoginLog selectLatestByUserId(@Param("userId") String userId);
}
