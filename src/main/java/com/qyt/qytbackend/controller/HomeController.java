package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.common.result.Result;
import com.qyt.qytbackend.service.HomeService;
import com.qyt.qytbackend.dto.ApiResponseDTO;
import com.qyt.qytbackend.dto.HomeRecommendData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页推荐控制器
 */
@RestController
@RequestMapping("/api/home")
@Tag(name = "首页推荐", description = "首页推荐相关的API接口")
@Slf4j
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * 获取首页推荐数据
     *
     * @return API响应
     */
    @GetMapping("/recommend")
    @Operation(summary = "获取首页推荐数据", description = "获取首页推荐数据，包括专区和商品信息")
    public ApiResponseDTO<HomeRecommendData> getHomeRecommendData() {
        log.info("开始获取首页推荐数据");
        
        // 调用服务层方法，已经封装了异常处理
        return homeService.getHomeRecommendData();
    }
}