package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ProductAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductAttrMapper {
    int insert(ProductAttr productAttr);

    int deleteById(@Param("id") Integer id);

    int update(ProductAttr productAttr);

    ProductAttr selectById(@Param("id") Integer id);

    List<ProductAttr> selectByProductId(@Param("productId") Integer productId);

    List<ProductAttr> selectByAttrType(@Param("attrType") Integer attrType);

    List<ProductAttr> selectAll();
}