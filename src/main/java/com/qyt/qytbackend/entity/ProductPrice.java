package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductPrice extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal originalPrice;
    private BigDecimal sellingPrice;
    private BigDecimal bulkPurchasePrice;
    private Long productId;
    private LocalDateTime effectiveTime;
    private LocalDateTime expiryTime;
    private String priceType;
    private String remarks;
    private String createPin;
    private String updatePin;
}