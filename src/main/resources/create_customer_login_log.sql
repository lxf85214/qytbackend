CREATE TABLE `customer_login_log` (
  `login_log_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志ID，自增主键，唯一标识每条登录日志记录',
  `user_id` VARCHAR(50) NOT NULL COMMENT '用户ID，关联用户表的用户唯一标识，用于确定是哪个用户的登录行为',
  `login_time` DATETIME NOT NULL COMMENT '登录时间，记录用户成功登录系统的具体日期和时间，格式为YYYY-MM-DD HH:MM:SS',
  `logout_time` DATETIME NULL COMMENT '登出时间，记录用户主动登出或系统判定其登出的具体日期和时间，未登出时可为NULL，格式同上',
  `login_ip` VARCHAR(50) NOT NULL COMMENT '登录IP地址，记录用户登录时使用的网络IP地址，便于追踪登录来源',
  `login_device` VARCHAR(100) NOT NULL COMMENT '登录设备信息，记录用户登录时使用的设备类型及相关信息，如"Windows 10 - Chrome 96.0"、"iPhone - Safari"等',
  `login_status` TINYINT(1) NOT NULL COMMENT '登录状态，0表示登出，1表示登录成功，用于区分登录行为的结果',
  `logout_type` VARCHAR(20) NULL COMMENT '登出类型，可选项如"主动登出"、"超时登出"、"异常退出"等，说明用户登出的方式或原因',
  `created_at` DATETIME NOT NULL COMMENT '记录创建时间，即本条登录日志记录生成的时间，通常与login_time一致或略晚，格式为YYYY-MM-DD HH:MM:SS',
  `updated_at` DATETIME NOT NULL COMMENT '记录更新时间，当logout_time或logout_type等信息补全或修改时更新，格式同上',
  PRIMARY KEY (`login_log_id`),
  KEY `idx_user_id_login_time` (`user_id`, `login_time`) COMMENT '用于查询指定用户登录记录的索引',
  KEY `idx_login_time` (`login_time`) COMMENT '用于按时间查询登录记录的索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录日志表';