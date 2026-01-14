package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZoneConfig extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer itemType;
    private String itemName;
    private String zoneName;
    private Integer isDisplay;
    private String defaultImage;
    private String description;
    private String createPin;
    private String updatePin;
}