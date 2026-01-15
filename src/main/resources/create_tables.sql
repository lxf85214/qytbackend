-- 相关表结构SQL脚本
-- 创建时间：2026-01-14

-- 1. 商品信息表
CREATE TABLE `product_info` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT '商品唯一标识',
  `product_name` VARCHAR(255) NOT NULL COMMENT '商品名称',
  `district` VARCHAR(50) NOT NULL COMMENT '所在区',
  `category_first_id` INT(11) NOT NULL COMMENT '商品一级分类ID（关联分类表）',
  `category_second_id` INT(11) NOT NULL COMMENT '商品二级分类ID（关联分类表）',
  `category_third_id` INT(11) NOT NULL COMMENT '商品三级分类ID（关联分类表）',
  `brand_id` INT(11) NOT NULL COMMENT '品牌ID（关联品牌表）',
  `main_image` VARCHAR(255) NOT NULL COMMENT '主图URL',
  `thumbnail_image` VARCHAR(255) COMMENT '预览图URL',
  `listing_image` VARCHAR(255) COMMENT '列表图URL',
  `detail_images` TEXT COMMENT '详情图URL（多个用逗号分隔）',
  `description` TEXT COMMENT '商品描述',
  `specifications` TEXT COMMENT '商品规格（JSON格式存储）',
  `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '状态：0-下架，1-上架',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- 2. 商品价格表
CREATE TABLE `product_price` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID：唯一标识一条商品价格记录',
  `original_price` DECIMAL(10,2) NOT NULL COMMENT '商品原价：商品的原始定价，不包含任何折扣',
  `selling_price` DECIMAL(10,2) NOT NULL COMMENT '商品售价：商品当前的销售价格，可能包含日常折扣',
  `bulk_purchase_price` DECIMAL(10,2) COMMENT '集采价格：商品的集中采购价格，适用于批量采购场景',
  `product_id` BIGINT(20) NOT NULL COMMENT '商品ID：关联的商品唯一标识',
  `effective_time` DATETIME NOT NULL COMMENT '生效时间：价格生效的起始时间',
  `expiry_time` DATETIME COMMENT '失效时间：价格失效的结束时间，为空表示该价格长期有效',
  `price_type` VARCHAR(20) NOT NULL COMMENT '价格类型：区分价格的类型，例如：日常售价、促销价、会员价等',
  `remarks` VARCHAR(500) COMMENT '备注：对价格信息的补充说明',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品价格表';

-- 3. 商品库存表
CREATE TABLE `product_stock` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID：唯一标识每一条库存记录',
  `product_id` BIGINT(20) NOT NULL COMMENT '商品ID：关联商品表的商品唯一标识',
  `stock_quantity` INT(11) NOT NULL DEFAULT '0' COMMENT '库存数量：当前商品的库存数量',
  `warehouse_id` BIGINT(11) NOT NULL COMMENT '仓库ID：关联仓库表的仓库唯一标识',
  `locked_quantity` INT(11) NOT NULL DEFAULT '0' COMMENT '锁定数量：因订单等原因被锁定的库存数量',
  `available_quantity` INT(11) NOT NULL DEFAULT '0' COMMENT '可用库存数量：可被销售的实际可用库存',
  `sales` INT(11) DEFAULT '0' COMMENT '累计销量',
  `version` INT(11) NOT NULL DEFAULT '0' COMMENT '版本号：乐观锁版本号，用于并发控制',
  `remark` VARCHAR(255) COMMENT '备注：关于库存记录的额外说明信息',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品库存表';

-- 4. 商品属性表
CREATE TABLE `product_attr` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识符：商品属性记录唯一标识符',
  `attr_type` INT(11) NOT NULL COMMENT '属性类型：商品属性的分类',
  `attr_name` VARCHAR(100) NOT NULL COMMENT '属性名称：具体的属性值',
  `product_id` INT(20) NOT NULL COMMENT '商品id：关联的商品唯一标识符',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性表';

-- 5. 商品品类表
CREATE TABLE `product_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` VARCHAR(50) NOT NULL COMMENT '分类名称',
  `parent_id` INT(11) DEFAULT '0' COMMENT '父分类ID：0-顶级分类',
  `level` INT(2) NOT NULL COMMENT '分类层级：1-一级，2-二级等',
  `sort_order` INT(4) DEFAULT '0' COMMENT '排序优先级：数字越小越靠前',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) DEFAULT '0' NOT NULL COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品品类表';

-- 6. 商品品牌表
CREATE TABLE `product_brand` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '品牌ID',
  `brand_name` VARCHAR(50) NOT NULL COMMENT '品牌名称',
  `brand_logo` VARCHAR(255) COMMENT '品牌logo URL',
  `brand_desc` TEXT COMMENT '品牌描述',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品品牌表';

-- 7. 商品上架下架表
CREATE TABLE `product_add_and_remove` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '申请单号：唯一标识商品上架或下架申请的编号',
  `product_name` VARCHAR(255) NOT NULL COMMENT '商品名称：商品的全称',
  `product_category` VARCHAR(100) NOT NULL COMMENT '商品分类：商品所属的分类',
  `brand_id` BIGINT(11) NOT NULL COMMENT '品牌id',
  `brand_name` VARCHAR(100) NOT NULL COMMENT '品牌名称',
  `product_sku` VARCHAR(50) NOT NULL COMMENT '商品SKU编码：商品的唯一库存单位编码',
  `specifications` TEXT COMMENT '规格参数：商品的详细规格参数',
  `selling_price` DECIMAL(10,2) NOT NULL COMMENT '销售价：商品当前的销售价格',
  `original_price` DECIMAL(10,2) COMMENT '市场价/原价：商品的市场指导价或原价',
  `cost_price` DECIMAL(10,2) NOT NULL COMMENT '成本价：商品的采购或生产成本价',
  `stock_quantity` INT(11) NOT NULL DEFAULT '0' COMMENT '库存数量：商品当前的库存数量',
  `main_image` VARCHAR(255) NOT NULL COMMENT '商品主图：商品主图的URL地址',
  `detail_media` TEXT COMMENT '商品详情图/视频：商品详情页的图片或视频URL地址',
  `product_description` TEXT COMMENT '商品描述：商品的详细文字描述',
  `logistics_info` VARCHAR(255) COMMENT '物流信息：商品的物流方式、运费政策等信息',
  `after_sales_service` TEXT COMMENT '售后服务：商品的售后服务政策',
  `tags` VARCHAR(255) COMMENT '标签：商品的关键词标签',
  `current_status` VARCHAR(20) NOT NULL COMMENT '当前状态：商品当前的状态',
  `remove_reason` VARCHAR(255) COMMENT '下架原因：当商品操作类型为下架或当前状态为已下架时的原因',
  `remarks` TEXT COMMENT '备注：申请过程中的其他补充说明信息',
  `operation_type` VARCHAR(20) NOT NULL COMMENT '操作类型：固定值为"上架"或"下架"',
  `operation_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间：记录本条记录创建或最后一次操作的时间',
  `applicant` VARCHAR(50) NOT NULL COMMENT '申请人：提交上架或下架申请的人员姓名',
  `application_department` VARCHAR(50) NOT NULL COMMENT '申请部门：申请人所属的部门名称',
  `application_date` DATE NOT NULL DEFAULT (CURRENT_DATE) COMMENT '申请日期：申请提交的日期',
  `approval_status` VARCHAR(20) NOT NULL DEFAULT 'pending' COMMENT '审核状态：pending（待审核）、approved（已审核通过）、rejected（已驳回）',
  `approval_comments` TEXT COMMENT '审核意见：审核人对申请的具体意见或驳回理由',
  `approver` VARCHAR(50) COMMENT '审核人：执行审核操作的人员姓名',
  `approval_date` DATE COMMENT '审核日期：审核操作执行的日期',
  `actual_remove_time` DATETIME COMMENT '实际下架时间：当商品实际完成下架操作时记录的时间',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_brand_id` (`brand_id`),
  KEY `idx_approval_status` (`approval_status`),
  KEY `idx_operation_type` (`operation_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品上架下架表';

