package com.qyt.qytbackend.service.impl;

import com.qyt.qytbackend.common.jwt.JwtUtils;
import com.qyt.qytbackend.dto.UserAddRequestDTO;
import com.qyt.qytbackend.dto.UserLoginRequestDTO;
import com.qyt.qytbackend.dto.UserLoginResponseDTO;
import com.qyt.qytbackend.dto.UserLogoutRequestDTO;
import com.qyt.qytbackend.entity.UserInfo;
import com.qyt.qytbackend.entity.UserLoginLog;
import com.qyt.qytbackend.mapper.UserInfoMapper;
import com.qyt.qytbackend.mapper.UserLoginLogMapper;
import com.qyt.qytbackend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final long TOKEN_EXPIRE_DAYS = 30;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserInfo addUser(UserAddRequestDTO requestDTO) throws Exception {
        try {
            // 检查用户名是否已存在
            UserInfo existingUser = userInfoMapper.selectByUsername(requestDTO.getUsername());
            if (existingUser != null) {
                throw new Exception("用户名已存在");
            }

            // 检查手机号是否已存在
            UserInfo existingPhoneUser = userInfoMapper.selectByPhone(requestDTO.getPhone());
            if (existingPhoneUser != null) {
                throw new Exception("手机号已被注册");
            }

            // 创建用户对象
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(requestDTO.getUsername());
            userInfo.setPassword(requestDTO.getPassword()); // 直接使用前端加密后的密码
            userInfo.setPhone(requestDTO.getPhone());
            userInfo.setEmail(requestDTO.getEmail());
            userInfo.setRealName(requestDTO.getRealName());
            userInfo.setIdCard(requestDTO.getIdCard());
            userInfo.setAvatar(requestDTO.getAvatar());
            userInfo.setUserType(requestDTO.getUserType());
            userInfo.setStatus(requestDTO.getStatus());
            userInfo.setLoginStatus(0); // 初始状态为未登录
            userInfo.setRegisterTime(LocalDateTime.now());
            userInfo.setCreatePin(requestDTO.getCreatePin() != null ? requestDTO.getCreatePin() : "system");
            userInfo.setUpdatePin(requestDTO.getCreatePin() != null ? requestDTO.getCreatePin() : "system");

            // 插入数据库
            int result = userInfoMapper.insert(userInfo);
            if (result <= 0) {
                throw new Exception("用户创建失败");
            }

            return userInfo;
        } catch (Exception e) {
            log.error("新增用户失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO requestDTO, String loginIp, String device, String browser) throws Exception {
        try {
            // 根据用户名查询用户
            UserInfo userInfo = userInfoMapper.selectByUsername(requestDTO.getUsername());
            if (userInfo == null) {
                // 记录登录失败日志
                recordLoginLog(requestDTO.getUsername(), null, loginIp, 0, "用户不存在", device, browser);
                throw new Exception("用户名或密码错误");
            }

            // 检查账号状态
            if (userInfo.getStatus() == 0) {
                // 记录登录失败日志
                recordLoginLog(requestDTO.getUsername(), userInfo.getId(), loginIp, 0, "账号已禁用", device, browser);
                throw new Exception("账号已禁用");
            }

            // 密码比对（前端加密后传输，直接比对密文）
            if (!userInfo.getPassword().equals(requestDTO.getPassword())) {
                // 记录登录失败日志
                recordLoginLog(requestDTO.getUsername(), userInfo.getId(), loginIp, 0, "密码错误", device, browser);
                throw new Exception("用户名或密码错误");
            }

            // 生成token
            String token = jwtUtils.generateToken(userInfo.getId());

            // 将token缓存到redis中，有效期30天
            String userInfoJson = objectMapper.writeValueAsString(userInfo);
            redisTemplate.opsForValue().set(token, userInfoJson, TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);

            // 更新用户登录状态和最后登录时间
            userInfo.setLoginStatus(1);
            userInfo.setLastLoginTime(LocalDateTime.now());
            userInfo.setUpdatePin(userInfo.getUsername());
            userInfoMapper.update(userInfo);

            // 记录登录成功日志
            recordLoginLog(requestDTO.getUsername(), userInfo.getId(), loginIp, 1, null, device, browser);

            // 构建响应
            UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
            responseDTO.setToken(token);
            responseDTO.setUserInfo(userInfo);

            return responseDTO;
        } catch (Exception e) {
            log.error("用户登录失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public boolean logout(UserLogoutRequestDTO requestDTO) throws Exception {
        try {
            UserInfo userInfo = null;

            // 根据用户名或用户ID查询用户
            if (requestDTO.getUserId() != null) {
                userInfo = userInfoMapper.selectById(requestDTO.getUserId());
            } else if (requestDTO.getUsername() != null) {
                userInfo = userInfoMapper.selectByUsername(requestDTO.getUsername());
            }

            if (userInfo == null) {
                throw new Exception("用户不存在");
            }

            // 检查登录状态
            if (userInfo.getLoginStatus() == 0) {
                throw new Exception("用户未登录");
            }

            // 从redis中删除token（这里简化处理，实际应该从请求头或参数中获取token）
            // 注意：实际实现中，应该从请求中获取token并删除
            // redisTemplate.delete(token);

            // 更新用户登录状态
            userInfo.setLoginStatus(0);
            userInfo.setUpdatePin(userInfo.getUsername());
            userInfoMapper.update(userInfo);

            return true;
        } catch (Exception e) {
            log.error("用户登出失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 记录登录日志
     */
    private void recordLoginLog(String username, Long userId, String loginIp, Integer loginStatus, String failReason, String device, String browser) {
        try {
            UserLoginLog loginLog = new UserLoginLog();
            loginLog.setUserId(userId);
            loginLog.setUsername(username);
            loginLog.setLoginTime(LocalDateTime.now());
            loginLog.setLoginIp(loginIp);
            loginLog.setLoginStatus(loginStatus);
            loginLog.setFailReason(failReason);
            loginLog.setDevice(device);
            loginLog.setBrowser(browser);
            userLoginLogMapper.insert(loginLog);
        } catch (Exception e) {
            log.error("记录登录日志失败: {}", e.getMessage(), e);
        }
    }
}
