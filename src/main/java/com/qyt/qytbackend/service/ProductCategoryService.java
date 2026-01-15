package com.qyt.qytbackend.service;

import com.qyt.qytbackend.entity.ProductCategory;
import com.qyt.qytbackend.dto.CategoryCreateRequestDTO;
import com.qyt.qytbackend.dto.CategoryPageRequestDTO;
import com.qyt.qytbackend.dto.CategoryPageResponseDTO;

import java.util.List;

/**
 * 商品分类服务接口
 */
public interface ProductCategoryService {
    /**
     * 创建商品分类
     *
     * @param requestDTO 分类创建请求DTO
     * @return 创建的分类对象
     */
    ProductCategory createCategory(CategoryCreateRequestDTO requestDTO);

    /**
     * 分页查询商品分类
     *
     * @param requestDTO 分类分页查询请求DTO
     * @return 分类分页查询响应DTO
     */
    CategoryPageResponseDTO pageQueryCategories(CategoryPageRequestDTO requestDTO);

    /**
     * 根据父分类ID查询子分类
     *
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<ProductCategory> getChildCategories(Integer parentId);
}
