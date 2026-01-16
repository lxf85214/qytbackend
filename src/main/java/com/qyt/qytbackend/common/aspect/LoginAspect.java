package com.qyt.qytbackend.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qyt.qytbackend.common.annotation.LoginRequired;
import com.qyt.qytbackend.common.context.UserContext;
import com.qyt.qytbackend.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 登录校验切面
 * 用于处理使用了@LoginRequired注解的Controller方法
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginAspect {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    
    // 定义切点：使用@LoginRequired注解的方法
    @Pointcut("@annotation(com.qyt.qytbackend.common.annotation.LoginRequired)")
    public void loginPointcut() {
    }
    
    /**
     * 方法执行前的处理
     * @param joinPoint 连接点
     */
    @Before("loginPointcut()")
    public void beforeMethod(JoinPoint joinPoint) throws Exception {
        // 获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("获取请求上下文失败");
            throw new Exception("获取请求上下文失败");
        }
        
        HttpServletRequest request = attributes.getRequest();
        
        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            log.error("请求头中缺少Authorization token");
            throw new Exception("请先登录");
        }
        
        // 从Redis中获取用户信息
        String userInfoJson = redisTemplate.opsForValue().get(token);
        if (userInfoJson == null || userInfoJson.isEmpty()) {
            log.error("token无效或已过期: {}", token);
            throw new Exception("登录已过期，请重新登录");
        }
        
        // 将JSON字符串反序列化为UserInfo对象
        UserInfo userInfo;
        try {
            userInfo = objectMapper.readValue(userInfoJson, UserInfo.class);
        } catch (Exception e) {
            log.error("反序列化用户信息失败: {}", e.getMessage(), e);
            throw new Exception("用户信息解析失败");
        }
        
        // 将用户信息存入ThreadLocal
        UserContext.setUserInfo(userInfo);
        log.debug("用户登录信息已存入ThreadLocal: 用户ID={}, 用户名={}", userInfo.getId(), userInfo.getUsername());
    }
    
    /**
     * 方法正常返回后的处理
     */
    @AfterReturning("loginPointcut()")
    public void afterReturning() {
        // 清除ThreadLocal中的用户信息
        UserContext.clear();
        log.debug("已清除ThreadLocal中的用户信息");
    }
    
    /**
     * 方法抛出异常后的处理
     */
    @AfterThrowing("loginPointcut()")
    public void afterThrowing() {
        // 清除ThreadLocal中的用户信息
        UserContext.clear();
        log.debug("已清除ThreadLocal中的用户信息");
    }
}