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
     * @return 首页推荐数据
     */
    ApiResponseDTO<HomeRecommendData> getHomeRecommendData();
}