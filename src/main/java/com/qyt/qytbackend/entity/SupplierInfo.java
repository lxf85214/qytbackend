package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商信息表
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SupplierInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String supplierName;
    private String companyName;
    private Integer supplierType;
    private String bussinessLicense;
    private String taxLicense;
    private String companyProvince;
    private String companyCity;
    private String companyAddr;
    private String bussinessLicenseImage;
    private String bankName;
    private String bankNameBranch;
    private String bankCode;
    private String legalPerson;
    private String legalIdentityId;
    private String legalPhone;
    private String contactName;
    private String contactPhone;
    private String contactAddress;
    private String email;
    private String customerServicePhone;
    private String unitLogo;
    private Integer state;
    private String createPin;
    private String updatePin;
}
