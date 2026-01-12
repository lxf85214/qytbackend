package com.qyt.qytbackend.rpc;

import com.qyt.qytbackend.rpc.dto.SmsRequest;
import com.qyt.qytbackend.rpc.dto.SmsResponse;

/**
 * 短信服务RPC接口
 *
 * @author qyt
 * @date 2024
 */
public interface SmsRpc {

    /**
     * 发送短信
     *
     * @param request 短信请求
     * @return 短信响应
     */
    SmsResponse sendSms(SmsRequest request);

    /**
     * 发送验证码短信
     *
     * @param phone 手机号
     * @param code  验证码
     * @return 短信响应
     */
    SmsResponse sendVerificationCode(String phone, String code);

    /**
     * 查询短信发送状态
     *
     * @param messageId 消息ID
     * @return 短信响应
     */
    SmsResponse querySmsStatus(String messageId);
}
