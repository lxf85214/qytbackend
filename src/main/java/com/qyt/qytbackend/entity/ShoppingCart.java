package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class ShoppingCart extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer productId;
    private String productSku;
    private Integer quantity;
    private Integer selected;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createPin;
    private String updatePin;
}