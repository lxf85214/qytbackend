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

    /**
     * 分页查询商品分类
     * @param level 分类层级
     * @param categoryName 分类名称（模糊匹配）
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 分类列表
     */
    List<ProductCategory> selectByPage(@Param("level") Integer level, @Param("categoryName") String categoryName, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询商品分类总数
     * @param level 分类层级
     * @param categoryName 分类名称（模糊匹配）
     * @return 总数
     */
    int selectCount(@Param("level") Integer level, @Param("categoryName") String categoryName);
}