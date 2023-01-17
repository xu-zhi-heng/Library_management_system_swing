package utils;

import java.sql.*;
public class DBHelper {
    // 完成数据访问 【1加载数据库驱动 2创建数据库连接】放到本类 【3 准备sql 4 执行sql】放到dao中 5数据加工   【6关闭数据库的相关对象】
    private static String driverClass = "com.mysql.cj.jdbc.Driver";
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    // private static String user="root";
    // private static String password="root";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (null == connection) {       //单例模式
            // 1加载数据库驱动
            try {
                Class.forName(driverClass).newInstance();
                // 2获得数据库连接
                connection = DriverManager.getConnection(jdbcUrl, "root", "root");

            } catch (Exception e) {
                System.out.println("数据库连接失败！");
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(DBHelper.getConnection());
    }

    public static void closeALl(Connection conn, Statement st, ResultSet rs) {
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 * jdbc.driverClass=com.mysql.cj.jdbc.Driver
 * jdbc.jdbcUrl=jdbc:mysql://localhost:3306/ssm_crud?useUnicode=true&
 * characterEncoding=utf8&serverTimezone=UTC jdbc.user=root jdbc.password=root
 */