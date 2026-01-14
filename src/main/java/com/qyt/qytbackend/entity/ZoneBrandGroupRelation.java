package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZoneBrandGroupRelation extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long zoneId;
    private Long brandGroupId;
    private String createPin;
    private String updatePin;
}