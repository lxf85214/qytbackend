package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int insert(OrderItem orderItem);

    int deleteById(@Param("id") Integer id);

    int update(OrderItem orderItem);

    OrderItem selectById(@Param("id") Integer id);

    List<OrderItem> selectByOrderId(@Param("orderId") Long orderId);

    List<OrderItem> selectByProductId(@Param("productId") Long productId);

    List<OrderItem> selectAll();
}