package com.czg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 删除和修改部门信息的方法实现
 *      只需要更改sql语句即可
 *
 * @Auther: erdongchen
 * @Date: 2022/4/30 - 04 - 30 - 16:49
 * @Description: com.czg.jdbc
 * @version: 1.0
 */
public class TextJDBC4 {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user = "root";
    private static String password = "root";

    public static void main(String[] args) {
        //既然只有sql语句不同，其他的都相同，那么就可以简单抽取出一个方法来执行

//        //修改部门信息
//        textUpdate();
        //删除部门信息
        textDelete();
    }

    /**
     * 修改部门信息
     */
    public static void textUpdate(){
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            String sql = "update dept set deptno = 50 where deptno = 70;";
            int rows = statement.executeUpdate(sql);
            System.out.println("影响的行数："+rows);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(null!=statement){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null!= connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除部门信息
     */
    private static void textDelete() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            String sql = "delete from dept where deptno = 50;";
            int rows = statement.executeUpdate(sql);
            System.out.println("影响的行数："+rows);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(null!=statement){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null!= connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
