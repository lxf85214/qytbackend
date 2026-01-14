package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderAddress extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private String shippingCompany;
    private String trackingNumber;
    private String receiverName;
    private String receiverPhone;
    private String receiverProvince;
    private String receiverCity;
    private String receiverCounty;
    private String receiverAddress;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createPin;
    private String updatePin;
}