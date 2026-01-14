package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductAttr extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long productId;
    private String attrName;
    private String attrValue;
    private String createPin;
    private String updatePin;
}