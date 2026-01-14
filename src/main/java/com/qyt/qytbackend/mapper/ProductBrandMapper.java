package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ProductBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductBrandMapper {
    int insert(ProductBrand productBrand);

    int deleteById(@Param("id") Integer id);

    int update(ProductBrand productBrand);

    ProductBrand selectById(@Param("id") Integer id);

    List<ProductBrand> selectByBrandName(@Param("brandName") String brandName);

    List<ProductBrand> selectByStatus(@Param("status") Integer status);

    List<ProductBrand> selectAll();
}