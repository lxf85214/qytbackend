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

    /**
     * 分页查询商品列表
     * @param thirdCategoryId 三级分类ID，非必传
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 商品列表
     */
    List<ProductInfo> selectProductPage(@Param("thirdCategoryId") Integer thirdCategoryId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * 查询商品总数
     * @param thirdCategoryId 三级分类ID，非必传
     * @return 商品总数
     */
    Long selectProductCount(@Param("thirdCategoryId") Integer thirdCategoryId);

    /**
     * 批量查询商品信息
     * @param productIds 商品ID列表
     * @return 商品列表
     */
    List<ProductInfo> selectByIds(@Param("productIds") List<Integer> productIds);
}