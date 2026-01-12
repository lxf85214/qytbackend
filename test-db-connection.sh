#!/bin/bash

# 数据库连接测试脚本

echo "正在测试数据库连接..."
echo "数据库URL: jdbc:mysql://localhost:3306/qyt"
echo "用户名: root"
echo ""

# 使用Java直接测试连接
java -cp "target/classes:$(./mvnw dependency:build-classpath -q -DincludeScope=runtime 2>/dev/null | tail -1)" \
  -Djava.util.logging.config.file=/dev/null \
  <<'JAVA_CODE'
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/qyt?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "!Yzh1985151";
        
        try {
            System.out.println("正在连接数据库...");
            Connection conn = DriverManager.getConnection(url, username, password);
            
            if (conn != null && !conn.isClosed()) {
                DatabaseMetaData metaData = conn.getMetaData();
                System.out.println("✅ 数据库连接成功！");
                System.out.println("数据库产品: " + metaData.getDatabaseProductName());
                System.out.println("数据库版本: " + metaData.getDatabaseProductVersion());
                System.out.println("驱动名称: " + metaData.getDriverName());
                System.out.println("驱动版本: " + metaData.getDriverVersion());
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("❌ 数据库连接失败！");
            System.out.println("错误信息: " + e.getMessage());
            System.exit(1);
        }
    }
}
JAVA_CODE
