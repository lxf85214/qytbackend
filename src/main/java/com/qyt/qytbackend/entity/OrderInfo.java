package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID：关联客户信息表
     */
    private Integer userId;
    /**
     * 供应商id：采购单有值
     */
    private Integer supplierId;
    /**
     * 订单类型：1-销售单;2-采购单
     */
    private Integer orderType;
    /**
     * 父订单id：父单或未拆单订单为0
     */
    private String parentOrderId;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 实付金额
     */
    private BigDecimal payAmount;
    /**
     * 运费
     */
    private BigDecimal freight;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 下单渠道：1-立即购买;2-购物车;3-代下单
     */
    private Integer orderChannel;
    /**
     * 支付方式：1-支付宝，2-微信，3-银行卡
     */
    private Integer payType;
    /**
     * 支付状态：0-未支付，1-已支付，2-退款中，3-已退款
     */
    private Integer payStatus;
    /**
     * 订单状态：0-待付款，1-待发货，2-待收货，3-已完成，4-已取消
     */
    private Integer orderStatus;
    /**
     * 下单时间
     */
    private LocalDateTime orderTime;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 发货时间
     */
    private LocalDateTime shipTime;
    /**
     * 确认收货时间
     */
    private LocalDateTime confirmTime;
    /**
     * 过期/取消时间
     */
    private LocalDateTime cancelTime;
    /**
     * 取消类型：1-超时未支付，系统自动取消；2-用户取消
     */
    private Integer cancelType;
    /**
     * 取消原因
     */
    private String cancelReason;
    /**
     * 下单备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}