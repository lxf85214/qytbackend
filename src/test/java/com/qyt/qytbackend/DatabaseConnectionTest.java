package com.qyt.qytbackend;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 数据库连接测试类
 *
 * @author qyt
 * @date 2024
 */
@Slf4j
@SpringBootTest
class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    /**
     * 测试数据库连接
     */
    @Test
    void testDatabaseConnection() {
        assertNotNull(dataSource, "数据源不能为空");

        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "数据库连接不能为空");
            assertFalse(connection.isClosed(), "数据库连接应该是打开的");

            // 获取数据库元数据
            DatabaseMetaData metaData = connection.getMetaData();
            String databaseProductName = metaData.getDatabaseProductName();
            String databaseProductVersion = metaData.getDatabaseProductVersion();
            String driverName = metaData.getDriverName();
            String driverVersion = metaData.getDriverVersion();
            String url = metaData.getURL();
            String username = metaData.getUserName();

            log.info("========== 数据库连接信息 ==========");
            log.info("数据库产品: {}", databaseProductName);
            log.info("数据库版本: {}", databaseProductVersion);
            log.info("驱动名称: {}", driverName);
            log.info("驱动版本: {}", driverVersion);
            log.info("连接URL: {}", url);
            log.info("用户名: {}", username);
            log.info("===================================");

            // 验证是MySQL数据库
            assertTrue(databaseProductName.contains("MySQL"), "应该是MySQL数据库");

            log.info("✅ 数据库连接测试成功！");

        } catch (SQLException e) {
            log.error("❌ 数据库连接测试失败！", e);
            fail("数据库连接失败: " + e.getMessage());
        }
    }

    /**
     * 测试数据库连接池
     */
    @Test
    void testConnectionPool() {
        assertNotNull(dataSource, "数据源不能为空");

        try {
            // 测试获取连接
            Connection connection1 = dataSource.getConnection();
            assertNotNull(connection1, "第一个连接不能为空");
            log.info("✅ 成功获取第一个连接");

            Connection connection2 = dataSource.getConnection();
            assertNotNull(connection2, "第二个连接不能为空");
            log.info("✅ 成功获取第二个连接");

            // 关闭连接
            connection1.close();
            connection2.close();
            log.info("✅ 连接已正确关闭");

        } catch (SQLException e) {
            log.error("❌ 连接池测试失败！", e);
            fail("连接池测试失败: " + e.getMessage());
        }
    }
}
