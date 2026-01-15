package com.qyt.qytbackend.service.impl;

import com.qyt.qytbackend.entity.ProductInfo;
import com.qyt.qytbackend.mapper.ProductInfoMapper;
import com.qyt.qytbackend.service.ProductInfoService;
import com.qyt.qytbackend.dto.ProductPublishRequestDTO;
import com.qyt.qytbackend.dto.ProductPublishResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 商品信息服务实现类
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 发布商品
     *
     * @param requestDTO 商品发布请求DTO
     * @return 商品发布响应DTO
     */
    @Override
    public ProductPublishResponseDTO publishProduct(ProductPublishRequestDTO requestDTO) {
        try {
            log.info("开始发布商品: {}", requestDTO.getProductName());

            // 创建商品实体
            ProductInfo productInfo = new ProductInfo();

            // 设置商品基本信息
            productInfo.setProductName(requestDTO.getProductName());
            productInfo.setDistrict(requestDTO.getDistrict());

            // 设置分类信息
            productInfo.setCategoryFirstId(requestDTO.getCategoryFirstId());
            productInfo.setCategorySecondId(requestDTO.getCategorySecondId());
            productInfo.setCategoryThirdId(requestDTO.getCategoryThirdId());

            // 设置品牌信息
            productInfo.setBrandId(requestDTO.getBrandId());

            // 设置图片信息（OSS路径）
            productInfo.setMainImage(requestDTO.getMainImage());
            productInfo.setThumbnailImage(requestDTO.getThumbnailImage());
            productInfo.setListingImage(requestDTO.getListingImage());
            productInfo.setDetailImages(requestDTO.getDetailImages());

            // 设置商品描述和规格
            productInfo.setDescription(requestDTO.getDescription());
            productInfo.setSpecifications(requestDTO.getSpecifications());

            // 设置状态为0-下架
            productInfo.setStatus(0);

            // 设置创建人和更新人
            productInfo.setCreatePin(requestDTO.getCreatePin());
            productInfo.setUpdatePin(requestDTO.getCreatePin());

            // 插入商品信息
            int result = productInfoMapper.insert(productInfo);
            log.info("发布商品结果: {}", result > 0 ? "成功" : "失败");

            if (result > 0) {
                // 构建响应
                ProductPublishResponseDTO responseDTO = new ProductPublishResponseDTO();
                responseDTO.setId(productInfo.getId());
                responseDTO.setProductName(productInfo.getProductName());
                responseDTO.setStatus(productInfo.getStatus());
                return responseDTO;
            } else {
                throw new RuntimeException("发布商品失败");
            }
        } catch (Exception e) {
            log.error("发布商品失败: {}", e.getMessage(), e);
            throw e;
        }
    }
}