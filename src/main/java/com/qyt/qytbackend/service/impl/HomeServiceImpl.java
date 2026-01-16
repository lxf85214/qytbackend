package com.qyt.qytbackend.service.impl;

import com.qyt.qytbackend.dto.ApiResponseDTO;
import com.qyt.qytbackend.dto.HomeRecommendData;
import com.qyt.qytbackend.dto.ZoneData;
import com.qyt.qytbackend.dto.ZoneProductData;
import com.qyt.qytbackend.entity.*;
import com.qyt.qytbackend.mapper.*;
import com.qyt.qytbackend.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 首页推荐服务实现类
 */
@Service
@Slf4j
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ZoneConfigMapper zoneConfigMapper;

    @Autowired
    private ZoneProductGroupRelationMapper zoneProductGroupRelationMapper;

    @Autowired
    private ZoneProductGroupMapper zoneProductGroupMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public ApiResponseDTO<HomeRecommendData> getHomeRecommendData(Integer queryType) {
        try {
            log.info("开始获取首页推荐数据，queryType={}", queryType);

            // 1. 查询所有推荐专区
            List<ZoneConfig> allZones = zoneConfigMapper.selectHomeRecommendZones(queryType);

            // 2. 按display_position分组
            Map<Integer, List<ZoneConfig>> zoneByPosition = allZones.stream()
                    .collect(Collectors.groupingBy(ZoneConfig::getDisplayPosition));

            // 3. 处理display_position=0的专区（第一行专区）
            List<ZoneData> firstRowZones = new ArrayList<>();
            List<ZoneConfig> position0List = zoneByPosition.getOrDefault(0, Collections.emptyList());
            for (ZoneConfig zone : position0List) {
                ZoneData zoneData = new ZoneData();
                zoneData.setZoneName(zone.getZoneName());
                zoneData.setDefaultImage(zone.getDefaultImage());
                firstRowZones.add(zoneData);
            }

            // 4. 处理display_position=1的专区（其他行专区）
            List<ZoneProductData> otherRowZones = new ArrayList<>();
            List<ZoneConfig> position1List = zoneByPosition.getOrDefault(1, Collections.emptyList());

            // 按display_product分组
            Map<Integer, List<ZoneConfig>> zoneByDisplayProduct = position1List.stream()
                    .collect(Collectors.groupingBy(ZoneConfig::getDisplayProduct));

            // 处理display_product=0的专区（不显示商品）
            List<ZoneConfig> displayProduct0List = zoneByDisplayProduct.getOrDefault(0, Collections.emptyList());
            for (ZoneConfig zone : displayProduct0List) {
                ZoneProductData zoneProductData = new ZoneProductData();
                zoneProductData.setZoneName(zone.getZoneName());
                zoneProductData.setProducts(Collections.emptyList());
                otherRowZones.add(zoneProductData);
            }

            // 处理display_product=1的专区（显示商品）
            List<ZoneConfig> displayProduct1List = zoneByDisplayProduct.getOrDefault(1, Collections.emptyList());
            if (!displayProduct1List.isEmpty()) {
                // 获取所有需要查询商品的专区ID
                List<Long> zoneIds = displayProduct1List.stream()
                        .map(ZoneConfig::getId)
                        .toList();

                // 查询所有专区对应的商品组关系
                List<ZoneProductGroupRelation> allRelations = new ArrayList<>();
                for (Long zoneId : zoneIds) {
                    allRelations.addAll(zoneProductGroupRelationMapper.selectByZoneId(zoneId));
                }

                // 获取所有商品组ID
                List<Long> groupIds = allRelations.stream()
                        .map(ZoneProductGroupRelation::getGroupId)
                        .distinct()
                        .toList();

                // 查询所有商品组下的商品
                List<ZoneProductGroup> allProductsInGroups = new ArrayList<>();
                for (Long groupId : groupIds) {
                    allProductsInGroups.addAll(zoneProductGroupMapper.selectByGroupId(groupId));
                }

                // 获取所有商品ID
                List<Long> productIds = allProductsInGroups.stream()
                        .map(ZoneProductGroup::getProductId)
                        .distinct()
                        .toList();

                // 批量查询商品信息
                List<ProductInfo> allProducts = new ArrayList<>();
                if (!productIds.isEmpty()) {
                    try {
                        // 将Long类型的商品ID转换为Integer类型
                        List<Integer> intProductIds = productIds.stream()
                                .map(Long::intValue)
                                .collect(Collectors.toList());
                        
                        // 使用批量查询方法
                        allProducts = productInfoMapper.selectByIds(intProductIds);
                    } catch (Exception e) {
                        log.error("批量查询商品失败: {}", e.getMessage(), e);
                    }
                }

                // 构建专区ID到商品组关系的映射
                Map<Long, List<ZoneProductGroupRelation>> zoneIdToRelations = allRelations.stream()
                        .collect(Collectors.groupingBy(ZoneProductGroupRelation::getZoneId));

                // 构建商品组ID到商品的映射
                Map<Long, List<ZoneProductGroup>> groupIdToProducts = allProductsInGroups.stream()
                        .collect(Collectors.groupingBy(ZoneProductGroup::getId));

                // 构建商品ID到商品信息的映射
                Map<Long, ProductInfo> productIdToProduct = allProducts.stream()
                        .collect(Collectors.toMap(ProductInfo::getId, product -> product));

                // 组装每个专区的商品信息
                for (ZoneConfig zone : displayProduct1List) {
                    ZoneProductData zoneProductData = new ZoneProductData();
                    zoneProductData.setZoneName(zone.getZoneName());
                    
                    List<ProductInfo> zoneProducts = new ArrayList<>();

                    // 获取当前专区的所有商品组关系
                    List<ZoneProductGroupRelation> relations = zoneIdToRelations.getOrDefault(zone.getId(), Collections.emptyList());

                    // 获取所有商品
                    for (ZoneProductGroupRelation relation : relations) {
                        List<ZoneProductGroup> productsInGroup = groupIdToProducts.getOrDefault(relation.getGroupId(), Collections.emptyList());
                        for (ZoneProductGroup productInGroup : productsInGroup) {
                            ProductInfo product = productIdToProduct.get(productInGroup.getProductId());
                            if (product != null) {
                                zoneProducts.add(product);
                            }
                        }
                    }

                    zoneProductData.setProducts(zoneProducts);
                    otherRowZones.add(zoneProductData);
                }
            }

            // 5. 组装返回结果
            HomeRecommendData homeRecommendData = new HomeRecommendData();
            homeRecommendData.setFirstRowZones(firstRowZones);
            homeRecommendData.setOtherRowZones(otherRowZones);

            log.info("获取首页推荐数据完成");
            return ApiResponseDTO.success(homeRecommendData);
        } catch (Exception e) {
            log.error("获取首页推荐数据失败: {}", e.getMessage(), e);
            return ApiResponseDTO.error(500, "获取首页推荐数据失败: " + e.getMessage());
        }
    }
}