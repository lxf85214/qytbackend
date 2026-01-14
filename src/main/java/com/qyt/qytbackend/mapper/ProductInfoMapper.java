package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoMapper {
    int insert(ProductInfo productInfo);

    int deleteById(@Param("id") Integer id);

    int update(ProductInfo productInfo);

    ProductInfo selectById(@Param("id") Integer id);

    List<ProductInfo> selectByProductName(@Param("productName") String productName);

    List<ProductInfo> selectByCategoryId(@Param("categoryId") Integer categoryId);

    List<ProductInfo> selectByBrandId(@Param("brandId") Integer brandId);

    List<ProductInfo> selectByStatus(@Param("status") Integer status);

    List<ProductInfo> selectAll();
}