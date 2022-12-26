package com.yzm.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public enum JDBCUtils {
    INSTANCE;

    private static final String url = "jdbc:mysql://192.168.192.128:3306/mybatis?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "1234";

    private static Connection connection;

    static {
        try {
            // 注册驱动，MySQl 5之后，可以省略注册驱动的步骤
//            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
