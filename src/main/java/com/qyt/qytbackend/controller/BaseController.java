package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.common.result.Result;
import com.qyt.qytbackend.util.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 基础控制器类
 * 提供通用的控制器方法
 *
 * @author qyt
 * @date 2024
 */
public class BaseController {

    /**
     * 健康检查接口
     */
    @GetMapping("/health")
    @Operation(summary = "健康检查接口", description = "检查服务是否正常运行")
    public Result<String> health() {
        return Result.success("服务运行正常");
    }

    /**
     * 获取分页参数
     */
    protected Integer getPage(@RequestParam(required = false) Integer page) {
        return PageUtil.getPage(page);
    }

    /**
     * 获取每页数量
     */
    protected Integer getSize(@RequestParam(required = false) Integer size) {
        return PageUtil.getSize(size);
    }
}
