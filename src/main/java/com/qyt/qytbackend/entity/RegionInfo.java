package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class RegionInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String regionName;
    private Integer parentId;
    private Integer level;
    private String regionCode;
    private String createPin;
    private String updatePin;
}