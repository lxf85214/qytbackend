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