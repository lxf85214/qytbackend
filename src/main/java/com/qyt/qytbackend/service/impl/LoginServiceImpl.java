package com.qyt.qytbackend.service.impl;

import com.qyt.qytbackend.common.jwt.JwtUtils;
import com.qyt.qytbackend.dto.LoginResponse;
import com.qyt.qytbackend.entity.CustomerInfo;
import com.qyt.qytbackend.entity.CustomerLoginLog;
import com.qyt.qytbackend.entity.CustomerVerificationCode;
import com.qyt.qytbackend.mapper.CustomerInfoMapper;
import com.qyt.qytbackend.mapper.CustomerLoginLogMapper;
import com.qyt.qytbackend.mapper.CustomerVerificationCodeMapper;
import com.qyt.qytbackend.rpc.SmsRpc;
import com.qyt.qytbackend.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 登录服务实现类
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SmsRpc smsRpc;

    @Autowired
    private CustomerVerificationCodeMapper customerVerificationCodeMapper;

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private CustomerLoginLogMapper customerLoginLogMapper;

    @Autowired
    private JwtUtils jwtUtils;

    // 手机号正则表达式
    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";
    // 验证码有效期（分钟）
    private static final int CODE_EXPIRY_MINUTES = 5;

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @return 操作结果
     */
    @Override
    public boolean sendSms(String phone) {
        // 校验手机号合法性
        if (!isValidPhone(phone)) {
            return false;
        }

        // 生成6位验证码
        String code = generateVerificationCode();

        // 调用短信服务发送验证码
        try {
            smsRpc.sendVerificationCode(phone, code);
        } catch (Exception e) {
            log.error("发送短信失败: {}", e.getMessage(), e);
            return false;
        }

        // 保存验证码到数据库
        CustomerVerificationCode verificationCode = new CustomerVerificationCode();
        verificationCode.setPhoneNumber(phone);
        verificationCode.setVerificationCode(code);
        verificationCode.setCreateTime(LocalDateTime.now());
        verificationCode.setCreatePin("system");
        verificationCode.setUpdateTime(LocalDateTime.now());
        verificationCode.setUpdatePin("system");
        verificationCode.setIsDeleted(0);

        customerVerificationCodeMapper.insert(verificationCode);

        return true;
    }

    /**
     * 登录并注册
     * @param phone 手机号
     * @param code 验证码
     * @return 登录响应
     */
    @Override
    public LoginResponse login(String phone, String code) {
        // 校验手机号合法性
        if (!isValidPhone(phone)) {
            return null;
        }

        // 校验验证码
        CustomerVerificationCode latestCode = customerVerificationCodeMapper.selectLatestByPhoneNumber(phone);
        if (latestCode == null) {
            return null;
        }

        // 检查验证码是否过期
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime codeTime = latestCode.getCreateTime();
        if (codeTime.plusMinutes(CODE_EXPIRY_MINUTES).isBefore(now)) {
            return null;
        }

        // 检查验证码是否正确
        if (!code.equals(latestCode.getVerificationCode())) {
            return null;
        }

        // 查询用户是否存在
        CustomerInfo customerInfo = customerInfoMapper.selectByPhone(phone);
        Long userId;

        if (customerInfo != null) {
            // 用户存在，更新登录状态
            customerInfo.setLoginStatus(1);
            customerInfo.setLastLoginTime(now);
            customerInfo.setUpdatePin("system");
            customerInfo.setUpdateTime(now);
            customerInfoMapper.update(customerInfo);
            userId = customerInfo.getId();
        } else {
            // 用户不存在，创建新用户
            customerInfo = new CustomerInfo();
            customerInfo.setPhone(phone);
            customerInfo.setLoginStatus(1);
            customerInfo.setLastLoginTime(now);
            customerInfo.setRegisterTime(now);
            customerInfo.setStatus(1); // 正常状态
            customerInfo.setMemberLevel(0); // 普通会员
            customerInfo.setCreatePin("system");
            customerInfo.setCreateTime(now);
            customerInfo.setUpdatePin("system");
            customerInfo.setUpdateTime(now);
            customerInfo.setIsDeleted(0); // 未删除
            customerInfoMapper.insert(customerInfo);
            userId = customerInfo.getId();
        }

        // 插入登录日志
        CustomerLoginLog loginLog = new CustomerLoginLog();
        loginLog.setUserId(userId);
        loginLog.setLoginTime(now);
        loginLog.setLoginIp("127.0.0.1"); // 实际项目中应从请求中获取真实IP
        loginLog.setLoginDevice("unknown"); // 实际项目中应从请求头中获取设备信息
        loginLog.setLoginStatus(1); // 1表示登录成功
        loginLog.setCreatePin("system");
        loginLog.setCreateTime(now);
        loginLog.setUpdatePin("system");
        loginLog.setUpdateTime(now);
        loginLog.setIsDeleted(0); // 未删除
        customerLoginLogMapper.insert(loginLog);

        // 生成JWT token
        String token = jwtUtils.generateToken(userId);

        // 构建登录响应
        LoginResponse response = new LoginResponse();
        response.setUserId(userId);
        response.setLoginStatus(true);
        response.setToken(token);

        return response;
    }

    /**
     * 登出
     * @param userId 用户ID
     * @return 操作结果
     */
    @Override
    public boolean logout(Long userId) {
        // 校验用户是否存在
        CustomerInfo customerInfo = customerInfoMapper.selectById(userId);
        if (customerInfo == null) {
            return false;
        }

        // 更新登录状态为已登出
        customerInfo.setLoginStatus(0);
        customerInfo.setUpdatePin("system");
        customerInfo.setUpdateTime(LocalDateTime.now());
        customerInfoMapper.update(customerInfo);

        // 查询用户最新的登录成功日志
        CustomerLoginLog latestLoginLog = customerLoginLogMapper.selectLatestByUserId(userId);
        if (latestLoginLog != null) {
            // 更新登出时间和登录状态
            latestLoginLog.setLogoutTime(LocalDateTime.now());
            latestLoginLog.setLoginStatus(0); // 0表示已登出
            latestLoginLog.setLogoutType("主动登出");
            latestLoginLog.setUpdatePin("system");
            latestLoginLog.setUpdateTime(LocalDateTime.now());
            customerLoginLogMapper.update(latestLoginLog);
        }

        return true;
    }

    /**
     * 验证手机号合法性
     */
    private boolean isValidPhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }
        return Pattern.matches(PHONE_REGEX, phone);
    }

    /**
     * 生成6位验证码
     */
    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}