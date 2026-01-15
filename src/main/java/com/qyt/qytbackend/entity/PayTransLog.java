package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class PayTransLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    private Long orderId;
    /**
     * 子单号
     */
    private Long subOrderId;
    /**
     * 三方支付流水号：微信/支付宝返回的流水号
     */
    private Long thirdSeqNo;
    /**
     * 类型：1-支付0-退款
     */
    private Integer payType;
    /**
     * 支付金额：支付总金额
     */
    private BigDecimal payAmount;
    /**
     * 现金金额：现金支付金额
     */
    private BigDecimal cashAmount;
    /**
     * 优惠金额：优惠金额(金额单位：分/积分数)
     */
    private Long discountAmount;
    /**
     * 支付状态：10-待支付、20-支付成功，30-支付失败，40-退款中，50-已退款，60-超时未支付
     */
    private Integer payStatus;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}