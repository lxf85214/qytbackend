package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ProductStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
    int insert(ProductStock productStock);

    int deleteById(@Param("id") Integer id);

    int update(ProductStock productStock);

    ProductStock selectById(@Param("id") Integer id);

    List<ProductStock> selectByProductId(@Param("productId") Long productId);

    List<ProductStock> selectByWarehouseId(@Param("warehouseId") Long warehouseId);

    List<ProductStock> selectAll();
}