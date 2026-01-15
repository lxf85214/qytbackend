package com.qyt.qytbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductAddAndRemove extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称：商品的全称
     */
    private String productName;
    /**
     * 商品分类：商品所属的分类
     */
    private String productCategory;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 商品SKU编码：商品的唯一库存单位编码
     */
    private String productSku;
    /**
     * 规格参数：商品的详细规格参数
     */
    private String specifications;
    /**
     * 销售价：商品当前的销售价格
     */
    private BigDecimal sellingPrice;
    /**
     * 市场价/原价：商品的市场指导价或原价
     */
    private BigDecimal originalPrice;
    /**
     * 成本价：商品的采购或生产成本价
     */
    private BigDecimal costPrice;
    /**
     * 库存数量：商品当前的库存数量
     */
    private Integer stockQuantity;
    /**
     * 商品主图：商品主图的URL地址
     */
    private String mainImage;
    /**
     * 商品详情图/视频：商品详情页的图片或视频URL地址
     */
    private String detailMedia;
    /**
     * 商品描述：商品的详细文字描述
     */
    private String productDescription;
    /**
     * 物流信息：商品的物流方式、运费政策等信息
     */
    private String logisticsInfo;
    /**
     * 售后服务：商品的售后服务政策
     */
    private String afterSalesService;
    /**
     * 标签：商品的关键词标签
     */
    private String tags;
    /**
     * 当前状态：商品当前的状态
     */
    private String currentStatus;
    /**
     * 下架原因：当商品操作类型为下架或当前状态为已下架时的原因
     */
    private String removeReason;
    /**
     * 备注：申请过程中的其他补充说明信息
     */
    private String remarks;
    /**
     * 操作类型：固定值为"上架"或"下架"
     */
    private String operationType;
    /**
     * 操作时间：记录本条记录创建或最后一次操作的时间
     */
    private LocalDateTime operationTime;
    /**
     * 申请人：提交上架或下架申请的人员姓名
     */
    private String applicant;
    /**
     * 申请部门：申请人所属的部门名称
     */
    private String applicationDepartment;
    /**
     * 申请日期：申请提交的日期
     */
    private LocalDate applicationDate;
    /**
     * 审核状态：pending（待审核）、approved（已审核通过）、rejected（已驳回）
     */
    private String approvalStatus;
    /**
     * 审核意见：审核人对申请的具体意见或驳回理由
     */
    private String approvalComments;
    /**
     * 审核人：执行审核操作的人员姓名
     */
    private String approver;
    /**
     * 审核日期：审核操作执行的日期
     */
    private LocalDateTime approvalDate;
    /**
     * 实际下架时间：当商品实际完成下架操作时记录的时间
     */
    private LocalDateTime actualRemoveTime;
    /**
     * 创建人
     */
    private String createPin;
    /**
     * 更新人
     */
    private String updatePin;
}