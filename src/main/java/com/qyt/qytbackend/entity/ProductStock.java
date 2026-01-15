package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductStock extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID：关联商品表的商品唯一标识
     */
    private Long productId;
    /**
     * 库存数量：当前商品的库存数量
     */
    private Integer stockQuantity;
    /**
     * 仓库ID：关联仓库表的仓库唯一标识
     */
    private Long warehouseId;
    /**
     * 锁定数量：因订单等原因被锁定的库存数量
     */
    private Integer lockedQuantity;
    /**
     * 可用库存数量：可被销售的实际可用库存
     */
    private Integer availableQuantity;
    /**
     * 累计销量
     */
    private Integer sales;
    /**
     * 版本号：乐观锁版本号，用于并发控制
     */
    private Integer version;
    /**
     * 备注：关于库存记录的额外说明信息
     */
    private String remark;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}