package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.ProductAddAndRemove;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductAddAndRemoveMapper {
    int insert(ProductAddAndRemove productAddAndRemove);

    int deleteById(@Param("id") Long id);

    int update(ProductAddAndRemove productAddAndRemove);

    ProductAddAndRemove selectById(@Param("id") Long id);

    List<ProductAddAndRemove> selectByOperationType(@Param("operationType") String operationType);

    List<ProductAddAndRemove> selectByApprovalStatus(@Param("approvalStatus") String approvalStatus);

    List<ProductAddAndRemove> selectByBrandId(@Param("brandId") Long brandId);

    List<ProductAddAndRemove> selectByProductName(@Param("productName") String productName);

    List<ProductAddAndRemove> selectAll();
}