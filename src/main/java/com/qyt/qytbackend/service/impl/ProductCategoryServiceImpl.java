package com.qyt.qytbackend.service.impl;

import com.qyt.qytbackend.entity.ProductCategory;
import com.qyt.qytbackend.mapper.ProductCategoryMapper;
import com.qyt.qytbackend.service.ProductCategoryService;
import com.qyt.qytbackend.dto.CategoryCreateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * 商品分类服务实现类
 */
@Slf4j
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategory createCategory(CategoryCreateRequestDTO requestDTO) {
        log.info("开始创建商品分类: {}", requestDTO.getCategoryName());
        log.info("父分类ID: {}, 层级: {}, 排序: {}", requestDTO.getParentId(), requestDTO.getLevel(), requestDTO.getSortOrder());
        log.info("操作人: {}", requestDTO.getUserInfo().getUsername());

        // 创建分类实体
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName(requestDTO.getCategoryName());
        productCategory.setParentId(requestDTO.getParentId());
        productCategory.setLevel(requestDTO.getLevel());
        productCategory.setSortOrder(requestDTO.getSortOrder());
        productCategory.setStatus(1); // 默认启用状态
        productCategory.setIsDeleted(0); // 默认未删除

        // 设置创建人和更新人
        String username = requestDTO.getUserInfo().getUsername();
        productCategory.setCreatePin(username);
        productCategory.setUpdatePin(username);

        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        productCategory.setCreateTime(now);
        productCategory.setUpdateTime(now);

        // 实际的数据库插入操作（暂时注释掉，因为可能没有数据库连接）
        productCategoryMapper.insert(productCategory);

        return productCategory;
    }
}
