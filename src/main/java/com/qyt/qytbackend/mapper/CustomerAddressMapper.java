package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.CustomerAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerAddressMapper {
    int insert(CustomerAddress customerAddress);

    int deleteById(@Param("id") Integer id);

    int update(CustomerAddress customerAddress);

    CustomerAddress selectById(@Param("id") Integer id);

    List<CustomerAddress> selectByIsDefault(@Param("isDefault") Integer isDefault);

    List<CustomerAddress> selectAll();
}