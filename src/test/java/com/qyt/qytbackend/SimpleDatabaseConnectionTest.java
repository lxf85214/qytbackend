package com.qyt.qytbackend;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 简单的数据库连接测试类（不依赖Spring Boot）
 *
 * @author qyt
 * @date 2024
 */
public class SimpleDatabaseConnectionTest {

    private static final String BASE_URL = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/qyttest?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "qyt";
    private static final String PASSWORD = "qyt";
    private static final String DATABASE_NAME = "qyttest";

    public static void main(String[] args) {
        System.out.println("========== 开始测试数据库连接 ==========");
        System.out.println("MySQL服务器: localhost:3306");
        System.out.println("目标数据库: " + DATABASE_NAME);
        System.out.println("用户名: " + USERNAME);
        System.out.println();

        try {
            // 加载MySQL驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL驱动加载成功");

            // 直接尝试连接到目标数据库
            System.out.println("正在连接到数据库 '" + DATABASE_NAME + "'...");
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            if (connection != null && !connection.isClosed()) {
                System.out.println("✅ 数据库连接成功！");
                System.out.println();

                // 获取数据库元数据
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("========== 数据库信息 ==========");
                System.out.println("数据库产品: " + metaData.getDatabaseProductName());
                System.out.println("数据库版本: " + metaData.getDatabaseProductVersion());
                System.out.println("驱动名称: " + metaData.getDriverName());
                System.out.println("驱动版本: " + metaData.getDriverVersion());
                System.out.println("连接URL: " + metaData.getURL());
                System.out.println("用户名: " + metaData.getUserName());
                System.out.println("===================================");
                System.out.println();

                // 测试简单查询
                try {
                    var queryStmt = connection.createStatement();
                    var queryRs = queryStmt.executeQuery("SELECT DATABASE() AS db, VERSION() AS version");
                    if (queryRs.next()) {
                        System.out.println("当前数据库: " + queryRs.getString("db"));
                        System.out.println("MySQL版本: " + queryRs.getString("version"));
                    }
                    queryRs.close();
                    queryStmt.close();
                    System.out.println("✅ 数据库查询测试成功！");
                } catch (SQLException e) {
                    System.out.println("⚠️  查询测试失败: " + e.getMessage());
                }

                // 关闭连接
                connection.close();
                System.out.println("✅ 连接已正确关闭");
                System.out.println();
                System.out.println("========== 测试完成 ==========");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL驱动未找到！");
            System.err.println("错误信息: " + e.getMessage());
            System.err.println("请确保mysql-connector-j依赖已正确下载");
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("❌ 数据库连接失败！");
            System.err.println("错误代码: " + e.getErrorCode());
            System.err.println("SQL状态: " + e.getSQLState());
            System.err.println("错误信息: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("❌ 发生未知错误！");
            System.err.println("错误信息: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
