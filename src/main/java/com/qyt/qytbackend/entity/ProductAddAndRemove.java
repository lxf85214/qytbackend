package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductAddAndRemove extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productName;
    private String productCategory;
    private Long brandId;
    private String brandName;
    private String productSku;
    private String specifications;
    private BigDecimal sellingPrice;
    private BigDecimal originalPrice;
    private BigDecimal costPrice;
    private Integer stockQuantity;
    private String mainImage;
    private String detailMedia;
    private String productDescription;
    private String logisticsInfo;
    private String afterSalesService;
    private String tags;
    private String currentStatus;
    private String removeReason;
    private String remarks;
    private String operationType;
    private LocalDateTime operationTime;
    private String applicant;
    private String applicationDepartment;
    private LocalDate applicationDate;
    private String approvalStatus;
    private String approvalComments;
    private String approver;
    private LocalDateTime approvalDate;
    private LocalDateTime actualRemoveTime;
    private String createPin;
    private String updatePin;
}