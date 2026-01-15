package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderAddress extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单号：UUID或自定义规则生成
     */
    private Long orderId;
    /**
     * 物流公司名称
     */
    private String shippingCompany;
    /**
     * 物流单号
     */
    private String trackingNumber;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 收货省份
     */
    private String receiverProvince;
    /**
     * 收货城市
     */
    private String receiverCity;
    /**
     * 收货县城：关联合并后的地区表
     */
    private String receiverCounty;
    /**
     * 详细地址
     */
    private String receiverAddress;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}