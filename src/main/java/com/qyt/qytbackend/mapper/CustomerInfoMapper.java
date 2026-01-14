package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.CustomerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户信息Mapper接口
 *
 * @author qyt
 * @date 2024
 */
public interface CustomerInfoMapper {
    /**
     * 插入客户信息
     *
     * @param customerInfo 客户信息实体
     * @return 影响行数
     */
    int insert(CustomerInfo customerInfo);

    /**
     * 根据ID删除客户信息
     *
     * @param id 主键ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 更新客户信息
     *
     * @param customerInfo 客户信息实体
     * @return 影响行数
     */
    int update(CustomerInfo customerInfo);

    /**
     * 根据ID查询客户信息
     *
     * @param id 主键ID
     * @return 客户信息实体
     */
    CustomerInfo selectById(@Param("id") Long id);

    /**
     * 查询所有客户信息
     *
     * @return 客户信息列表
     */
    List<CustomerInfo> selectAll();

    /**
     * 根据用户名查询客户信息
     *
     * @param username 用户名
     * @return 客户信息实体
     */
    CustomerInfo selectByUsername(@Param("username") String username);

    /**
     * 根据手机号查询客户信息
     *
     * @param phone 手机号
     * @return 客户信息实体
     */
    CustomerInfo selectByPhone(@Param("phone") String phone);

    /**
     * 根据邮箱查询客户信息
     *
     * @param email 邮箱
     * @return 客户信息实体
     */
    CustomerInfo selectByEmail(@Param("email") String email);
}
