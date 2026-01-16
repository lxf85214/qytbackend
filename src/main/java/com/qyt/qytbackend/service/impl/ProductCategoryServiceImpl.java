package com.qyt.qytbackend.service.impl;

import com.qyt.qytbackend.entity.ProductCategory;
import com.qyt.qytbackend.mapper.ProductCategoryMapper;
import com.qyt.qytbackend.service.ProductCategoryService;
import com.qyt.qytbackend.dto.CategoryCreateRequestDTO;
import com.qyt.qytbackend.dto.CategoryPageRequestDTO;
import com.qyt.qytbackend.dto.CategoryPageResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        productCategory.setIsDelete(0); // 默认未删除

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

    @Override
    public CategoryPageResponseDTO pageQueryCategories(CategoryPageRequestDTO requestDTO) {
        log.info("开始分页查询商品分类: 层级={}, 分类名称={}, 页码={}, 页大小={}",
                requestDTO.getLevel(), requestDTO.getCategoryName(), requestDTO.getPageNum(), requestDTO.getPageSize());

        // 计算偏移量
        int offset = (requestDTO.getPageNum() - 1) * requestDTO.getPageSize();

        // 查询分页数据
        List<ProductCategory> categories = productCategoryMapper.selectByPage(
                requestDTO.getLevel(),
                requestDTO.getCategoryName(),
                offset,
                requestDTO.getPageSize()
        );

        // 查询总记录数
        int total = productCategoryMapper.selectCount(
                requestDTO.getLevel(),
                requestDTO.getCategoryName()
        );

        // 构建响应数据
        CategoryPageResponseDTO responseDTO = new CategoryPageResponseDTO();
        responseDTO.setTotal(total);
        responseDTO.setPageNum(requestDTO.getPageNum());
        responseDTO.setPageSize(requestDTO.getPageSize());

        // 为每个分类添加子分类列表
        List<CategoryPageResponseDTO.CategoryWithChildrenDTO> categoryWithChildrenList = categories.stream()
                .map(category -> {
                    CategoryPageResponseDTO.CategoryWithChildrenDTO categoryWithChildren = new CategoryPageResponseDTO.CategoryWithChildrenDTO();
                    categoryWithChildren.setCategory(category);
                    // 查询子分类
                    List<ProductCategory> children = getChildCategories(category.getId().intValue());
                    categoryWithChildren.setChildren(children);
                    return categoryWithChildren;
                })
                .collect(Collectors.toList());

        responseDTO.setList(categoryWithChildrenList);

        return responseDTO;
    }

    @Override
    public List<ProductCategory> getChildCategories(Integer parentId) {
        return productCategoryMapper.selectByParentId(parentId);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        log.info("开始删除商品分类: ID={}", id);

        // 根据ID查询分类
        ProductCategory productCategory = productCategoryMapper.selectById(id);
        if (productCategory == null) {
            log.warn("分类不存在: ID={}", id);
            return false;
        }

        // 设置为已删除
        productCategory.setIsDelete(1);
        productCategory.setUpdateTime(LocalDateTime.now());

        // 执行更新操作
        int result = productCategoryMapper.update(productCategory);
        log.info("删除商品分类结果: {}", result > 0 ? "成功" : "失败");

        return result > 0;
    }
}
