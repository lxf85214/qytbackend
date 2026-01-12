package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.common.result.Result;
import com.qyt.qytbackend.entity.CustomerInfo;
import com.qyt.qytbackend.entity.CustomerLoginLog;
import com.qyt.qytbackend.entity.UserVerificationCode;
import com.qyt.qytbackend.mapper.CustomerInfoMapper;
import com.qyt.qytbackend.mapper.CustomerLoginLogMapper;
import com.qyt.qytbackend.mapper.UserVerificationCodeMapper;
import com.qyt.qytbackend.rpc.SmsRpc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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

import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Pattern;

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
    private SmsRpc smsRpc;

    @Autowired
    private UserVerificationCodeMapper userVerificationCodeMapper;

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private CustomerLoginLogMapper customerLoginLogMapper;

    // 手机号正则表达式
    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";
    // 验证码有效期（分钟）
    private static final int CODE_EXPIRY_MINUTES = 5;

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

        // 校验手机号合法性
        if (!isValidPhone(phone)) {
            return Result.badRequest("请输入正确手机号");
        }

        // 生成6位验证码
        String code = generateVerificationCode();

        // 调用短信服务发送验证码
        try {
            smsRpc.sendVerificationCode(phone, code);
        } catch (Exception e) {
            log.error("发送短信失败: {}", e.getMessage(), e);
            return Result.error("发送短信失败，请稍后重试");
        }

        // 保存验证码到数据库
        UserVerificationCode verificationCode = new UserVerificationCode();
        verificationCode.setPhoneNumber(phone);
        verificationCode.setVerificationCode(code);
        verificationCode.setCreateTime(LocalDateTime.now());
        verificationCode.setCreateBy("system");
        verificationCode.setUpdateTime(LocalDateTime.now());
        verificationCode.setUpdateBy("system");
        verificationCode.setIsDelete(0);

        userVerificationCodeMapper.insert(verificationCode);

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

        // 校验手机号合法性
        if (!isValidPhone(phone)) {
            return Result.badRequest("请输入正确手机号");
        }

        // 校验验证码
        UserVerificationCode latestCode = userVerificationCodeMapper.selectLatestByPhoneNumber(phone);
        if (latestCode == null) {
            return Result.badRequest("验证码已失效，请重新获取验证码");
        }

        // 检查验证码是否过期
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime codeTime = latestCode.getCreateTime();
        if (codeTime.plusMinutes(CODE_EXPIRY_MINUTES).isBefore(now)) {
            return Result.badRequest("验证码已失效，请重新获取验证码");
        }

        // 检查验证码是否正确
        if (!code.equals(latestCode.getVerificationCode())) {
            return Result.badRequest("验证码错误");
        }

        // 查询用户是否存在
        CustomerInfo customerInfo = customerInfoMapper.selectByPhone(phone);
        Integer userId;

        if (customerInfo != null) {
            // 用户存在，更新登录状态
            customerInfo.setLoginStatus(1);
            customerInfo.setLastLoginTime(now);
            customerInfoMapper.update(customerInfo);
            userId = customerInfo.getUserId();
        } else {
            // 用户不存在，创建新用户
            customerInfo = new CustomerInfo();
            customerInfo.setPhone(phone);
            customerInfo.setLoginStatus(1);
            customerInfo.setLastLoginTime(now);
            customerInfo.setRegisterTime(now);
            customerInfo.setStatus(1); // 正常状态
            customerInfo.setMemberLevel(0); // 普通会员
            customerInfoMapper.insert(customerInfo);
            userId = customerInfo.getUserId();
        }

        // 插入登录日志
        CustomerLoginLog loginLog = new CustomerLoginLog();
        loginLog.setUserId(String.valueOf(userId));
        loginLog.setLoginTime(now);
        loginLog.setLoginIp("127.0.0.1"); // 实际项目中应从请求中获取真实IP
        loginLog.setLoginDevice("unknown"); // 实际项目中应从请求头中获取设备信息
        loginLog.setLoginStatus(1); // 1表示登录成功
        loginLog.setCreatedAt(now);
        loginLog.setUpdatedAt(now);
        customerLoginLogMapper.insert(loginLog);

        // 返回登录成功结果
        LoginResponse response = new LoginResponse();
        response.setUserId(userId);
        response.setLoginStatus(true);

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
        Integer userId = request.getUserId();

        // 校验用户是否存在
        CustomerInfo customerInfo = customerInfoMapper.selectById(userId);
        if (customerInfo == null) {
            return Result.badRequest("非法用户");
        }

        // 更新登录状态为已登出
        customerInfo.setLoginStatus(0);
        customerInfoMapper.update(customerInfo);

        // 查询用户最新的登录成功日志
        CustomerLoginLog latestLoginLog = customerLoginLogMapper.selectLatestByUserId(String.valueOf(userId));
        if (latestLoginLog != null) {
            // 更新登出时间和登录状态
            latestLoginLog.setLogoutTime(LocalDateTime.now());
            latestLoginLog.setLoginStatus(0); // 0表示已登出
            latestLoginLog.setLogoutType("主动登出");
            latestLoginLog.setUpdatedAt(LocalDateTime.now());
            customerLoginLogMapper.update(latestLoginLog);
        }

        return Result.success("登出成功");
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

    // 内部请求响应类
    @Schema(name = "SendSmsRequest", description = "发送短信请求")
    public static class SendSmsRequest {
        @Schema(description = "手机号", example = "13812345678", required = true)
        private String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    @Schema(name = "LoginRequest", description = "登录请求")
    public static class LoginRequest {
        @Schema(description = "手机号", example = "13812345678", required = true)
        private String phone;
        @Schema(description = "验证码", example = "123456", required = true)
        private String code;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    @Schema(name = "LoginResponse", description = "登录响应")
    public static class LoginResponse {
        @Schema(description = "用户ID", example = "1")
        private Integer userId;
        @Schema(description = "登录状态", example = "true")
        private boolean loginStatus;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public boolean isLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(boolean loginStatus) {
            this.loginStatus = loginStatus;
        }
    }

    @Schema(name = "LogoutRequest", description = "登出请求")
    public static class LogoutRequest {
        @Schema(description = "用户ID", example = "1", required = true)
        private Integer userId;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }
    }
}
