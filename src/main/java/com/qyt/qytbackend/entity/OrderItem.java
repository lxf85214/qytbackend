package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long productId;
    private String productName;
    private String productImage;
    private String productSku;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal discountRate;
    private BigDecimal subtotal;
    private String createPin;
    private String updatePin;
}