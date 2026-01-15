package com.qyt.qytbackend.service;

import com.qyt.qytbackend.dto.UserAddRequestDTO;
import com.qyt.qytbackend.dto.UserLoginRequestDTO;
import com.qyt.qytbackend.dto.UserLoginResponseDTO;
import com.qyt.qytbackend.dto.UserLogoutRequestDTO;
import com.qyt.qytbackend.entity.UserInfo;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 新增用户
     * @param requestDTO 用户新增请求DTO
     * @return 用户信息
     * @throws Exception 异常
     */
    UserInfo addUser(UserAddRequestDTO requestDTO) throws Exception;

    /**
     * 用户登录
     * @param requestDTO 用户登录请求DTO
     * @param loginIp 登录IP
     * @param device 登录设备
     * @param browser 浏览器
     * @return 登录响应DTO
     * @throws Exception 异常
     */
    UserLoginResponseDTO login(UserLoginRequestDTO requestDTO, String loginIp, String device, String browser) throws Exception;

    /**
     * 用户登出
     * @param requestDTO 用户登出请求DTO
     * @return 登出结果
     * @throws Exception 异常
     */
    boolean logout(UserLogoutRequestDTO requestDTO) throws Exception;
}
