package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World控制器
 *
 * @author qyt
 * @date 2024
 */
@RestController
@RequestMapping("/api/v1")
public class HelloController extends BaseController {

    /**
     * Hello World接口
     *
     * @return HelloWorld
     */
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("HelloWorld");
    }
}
