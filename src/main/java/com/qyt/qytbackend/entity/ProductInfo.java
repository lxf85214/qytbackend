package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productName;
    private String district;
    private Integer categoryFirstId;
    private Integer categorySecondId;
    private Integer categoryThirdId;
    private Integer brandId;
    private String mainImage;
    private String thumbnailImage;
    private String listingImage;
    private String detailImages;
    private String description;
    private String specifications;
    private Integer status;
    private String createPin;
    private String updatePin;
}