package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class RegionInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 地区名称
     */
    private String regionName;
    /**
     * 父地区ID：省份的父ID为0，城市的父ID为省份ID，县城的父ID为城市ID
     */
    private Integer parentId;
    /**
     * 地区类型：1-省份，2-城市，3-县城
     */
    private Integer level;
    /**
     * 地区编码：如国标码，省份2位，城市4位，县城6位等
     */
    private String regionCode;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}