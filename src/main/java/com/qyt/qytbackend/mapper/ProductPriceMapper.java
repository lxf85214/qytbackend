package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ProductPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductPriceMapper {
    int insert(ProductPrice productPrice);

    int deleteById(@Param("id") Long id);

    int update(ProductPrice productPrice);

    ProductPrice selectById(@Param("id") Long id);

    List<ProductPrice> selectByProductId(@Param("productId") Long productId);

    List<ProductPrice> selectByPriceType(@Param("priceType") String priceType);

    List<ProductPrice> selectAll();
}