package com.qyt.qytbackend.rpc.impl;

import com.qyt.qytbackend.exception.BusinessException;
import com.qyt.qytbackend.rpc.SmsRpc;
import com.qyt.qytbackend.rpc.dto.SmsRequest;
import com.qyt.qytbackend.rpc.dto.SmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 短信服务RPC实现类
 *
 * @author qyt
 * @date 2024
 */
@Slf4j
@Component
public class SmsRpcImpl implements SmsRpc {

    @Value("${sms.provider:}")
    private String smsProvider;

    @Value("${sms.access-key-id:}")
    private String accessKeyId;

    @Value("${sms.access-key-secret:}")
    private String accessKeySecret;

    @Value("${sms.sign-name:}")
    private String signName;

    @Value("${sms.verification-code-template:}")
    private String verificationCodeTemplate;

    @Override
    public SmsResponse sendSms(SmsRequest request) {
        log.info("发送短信，手机号: {}, 模板ID: {}", request.getPhone(), request.getTemplateId());

        try {
            // TODO: 根据配置的短信服务商实现具体调用
            // 支持阿里云、腾讯云、华为云等
            // 1. 根据sms.provider选择对应的客户端
            // 2. 构建请求参数
            // 3. 调用短信服务接口
            // 4. 处理返回结果

            SmsResponse response = new SmsResponse();
            response.setSuccess(true);
            response.setMessageId("message_id_example");

            log.info("短信发送成功，手机号: {}, 消息ID: {}", request.getPhone(), response.getMessageId());
            return response;

        } catch (Exception e) {
            log.error("短信发送失败，手机号: {}", request.getPhone(), e);
            SmsResponse response = new SmsResponse();
            response.setSuccess(false);
            response.setErrorCode("SMS_ERROR");
            response.setErrorMessage("短信发送失败: " + e.getMessage());
            throw new BusinessException("短信发送失败", e);
        }
    }

    @Override
    public SmsResponse sendVerificationCode(String phone, String code) {
        log.info("发送验证码短信，手机号: {}", phone);

        SmsRequest request = new SmsRequest();
        request.setPhone(phone);
        request.setTemplateId(verificationCodeTemplate);
        request.setTemplateParams("{\"code\":\"" + code + "\"}");

        return sendSms(request);
    }

    @Override
    public SmsResponse querySmsStatus(String messageId) {
        log.info("查询短信发送状态，消息ID: {}", messageId);

        try {
            // TODO: 实现短信状态查询接口调用

            SmsResponse response = new SmsResponse();
            response.setSuccess(true);

            log.info("查询短信发送状态成功，消息ID: {}", messageId);
            return response;

        } catch (Exception e) {
            log.error("查询短信发送状态失败，消息ID: {}", messageId, e);
            SmsResponse response = new SmsResponse();
            response.setSuccess(false);
            response.setErrorCode("QUERY_ERROR");
            response.setErrorMessage("查询短信状态失败: " + e.getMessage());
            throw new BusinessException("查询短信状态失败", e);
        }
    }
}
