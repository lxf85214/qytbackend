package com.qyt.qytbackend.common.context;

import com.qyt.qytbackend.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户上下文类
 * 使用ThreadLocal存储当前登录用户的信息
 */
@Slf4j
public class UserContext {

    // ThreadLocal存储用户信息
    private static final ThreadLocal<UserInfo> USER_HOLDER = new ThreadLocal<>();
    
    /**
     * 设置用户信息到ThreadLocal
     * @param userInfo 用户信息
     */
    public static void setUserInfo(UserInfo userInfo) {
        USER_HOLDER.set(userInfo);
    }
    
    /**
     * 从ThreadLocal获取用户信息
     * @return 用户信息
     */
    public static UserInfo getUserInfo() {
        return USER_HOLDER.get();
    }
    
    /**
     * 从ThreadLocal获取用户ID
     * @return 用户ID
     */
    public static Long getUserId() {
        UserInfo userInfo = getUserInfo();
        return userInfo != null ? userInfo.getId() : null;
    }
    
    /**
     * 从ThreadLocal获取用户名
     * @return 用户名
     */
    public static String getUsername() {
        UserInfo userInfo = getUserInfo();
        return userInfo != null ? userInfo.getUsername() : null;
    }
    
    /**
     * 清除ThreadLocal中的用户信息
     */
    public static void clear() {
        try {
            USER_HOLDER.remove();
        } catch (Exception e) {
            log.error("清除用户上下文信息失败: {}", e.getMessage(), e);
        }
    }
}