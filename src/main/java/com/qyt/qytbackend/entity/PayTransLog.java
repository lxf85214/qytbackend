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

    private Long orderId;
    private Long subOrderId;
    private Long thirdSeqNo;
    private Integer payType;
    private BigDecimal payAmount;
    private BigDecimal cashAmount;
    private Long discountAmount;
    private Integer payStatus;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createPin;
    private String updatePin;
}