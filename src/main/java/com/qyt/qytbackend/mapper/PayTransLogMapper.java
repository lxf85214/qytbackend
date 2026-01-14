package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.PayTransLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayTransLogMapper {
    int insert(PayTransLog payTransLog);

    int deleteById(@Param("id") Long id);

    int update(PayTransLog payTransLog);

    PayTransLog selectById(@Param("id") Long id);

    List<PayTransLog> selectByOrderId(@Param("orderId") Long orderId);

    List<PayTransLog> selectByPayStatus(@Param("payStatus") Integer payStatus);

    List<PayTransLog> selectByPayType(@Param("payType") Integer payType);

    List<PayTransLog> selectAll();
}