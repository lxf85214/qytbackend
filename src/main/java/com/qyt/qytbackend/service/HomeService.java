package com.qyt.qytbackend.service;

import com.qyt.qytbackend.dto.ApiResponseDTO;
import com.qyt.qytbackend.dto.HomeRecommendData;

/**
 * 首页推荐服务接口
 */
public interface HomeService {
    /**
     * 获取首页推荐数据
     *
     * @param queryType 查询类型，非必输，不传时默认为1，对应匹配zone_config表中item_type字段
     * @return 首页推荐数据
     */
    ApiResponseDTO<HomeRecommendData> getHomeRecommendData(Integer queryType);
}