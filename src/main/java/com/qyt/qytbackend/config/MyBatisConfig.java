package com.qyt.qytbackend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 *
 * @author qyt
 * @date 2024
 */
@Configuration
@MapperScan("com.qyt.qytbackend.mapper")
public class MyBatisConfig {
}
