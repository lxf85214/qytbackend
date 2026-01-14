package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerAddress extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long customerId;
    private String receiverName;
    private String receiverPhone;
    private String province;
    private String city;
    private String district;
    private String detailedAddress;
    private Integer isDefault;
    private String createPin;
    private String updatePin;
}