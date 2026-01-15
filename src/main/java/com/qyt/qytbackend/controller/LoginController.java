package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.common.result.Result;
import com.qyt.qytbackend.dto.LoginRequest;
import com.qyt.qytbackend.dto.LoginResponse;
import com.qyt.qytbackend.dto.LogoutRequest;
import com.qyt.qytbackend.dto.SendSmsRequest;
import com.qyt.qytbackend.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录登出控制器
 *
 * @author qyt
 * @date 2024
 */
@Tag(name = "登录登出相关接口", description = "提供发送短信验证码、登录注册、登出等功能")
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    /**
     * 发送短信验证码
     */
    @Operation(summary = "发送短信验证码", description = "根据手机号发送6位验证码，验证码有效期5分钟")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "验证码发送成功"),
            @ApiResponse(responseCode = "400", description = "请输入正确手机号"),
            @ApiResponse(responseCode = "500", description = "发送短信失败，请稍后重试")
    })
    @PostMapping("/send-sms")
    public Result<String> sendSms(@Parameter(description = "发送短信请求", required = true, schema = @Schema(implementation = SendSmsRequest.class)) @RequestBody SendSmsRequest request) {
        String phone = request.getPhone();

        // 调用服务层发送短信
        boolean result = loginService.sendSms(phone);
        if (!result) {
            return Result.badRequest("请输入正确手机号");
        }

        return Result.success("验证码发送成功");
    }

    /**
     * 登录并注册接口
     */
    @Operation(summary = "登录并注册", description = "使用手机号和验证码登录，若用户不存在则自动注册")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "400", description = "验证码已失效或手机号格式错误")
    })
    @PostMapping("/login")
    public Result<LoginResponse> login(@Parameter(description = "登录请求", required = true, schema = @Schema(implementation = LoginRequest.class)) @RequestBody LoginRequest request) {
        String phone = request.getPhone();
        String code = request.getCode();

        // 调用服务层登录
        LoginResponse response = loginService.login(phone, code);
        if (response == null) {
            return Result.badRequest("验证码已失效或手机号格式错误");
        }

        return Result.success("登录成功", response);
    }

    /**
     * 登出接口
     */
    @Operation(summary = "登出", description = "根据用户id登出用户")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "登出成功"),
            @ApiResponse(responseCode = "400", description = "非法用户")
    })
    @PostMapping("/logout")
    public Result<String> logout(@Parameter(description = "登出请求", required = true, schema = @Schema(implementation = LogoutRequest.class)) @RequestBody LogoutRequest request) {
        Long userId = request.getUserId();

        // 调用服务层登出
        boolean result = loginService.logout(userId);
        if (!result) {
            return Result.badRequest("非法用户");
        }

        return Result.success("登出成功");
    }
}
