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

    /**
     * 供应商名称：商品页展示名
     */
    private String supplierName;
    /**
     * 企业名称：唯一
     */
    private String companyName;
    /**
     * 供应商类型：1-企业供应商;2-个人供应商
     */
    private Integer supplierType;
    /**
     * 营业执照号：企业必填
     */
    private String bussinessLicense;
    /**
     * 税务登记证号：三证合一后，使用统一社会信用代码
     */
    private String taxLicense;
    /**
     * 企业所在省
     */
    private String companyProvince;
    /**
     * 企业所在市
     */
    private String companyCity;
    /**
     * 企业详细地址
     */
    private String companyAddr;
    /**
     * 营业执照图片：上传的营业执照副本电子图片
     */
    private String bussinessLicenseImage;
    /**
     * 开户银行名称：企业的开户银行名称
     */
    private String bankName;
    /**
     * 银行支行名称：企业开户银行的具体支行名称
     */
    private String bankNameBranch;
    /**
     * 银行账号：企业的银行账号
     */
    private String bankCode;
    /**
     * 法定代表人姓名：企业的法定代表人姓名
     */
    private String legalPerson;
    /**
     * 法定代表人身份证号：企业法定代表人的身份证号码
     */
    private String legalIdentityId;
    /**
     * 法定代表人电话：企业法定代表人的联系电话
     */
    private String legalPhone;
    /**
     * 联系人姓名：供应商的业务联系人姓名
     */
    private String contactName;
    /**
     * 联系人电话：供应商业务联系人的联系电话
     */
    private String contactPhone;
    /**
     * 联系人地址：供应商业务联系人的联系地址
     */
    private String contactAddress;
    /**
     * 电子邮箱：供应商的联系邮箱
     */
    private String email;
    /**
     * 客服电话：供应商的客服热线电话
     */
    private String customerServicePhone;
    /**
     * 单位logo：供应商的企业logo图片URL
     */
    private String unitLogo;
    /**
     * 状态：0-已删除，1-有效，2-待审核
     */
    private Integer state;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}
