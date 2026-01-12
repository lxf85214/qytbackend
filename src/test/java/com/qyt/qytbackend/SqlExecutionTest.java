package com.qyt.qytbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * SQL执行测试类
 * 用于执行初始化SQL语句
 */
@SpringBootTest
class SqlExecutionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 执行SQL创建客户信息表
     */
    @Test
    void executeCreateCustomerInfoTable() throws IOException {
        // 读取SQL文件
        ClassPathResource resource = new ClassPathResource("create_customer_info.sql");
        String sql = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        
        System.out.println("执行SQL语句:");
        System.out.println(sql);
        
        // 执行SQL
        jdbcTemplate.execute(sql);
        
        System.out.println("\n✅ SQL执行成功，客户信息表创建完成！");
    }

    /**
     * 执行SQL创建用户登录日志表
     */
    @Test
    void executeCreateCustomerLoginLogTable() throws IOException {
        // 读取SQL文件
        ClassPathResource resource = new ClassPathResource("create_customer_login_log.sql");
        String sql = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        
        System.out.println("执行SQL语句:");
        System.out.println(sql);
        
        // 执行SQL
        jdbcTemplate.execute(sql);
        
        System.out.println("\n✅ SQL执行成功，用户登录日志表创建完成！");
    }
}