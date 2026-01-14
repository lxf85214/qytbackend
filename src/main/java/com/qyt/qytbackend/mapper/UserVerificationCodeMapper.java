package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.UserVerificationCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserVerificationCodeMapper {
    int insert(UserVerificationCode userVerificationCode);

    int deleteById(@Param("id") Long id);

    int update(UserVerificationCode userVerificationCode);

    UserVerificationCode selectById(@Param("id") Long id);

    List<UserVerificationCode> selectByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    List<UserVerificationCode> selectAll();
}