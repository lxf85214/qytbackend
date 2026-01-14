package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartMapper {
    int insert(ShoppingCart shoppingCart);

    int deleteById(@Param("id") Integer id);

    int update(ShoppingCart shoppingCart);

    ShoppingCart selectById(@Param("id") Integer id);

    List<ShoppingCart> selectByUserId(@Param("userId") Integer userId);

    List<ShoppingCart> selectByProductId(@Param("productId") Integer productId);

    List<ShoppingCart> selectBySelected(@Param("selected") Integer selected);

    List<ShoppingCart> selectAll();
}