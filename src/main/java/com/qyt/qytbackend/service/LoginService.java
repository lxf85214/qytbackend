package com.qyt.qytbackend.service;

import com.qyt.qytbackend.dto.LoginResponse;

/**
 * 登录服务接口
 */
public interface LoginService {

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @return 操作结果
     */
    boolean sendSms(String phone);

    /**
     * 登录并注册
     * @param phone 手机号
     * @param code 验证码
     * @return 登录响应
     */
    LoginResponse login(String phone, String code);

    /**
     * 登出
     * @param userId 用户ID
     * @return 操作结果
     */
    boolean logout(Long userId);
}