package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductCategory extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String categoryName;
    private Integer parentId;
    private Integer level;
    private Integer sortOrder;
    private Integer status;
    private String createPin;
    private String updatePin;
}