-- 1. 客户信息表
CREATE TABLE `customer_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识（就是user_id）',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名：唯一',
  `password` VARCHAR(100) NOT NULL COMMENT '密码（加密存储）',
  `phone` VARCHAR(20) COMMENT '手机号码：唯一',
  `email` VARCHAR(100) COMMENT '电子邮箱',
  `real_name` VARCHAR(50) COMMENT '真实姓名',
  `id_card` VARCHAR(20) COMMENT '身份证号：唯一',
  `avatar` VARCHAR(255) COMMENT '头像URL',
  `register_time` DATETIME NOT NULL COMMENT '注册时间',
  `login_status` TINYINT(1) DEFAULT '0' COMMENT '登录状态：0-已登出；1-已登录',
  `last_login_time` DATETIME COMMENT '最后登录时间',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '账号状态：0-禁用，1-正常',
  `member_level` INT(2) DEFAULT '0' COMMENT '会员等级：0-普通，1-白银，2-黄金等',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_phone` (`phone`),
  UNIQUE KEY `uk_id_card` (`id_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户信息表';

-- 2. 客户验证码表
CREATE TABLE `customer_verification_code` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `phone_number` VARCHAR(20) NOT NULL COMMENT '用户手机号',
  `verification_code` VARCHAR(10) NOT NULL COMMENT '验证码',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_phone_create_time` (`phone_number`,`create_time`) COMMENT '用于查询指定手机号最新验证码的索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户验证码记录表';

-- 3. 客户登录日志表
CREATE TABLE `customer_login_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID：登录日志ID，自增主键',
  `user_id` BIGINT(11) NOT NULL COMMENT '用户ID：关联用户表的用户唯一标识',
  `login_time` DATETIME NOT NULL COMMENT '登录时间：记录用户成功登录系统的具体日期和时间',
  `logout_time` DATETIME COMMENT '登出时间：记录用户主动登出或系统判定其登出的具体日期和时间',
  `login_ip` VARCHAR(50) COMMENT '登录IP地址：记录用户登录时使用的网络IP地址',
  `login_device` VARCHAR(100) COMMENT '登录设备信息：记录用户登录时使用的设备类型及相关信息',
  `login_status` TINYINT(1) NOT NULL COMMENT '登录状态：0表示登出，1表示登录成功',
  `logout_type` VARCHAR(20) COMMENT '登出类型：可选项如"主动登出"、"超时登出"、"异常退出"等',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户登录日志表';

-- 4. 客户收货地址管理表
CREATE TABLE `customer_address` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '地址ID：收货地址的唯一标识',
  `recipient_mobile` VARCHAR(20) NOT NULL COMMENT '收货手机号：收货人的联系电话',
  `recipient_name` VARCHAR(50) NOT NULL COMMENT '收货人：收货人的姓名',
  `province` VARCHAR(50) NOT NULL COMMENT '所在省：收货地址所在的省份',
  `city` VARCHAR(50) NOT NULL COMMENT '所在市：收货地址所在的城市',
  `district` VARCHAR(50) NOT NULL COMMENT '所在区：收货地址所在的区/县',
  `detailed_address` VARCHAR(255) NOT NULL COMMENT '收货详细地址：具体的街道、门牌号等详细地址信息',
  `is_default` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否默认：是否为默认收货地址，0表示否，1表示是',
  `postal_code` VARCHAR(20) COMMENT '邮政编码：收货地址的邮政编码',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户收货地址管理表';

-- 5. 地区信息表
CREATE TABLE `region_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '地区唯一标识（region_id）',
  `region_name` VARCHAR(50) NOT NULL COMMENT '地区名称',
  `region_type` TINYINT(1) NOT NULL COMMENT '地区类型：1-省份，2-城市，3-县城',
  `parent_id` INT(11) DEFAULT '0' COMMENT '父地区ID：省份的父ID为0，城市的父ID为省份ID，县城的父ID为城市ID',
  `region_code` VARCHAR(10) COMMENT '地区编码：如国标码，省份2位，城市4位，县城6位等',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态：0-无效，1-有效',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_region_type` (`region_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地区信息表（合并后的省份、城市、县城信息表）';

-- 1. 购物车表
CREATE TABLE `shopping_cart` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT '购物车记录ID',
  `user_id` INT(11) NOT NULL COMMENT '用户ID：关联客户信息表',
  `product_id` INT(20) NOT NULL COMMENT '商品ID：关联商品信息表',
  `product_sku` VARCHAR(50) COMMENT '商品SKU：如有规格',
  `quantity` INT(11) NOT NULL DEFAULT '1' COMMENT '商品数量',
  `selected` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否选中：0-未选中，1-选中',
  `created_time` DATETIME NOT NULL COMMENT '创建时间',
  `updated_time` DATETIME NOT NULL COMMENT '更新时间',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- 2. 订单信息表
CREATE TABLE `order_info` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `user_id` INT(11) NOT NULL COMMENT '用户ID：关联客户信息表',
  `supplier_id` INT(11) COMMENT '供应商id：采购单有值',
  `order_type` INT(4) COMMENT '订单类型：1-销售单;2-采购单',
  `parent_order_id` VARCHAR(32) COMMENT '父订单id：父单或未拆单订单为0',
  `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
  `pay_amount` DECIMAL(10,2) NOT NULL COMMENT '实付金额',
  `freight` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '运费',
  `discount_amount` DECIMAL(10,2) DEFAULT '0' COMMENT '优惠金额',
  `order_channel` TINYINT(1) COMMENT '下单渠道：1-立即购买;2-购物车;3-代下单',
  `pay_type` TINYINT(1) COMMENT '支付方式：1-支付宝，2-微信，3-银行卡',
  `pay_status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '支付状态：0-未支付，1-已支付，2-退款中，3-已退款',
  `order_status` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '订单状态：0-待付款，1-待发货，2-待收货，3-已完成，4-已取消',
  `order_time` DATETIME COMMENT '下单时间',
  `pay_time` DATETIME COMMENT '支付时间',
  `ship_time` DATETIME COMMENT '发货时间',
  `confirm_time` DATETIME COMMENT '确认收货时间',
  `cancel_time` DATETIME COMMENT '过期/取消时间',
  `cancel_type` TINYINT(2) COMMENT '取消类型：1-超时未支付，系统自动取消；2-用户取消',
  `cancel_reason` VARCHAR(256) COMMENT '取消原因',
  `remark` VARCHAR(256) COMMENT '下单备注',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_status` (`order_status`),
  KEY `idx_pay_status` (`pay_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';

-- 3. 订单明细表
CREATE TABLE `order_item` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
  `order_id` BIGINT(20) NOT NULL COMMENT '订单号：关联订单信息表',
  `product_id` BIGINT(20) NOT NULL COMMENT '商品ID：关联商品信息表',
  `product_name` VARCHAR(255) NOT NULL COMMENT '商品名称：下单时快照',
  `product_image` VARCHAR(255) NOT NULL COMMENT '商品图片：下单时快照',
  `product_sku` VARCHAR(50) COMMENT '商品SKU：如有规格',
  `price` DECIMAL(10,2) NOT NULL COMMENT '购买单价',
  `quantity` INT(11) NOT NULL COMMENT '购买数量',
  `discount_rate` DECIMAL(10,2) COMMENT '折扣',
  `subtotal` DECIMAL(10,2) NOT NULL COMMENT '小计金额：price*quantity',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- 4. 订单地址表
CREATE TABLE `order_address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识id',
  `order_id` BIGINT(20) COMMENT '订单号：UUID或自定义规则生成',
  `shipping_company` VARCHAR(50) COMMENT '物流公司名称',
  `tracking_number` VARCHAR(50) COMMENT '物流单号',
  `receiver_name` VARCHAR(50) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` VARCHAR(20) NOT NULL COMMENT '收货人电话',
  `receiver_province` VARCHAR(20) NOT NULL COMMENT '收货省份',
  `receiver_city` VARCHAR(20) NOT NULL COMMENT '收货城市',
  `receiver_county` VARCHAR(20) NOT NULL COMMENT '收货县城：关联合并后的地区表',
  `receiver_address` VARCHAR(255) NOT NULL COMMENT '详细地址',
  `created_time` DATETIME NOT NULL COMMENT '创建时间',
  `updated_time` DATETIME COMMENT '更新时间',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单地址表';

-- 5. 支付流水表
CREATE TABLE `pay_trans_log` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识id',
  `order_id` BIGINT(20) COMMENT '支付订单号',
  `sub_order_id` BIGINT(20) COMMENT '子单号',
  `third_seq_no` BIGINT(64) COMMENT '三方支付流水号：微信/支付宝返回的流水号',
  `pay_type` TINYINT(4) COMMENT '类型：1-支付0-退款',
  `pay_amount` DECIMAL(10,2) COMMENT '支付金额：支付总金额',
  `cash_amount` DECIMAL(10,2) COMMENT '现金金额：现金支付金额',
  `discount_amount` BIGINT(20) COMMENT '优惠金额：优惠金额(金额单位：分/积分数)',
  `pay_status` TINYINT(4) COMMENT '支付状态：10-待支付、20-支付成功，30-支付失败，40-退款中，50-已退款，60-超时未支付',
  `created_time` DATETIME NOT NULL COMMENT '创建时间',
  `updated_time` DATETIME COMMENT '更新时间',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_pay_status` (`pay_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付流水表';

-- 1. 后端管理系统-用户信息表
CREATE TABLE `user_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识（就是user_id）',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名：唯一',
  `password` VARCHAR(100) NOT NULL COMMENT '密码（加密存储）',
  `phone` VARCHAR(20) COMMENT '手机号码：唯一',
  `email` VARCHAR(100) COMMENT '电子邮箱',
  `real_name` VARCHAR(50) COMMENT '真实姓名',
  `id_card` VARCHAR(20) COMMENT '身份证号：唯一',
  `avatar` VARCHAR(255) COMMENT '头像URL',
  `user_type` TINYINT(4) COMMENT '用户类型：0-全域通平台;1-供应商',
  `register_time` DATETIME NOT NULL COMMENT '注册时间',
  `login_status` TINYINT(1) DEFAULT '0' COMMENT '登录状态：0-已登出；1-已登录',
  `last_login_time` DATETIME COMMENT '最后登录时间',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '账号状态：0-禁用，1-正常',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_phone` (`phone`),
  UNIQUE KEY `uk_id_card` (`id_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-用户信息表';

-- 2. 后端管理系统-用户验证码表
CREATE TABLE `user_verification_code` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `phone_number` VARCHAR(20) NOT NULL COMMENT '用户手机号',
  `verification_code` VARCHAR(10) NOT NULL COMMENT '验证码',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_phone_create_time` (`phone_number`,`create_time`) COMMENT '用于查询指定手机号最新验证码的索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-用户验证码表';

-- 3. 后端管理系统-用户登录日志表
CREATE TABLE `user_login_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID：登录日志ID，自增主键',
  `user_id` BIGINT(11) NOT NULL COMMENT '用户ID：关联用户表的用户唯一标识',
  `login_time` DATETIME NOT NULL COMMENT '登录时间：记录用户成功登录系统的具体日期和时间',
  `logout_time` DATETIME COMMENT '登出时间：记录用户主动登出或系统判定其登出的具体日期和时间',
  `login_ip` VARCHAR(50) COMMENT '登录IP地址：记录用户登录时使用的网络IP地址',
  `login_device` VARCHAR(100) COMMENT '登录设备信息：记录用户登录时使用的设备类型及相关信息',
  `login_status` TINYINT(1) NOT NULL COMMENT '登录状态：0表示登出，1表示登录成功',
  `logout_type` VARCHAR(20) COMMENT '登出类型：可选项如"主动登出"、"超时登出"、"异常退出"等',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-用户登录日志表';

-- 4. 后端管理系统-角色表
CREATE TABLE `role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '角色标识',
  `role_name` VARCHAR(255) COMMENT '角色名称',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态（0-下架，1-上架）',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-角色表';

-- 5. 后端管理系统-菜单表
CREATE TABLE `resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `name` VARCHAR(255) COMMENT '菜单名称',
  `pid` INT(11) COMMENT '父id',
  `type` VARCHAR(16) NOT NULL COMMENT 'MENU,ELEM',
  `url` VARCHAR(512) COMMENT '菜单地址',
  `level` TINYINT(4) COMMENT '层级，从0开始',
  `order_num` TINYINT(4) COMMENT '序号',
  `icon` VARCHAR(512) COMMENT '菜单图标',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态（0-停用，1-启用）',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`),
  KEY `idx_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-菜单表';

-- 6. 后端管理系统-菜单角色映射表
CREATE TABLE `role_resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` INT(11) COMMENT '角色id',
  `resource_id` INT(11) COMMENT '菜单id',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-菜单角色映射表';

-- 7. 后端管理系统-用户角色映射表
CREATE TABLE `user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` INT(11) COMMENT '用户id',
  `role_id` INT(11) COMMENT '角色id',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-用户角色映射表';

-- 1. 后端管理系统-首页专区配置表
CREATE TABLE `zone_config` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID：唯一标识每条专区配置记录',
  `item_type` INT(4) NOT NULL COMMENT '类目：1-推荐，2-米面粮油',
  `item_name` VARCHAR(50) NOT NULL COMMENT '类目名称：1-推荐，2-米面粮油',
  `zone_name` VARCHAR(100) NOT NULL COMMENT '专区名称：如"热门推荐""新品专区"等',
  `is_display` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否展示：0-不展示，1-展示，默认1',
  `default_image` VARCHAR(255) NOT NULL COMMENT '默认图片：用于专区未自定义图片时的展示',
  `description` VARCHAR(500) COMMENT '描述：对专区内容或规则的补充说明',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_item_type` (`item_type`),
  KEY `idx_is_display` (`is_display`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-首页专区配置表';

-- 2. 后端管理系统-首页专区商品组表
CREATE TABLE `zone_product_group` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID：唯一标识每个商品组记录',
  `group_name` VARCHAR(100) NOT NULL COMMENT '商品组名称：如"手机数码组""家居用品组"等',
  `product_id` BIGINT(20) NOT NULL COMMENT '商品ID：关联商品表的商品ID',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-首页专区商品组表';

-- 3. 后端管理系统-首页专区商品组映射表
CREATE TABLE `zone_product_group_relation` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID：唯一标识每条映射记录',
  `zone_id` BIGINT(11) NOT NULL COMMENT '专区ID：关联首页推荐专区配置表的id',
  `group_id` BIGINT(11) NOT NULL COMMENT '商品组ID：关联首页推荐专区商品组表的id',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_zone_id` (`zone_id`),
  KEY `idx_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-首页专区商品组映射表';

-- 4. 后端管理系统-首页专区品牌组表
CREATE TABLE `zone_brand_group` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID：唯一标识每个商品组记录',
  `brand_group_name` VARCHAR(100) NOT NULL COMMENT '品牌组组名称',
  `brand_id` BIGINT(11) NOT NULL COMMENT '品牌ID：关联品牌表的ID',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_brand_id` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-首页专区品牌组表';

-- 5. 后端管理系统-首页专区品牌组映射表
CREATE TABLE `zone_brand_group_relation` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID：唯一标识每条映射记录',
  `zone_id` BIGINT(11) NOT NULL COMMENT '专区ID：关联首页专区配置表的id',
  `brand_group_id` BIGINT(11) NOT NULL COMMENT '品牌组ID：关联首页专区品牌组表的id',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  KEY `idx_zone_id` (`zone_id`),
  KEY `idx_brand_group_id` (`brand_group_id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端管理系统-首页专区品牌组映射表';

-- 供应商信息表
CREATE TABLE `supplier_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识（就是user_id）',
  `supplier_name` VARCHAR(50) COMMENT '供应商名称：商品页展示名',
  `company_name` VARCHAR(50) COMMENT '企业名称：唯一',
  `supplier_type` TINYINT(4) COMMENT '供应商类型：1生产厂商、2大型电商、3普通电商、4常规代理商',
  `bussiness_license` VARCHAR(64) COMMENT '营业执照注册号',
  `tax_license` VARCHAR(64) COMMENT '税务登记号',
  `company_province` VARCHAR(16) COMMENT '企业注册省',
  `company_city` VARCHAR(16) COMMENT '企业注册市',
  `company_addr` VARCHAR(512) COMMENT '企业注册详细地址',
  `bussiness_license_image` VARCHAR(512) COMMENT '营业执照图片',
  `bank_name` VARCHAR(32) COMMENT '开户行',
  `bank_name_branch` VARCHAR(32) COMMENT '开户支行',
  `bank_code` VARCHAR(32) COMMENT '银行账号',
  `legalPerson` VARCHAR(8) COMMENT '法人姓名',
  `legal_identity_id` VARCHAR(32) COMMENT '法人身份证号',
  `legal_phone` VARCHAR(32) COMMENT '法人电话',
  `contact_name` VARCHAR(8) COMMENT '联系人姓名',
  `contact_phone` VARCHAR(32) COMMENT '联系人电话',
  `contact_address` VARCHAR(512) COMMENT '联系地址',
  `email` VARCHAR(32) COMMENT 'email',
  `customer_service_phone` VARCHAR(16) COMMENT '客服电话',
  `unit_logo` VARCHAR(512) COMMENT '徽标LOGO图片',
  `state` TINYINT(4) COMMENT '状态：1-提交申请;2-审核通过;3-缴纳保证金;4-审核失败;5-注册未完成;6-冻结',
  `create_pin` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_pin` VARCHAR(50) COMMENT '更新人',
  `update_time` DATETIME COMMENT '更新时间',
  `is_delete` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0表示未删除，1表示已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_company_name` (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商信息表';
