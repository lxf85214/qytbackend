package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductStock extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long productId;
    private Integer stockQuantity;
    private Long warehouseId;
    private Integer lockedQuantity;
    private Integer availableQuantity;
    private Integer sales;
    private Integer version;
    private String remark;
    private String createPin;
    private String updatePin;
}