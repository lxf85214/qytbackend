package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryMapper {
    int insert(ProductCategory productCategory);

    int deleteById(@Param("id") Integer id);

    int update(ProductCategory productCategory);

    ProductCategory selectById(@Param("id") Integer id);

    List<ProductCategory> selectByParentId(@Param("parentId") Integer parentId);

    List<ProductCategory> selectByLevel(@Param("level") Integer level);

    List<ProductCategory> selectByStatus(@Param("status") Integer status);

    List<ProductCategory> selectAll();
}