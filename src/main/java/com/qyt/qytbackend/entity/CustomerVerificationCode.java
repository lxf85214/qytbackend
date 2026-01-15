package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerVerificationCode extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户手机号
     */
    private String phoneNumber;
    /**
     * 验证码
     */
    private String verificationCode;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}