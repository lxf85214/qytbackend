package com.qyt.qytbackend.service;

import com.qyt.qytbackend.entity.ProductCategory;
import com.qyt.qytbackend.dto.CategoryCreateRequestDTO;

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
}
