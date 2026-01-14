package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.CustomerVerificationCode;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CustomerVerificationCodeMapper {
    int insert(CustomerVerificationCode customerVerificationCode);
    int deleteById(@Param("id") Long id);
    int update(CustomerVerificationCode customerVerificationCode);
    CustomerVerificationCode selectById(@Param("id") Long id);
    CustomerVerificationCode selectByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    CustomerVerificationCode selectLatestByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    List<CustomerVerificationCode> selectAll();
}