package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZoneProductGroup extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String groupName;
    private Long productId;
    private String createPin;
    private String updatePin;
}