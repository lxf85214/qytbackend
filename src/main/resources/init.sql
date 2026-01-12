CREATE TABLE `user_verification_code` ( 
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识', 
 `phone_number` VARCHAR(20) NOT NULL COMMENT '手机号', 
 `verification_code` VARCHAR(10) NOT NULL COMMENT '验证码', 
 `create_time` DATETIME NOT NULL COMMENT '创建时间', 
 `create_by` VARCHAR(50) NOT NULL COMMENT '创建人', 
 `update_time` DATETIME NULL COMMENT '更新时间', 
 `update_by` VARCHAR(50) NULL COMMENT '更新人', 
 `is_delete` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0-未删除，1-已删除）', 
 PRIMARY KEY (`id`), 
 KEY `idx_phone_create_time` (`phone_number`, `create_time`) COMMENT '用于查询指定手机号最新验证码的索引' 
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户验证码记录表';

-- 创建客户信息表
CREATE TABLE `customer_info` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码（加密存储）',
  `phone` VARCHAR(20) UNIQUE COMMENT '手机号码',
  `email` VARCHAR(100) UNIQUE COMMENT '电子邮箱',
  `real_name` VARCHAR(50) COMMENT '真实姓名',
  `id_card` VARCHAR(20) UNIQUE COMMENT '身份证号',
  `avatar` VARCHAR(255) COMMENT '头像URL',
  `register_time` DATETIME NOT NULL COMMENT '注册时间',
  `login_status` TINYINT(1) DEFAULT 0 COMMENT '登录状态：0-已登出；1-已登录',
  `last_login_time` DATETIME COMMENT '最后登录时间',
  `status` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '账号状态（0-禁用，1-正常）',
  `member_level` INT(2) DEFAULT 0 COMMENT '会员等级（0-普通，1-白银，2-黄金等）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户信息表';