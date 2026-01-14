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

    private Integer userId;
    private Integer supplierId;
    private Integer orderType;
    private String parentOrderId;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
    private BigDecimal freight;
    private BigDecimal discountAmount;
    private Integer orderChannel;
    private Integer payType;
    private Integer payStatus;
    private Integer orderStatus;
    private LocalDateTime orderTime;
    private LocalDateTime payTime;
    private LocalDateTime shipTime;
    private LocalDateTime confirmTime;
    private LocalDateTime cancelTime;
    private Integer cancelType;
    private String cancelReason;
    private String remark;
    private String createPin;
    private String updatePin;
}