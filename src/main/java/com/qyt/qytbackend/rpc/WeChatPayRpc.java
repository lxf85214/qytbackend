package com.qyt.qytbackend.rpc;

import com.qyt.qytbackend.rpc.dto.WeChatPayRequest;
import com.qyt.qytbackend.rpc.dto.WeChatPayResponse;

/**
 * 微信支付RPC接口
 *
 * @author qyt
 * @date 2024
 */
public interface WeChatPayRpc {

    /**
     * 创建支付订单
     *
     * @param request 支付请求
     * @return 支付响应
     */
    WeChatPayResponse createPayment(WeChatPayRequest request);

    /**
     * 查询支付订单状态
     *
     * @param orderNo 订单号
     * @return 支付响应
     */
    WeChatPayResponse queryPayment(String orderNo);

    /**
     * 关闭支付订单
     *
     * @param orderNo 订单号
     * @return 是否成功
     */
    Boolean closePayment(String orderNo);

    /**
     * 处理支付回调
     *
     * @param notifyData 回调数据
     * @return 是否处理成功
     */
    Boolean handleNotify(String notifyData);
}
