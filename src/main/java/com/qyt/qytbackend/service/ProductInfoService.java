package com.qyt.qytbackend.service;

import com.qyt.qytbackend.entity.ProductInfo;
import com.qyt.qytbackend.dto.ProductPublishRequestDTO;
import com.qyt.qytbackend.dto.ProductPublishResponseDTO;

/**
 * 商品信息服务接口
 */
public interface ProductInfoService {
    /**
     * 发布商品
     *
     * @param requestDTO 商品发布请求DTO
     * @return 商品发布响应DTO
     */
    ProductPublishResponseDTO publishProduct(ProductPublishRequestDTO requestDTO);
}