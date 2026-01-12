package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.UserVerificationCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户验证码记录Mapper接口
 *
 * @author qyt
 * @date 2024
 */
public interface UserVerificationCodeMapper {
    /**
     * 插入用户验证码记录
     *
     * @param userVerificationCode 用户验证码记录实体
     * @return 影响行数
     */
    int insert(UserVerificationCode userVerificationCode);

    /**
     * 根据ID删除用户验证码记录
     *
     * @param id 主键ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 更新用户验证码记录
     *
     * @param userVerificationCode 用户验证码记录实体
     * @return 影响行数
     */
    int update(UserVerificationCode userVerificationCode);

    /**
     * 根据ID查询用户验证码记录
     *
     * @param id 主键ID
     * @return 用户验证码记录实体
     */
    UserVerificationCode selectById(Long id);

    /**
     * 查询所有用户验证码记录
     *
     * @return 用户验证码记录列表
     */
    List<UserVerificationCode> selectAll();

    /**
     * 根据手机号查询最新的验证码记录
     *
     * @param phoneNumber 手机号
     * @return 用户验证码记录实体
     */
    UserVerificationCode selectLatestByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
