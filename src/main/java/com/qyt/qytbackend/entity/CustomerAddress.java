package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerAddress extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 客户ID：关联客户信息表
     */
    private Long customerId;
    /**
     * 收货人：收货人的姓名
     */
    private String receiverName;
    /**
     * 收货手机号：收货人的联系电话
     */
    private String receiverPhone;
    /**
     * 所在省：收货地址所在的省份
     */
    private String province;
    /**
     * 所在市：收货地址所在的城市
     */
    private String city;
    /**
     * 所在区：收货地址所在的区/县
     */
    private String district;
    /**
     * 收货详细地址：具体的街道、门牌号等详细地址信息
     */
    private String detailedAddress;
    /**
     * 是否默认：是否为默认收货地址，0表示否，1表示是
     */
    private Integer isDefault;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}