package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.entity.ProductCategory;
import com.qyt.qytbackend.service.ProductCategoryService;
import com.qyt.qytbackend.dto.CategoryCreateRequestDTO;
import com.qyt.qytbackend.dto.CategoryPageRequestDTO;
import com.qyt.qytbackend.dto.CategoryPageResponseDTO;
import com.qyt.qytbackend.dto.ApiResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * 商品分类控制器
 */
@RestController
@RequestMapping("/api/category")
@Tag(name = "商品分类管理", description = "商品分类相关的API接口")
public class ProductCategoryController {

    private static final Logger log = LoggerFactory.getLogger(ProductCategoryController.class);

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 创建商品分类
     *
     * @param requestDTO 分类创建请求DTO
     * @return API响应
     */
    @PostMapping("/create")
    @Operation(summary = "创建商品分类", description = "根据请求参数创建新的商品分类")
    public ApiResponseDTO<ProductCategory> createCategory(@Parameter(description = "分类创建请求参数") @RequestBody CategoryCreateRequestDTO requestDTO) {
        try {
            // 验证请求参数
            if (requestDTO.getCategoryName() == null || requestDTO.getCategoryName().trim().isEmpty()) {
                return ApiResponseDTO.error(400, "分类名称不能为空");
            }
            if (requestDTO.getLevel() == null || requestDTO.getLevel() <= 0) {
                return ApiResponseDTO.error(400, "分类层级必须大于0");
            }
            if (requestDTO.getParentId() == null) {
                return ApiResponseDTO.error(400, "父分类ID不能为空");
            }
            if (requestDTO.getUserInfo() == null || requestDTO.getUserInfo().getUsername() == null) {
                return ApiResponseDTO.error(400, "用户信息不能为空");
            }

            // 创建分类
            ProductCategory productCategory = productCategoryService.createCategory(requestDTO);

            return ApiResponseDTO.success(productCategory);
        } catch (Exception e) {
            log.error("创建分类失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 分页查询商品分类
     *
     * @param requestDTO 分类分页查询请求DTO
     * @return API响应
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询商品分类", description = "根据分类层级、分类名称模糊匹配进行分页查询")
    public ApiResponseDTO<CategoryPageResponseDTO> pageQueryCategories(@Parameter(description = "分类分页查询请求参数") @RequestBody CategoryPageRequestDTO requestDTO) {
        try {
            // 设置默认值
            if (requestDTO.getPageNum() == null || requestDTO.getPageNum() <= 0) {
                requestDTO.setPageNum(1);
            }
            if (requestDTO.getPageSize() == null || requestDTO.getPageSize() <= 0) {
                requestDTO.setPageSize(10);
            }

            // 分页查询
            CategoryPageResponseDTO responseDTO = productCategoryService.pageQueryCategories(requestDTO);

            return ApiResponseDTO.success(responseDTO);
        } catch (Exception e) {
            log.error("分页查询分类失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 删除商品分类（软删除）
     *
     * @param id 分类ID
     * @return API响应
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除商品分类", description = "根据分类ID进行软删除，将is_deleted设置为1")
    public ApiResponseDTO<Boolean> deleteCategory(@Parameter(description = "分类ID") @PathVariable("id") Integer id) {
        try {
            // 验证ID参数
            if (id == null || id <= 0) {
                return ApiResponseDTO.error(400, "分类ID不能为空且必须大于0");
            }

            // 执行删除操作
            boolean result = productCategoryService.deleteCategory(id);

            if (result) {
                return ApiResponseDTO.success(true);
            } else {
                return ApiResponseDTO.error(404, "分类不存在");
            }
        } catch (Exception e) {
            log.error("删除分类失败: {}", e.getMessage(), e);
            throw e;
        }
    }
}

