package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.OrderAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderAddressMapper {
    int insert(OrderAddress orderAddress);

    int deleteById(@Param("id") Long id);

    int update(OrderAddress orderAddress);

    OrderAddress selectById(@Param("id") Long id);

    List<OrderAddress> selectByOrderId(@Param("orderId") Long orderId);

    List<OrderAddress> selectAll();
}