package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {
    int insert(OrderInfo orderInfo);

    int deleteById(@Param("id") Long id);

    int update(OrderInfo orderInfo);

    OrderInfo selectById(@Param("id") Long id);

    List<OrderInfo> selectByUserId(@Param("userId") Integer userId);

    List<OrderInfo> selectByOrderStatus(@Param("orderStatus") Integer orderStatus);

    List<OrderInfo> selectByPayStatus(@Param("payStatus") Integer payStatus);

    List<OrderInfo> selectByOrderType(@Param("orderType") Integer orderType);

    List<OrderInfo> selectAll();
}