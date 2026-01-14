package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZoneProductGroupRelation extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long zoneId;
    private Long groupId;
    private String createPin;
    private String updatePin;
}