package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.dto.UserAddRequestDTO;
import com.qyt.qytbackend.dto.UserLoginRequestDTO;
import com.qyt.qytbackend.dto.UserLoginResponseDTO;
import com.qyt.qytbackend.dto.UserLogoutRequestDTO;
import com.qyt.qytbackend.dto.ApiResponseDTO;
import com.qyt.qytbackend.entity.UserInfo;
import com.qyt.qytbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户管理控制器
 */
@RestController
@RequestMapping("/api/admin/user")
@Tag(name = "用户管理", description = "PC端运营管理后台用户相关的API接口")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param requestDTO 用户新增请求DTO
     * @return API响应
     */
    @PostMapping("/add")
    @Operation(summary = "新增用户", description = "新增PC端运营管理后台用户")
    public ApiResponseDTO<UserInfo> addUser(@Parameter(description = "用户新增请求参数") @RequestBody UserAddRequestDTO requestDTO) {
        try {
            UserInfo userInfo = userService.addUser(requestDTO);
            return ApiResponseDTO.success(userInfo);
        } catch (Exception e) {
            log.error("新增用户失败: {}", e.getMessage(), e);
            return ApiResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 用户登录
     *
     * @param requestDTO 用户登录请求DTO
     * @return API响应
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "PC端运营管理后台用户登录")
    public ApiResponseDTO<UserLoginResponseDTO> login(@Parameter(description = "用户登录请求参数") @RequestBody UserLoginRequestDTO requestDTO) {
        try {
            // 获取登录IP、设备和浏览器信息
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String loginIp = getClientIp(request);
            String device = request.getHeader("User-Agent");
            String browser = getBrowserInfo(device);

            UserLoginResponseDTO responseDTO = userService.login(requestDTO, loginIp, device, browser);
            return ApiResponseDTO.success(responseDTO);
        } catch (Exception e) {
            log.error("用户登录失败: {}", e.getMessage(), e);
            return ApiResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 用户登出
     *
     * @param requestDTO 用户登出请求DTO
     * @return API响应
     */
    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "PC端运营管理后台用户登出")
    public ApiResponseDTO<Boolean> logout(@Parameter(description = "用户登出请求参数") @RequestBody UserLogoutRequestDTO requestDTO) {
        try {
            boolean result = userService.logout(requestDTO);
            return ApiResponseDTO.success(result);
        } catch (Exception e) {
            log.error("用户登出失败: {}", e.getMessage(), e);
            return ApiResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 获取客户端IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取浏览器信息
     */
    private String getBrowserInfo(String userAgent) {
        if (userAgent == null) {
            return "Unknown";
        }
        if (userAgent.contains("MSIE")) {
            return "Internet Explorer";
        } else if (userAgent.contains("Firefox")) {
            return "Firefox";
        } else if (userAgent.contains("Chrome")) {
            return "Chrome";
        } else if (userAgent.contains("Safari")) {
            return "Safari";
        } else if (userAgent.contains("Opera")) {
            return "Opera";
        } else {
            return "Other";
        }
    }
}
