package com.qyt.qytbackend.service;

import com.qyt.qytbackend.entity.ProductInfo;
import com.qyt.qytbackend.common.result.PageResult;
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

    /**
     * 分页查询商品列表
     *
     * @param pageNum        页码，默认1
     * @param pageSize       每页数量，默认10
     * @param thirdCategoryId 三级分类ID，非必传
     * @return 分页查询结果
     */
    PageResult<ProductInfo> getProductPage(Integer pageNum, Integer pageSize, Integer thirdCategoryId);
}