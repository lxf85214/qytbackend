package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.service.ProductInfoService;
import com.qyt.qytbackend.dto.ProductPublishRequestDTO;
import com.qyt.qytbackend.dto.ProductPublishResponseDTO;
import com.qyt.qytbackend.dto.ApiResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品信息控制器
 */
@RestController
@RequestMapping("/api/product")
@Tag(name = "商品管理", description = "商品发布、管理相关的API接口")
@Slf4j
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 发布商品
     *
     * @param requestDTO 商品发布请求DTO
     * @return API响应
     */
    @PostMapping("/publish")
    @Operation(summary = "发布商品", description = "发布新商品，默认状态为下架")
    public ApiResponseDTO<ProductPublishResponseDTO> publishProduct(
            @Parameter(description = "商品发布请求参数") @RequestBody ProductPublishRequestDTO requestDTO) {
        try {
            // 验证请求参数
            if (requestDTO.getProductName() == null || requestDTO.getProductName().trim().isEmpty()) {
                return ApiResponseDTO.error(400, "商品名称不能为空");
            }
            if (requestDTO.getCategoryFirstId() == null) {
                return ApiResponseDTO.error(400, "商品一级分类ID不能为空");
            }
            if (requestDTO.getBrandId() == null) {
                return ApiResponseDTO.error(400, "品牌ID不能为空");
            }
            if (requestDTO.getMainImage() == null || requestDTO.getMainImage().trim().isEmpty()) {
                return ApiResponseDTO.error(400, "主图URL不能为空");
            }
            if (requestDTO.getCreatePin() == null || requestDTO.getCreatePin().trim().isEmpty()) {
                return ApiResponseDTO.error(400, "创建人不能为空");
            }

            // 发布商品
            ProductPublishResponseDTO responseDTO = productInfoService.publishProduct(requestDTO);

            return ApiResponseDTO.success(responseDTO);
        } catch (Exception e) {
            log.error("发布商品失败: {}", e.getMessage(), e);
            return ApiResponseDTO.error(500, "发布商品失败: " + e.getMessage());
        }
    }
}