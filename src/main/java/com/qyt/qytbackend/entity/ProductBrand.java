package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductBrand extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String brandName;
    private String brandLogo;
    private String brandDesc;
    private Integer status;
    private String createPin;
    private String updatePin;
}