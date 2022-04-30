package com.czg.jdbc;

import com.sun.org.apache.bcel.internal.generic.IFNULL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 异常处理
 *
 * @Auther: erdongchen
 * @Date: 2022/4/30 - 04 - 30 - 16:08
 * @Description: com.czg.jdbc
 * @version: 1.0
 */
public class TextJDBC3  {
        //提取参数至成员变量处，并设为私有静态
       private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
       private static String user = "root";
       private static String password = "root";

    public static void main(String[] args) {
        //不抛异常，手动捕获
        /*
        * 处理异常的方法
        *       一般是吧可能出异常的代码提放在try{}中，用catch来捕获
        *       如果有关闭流的操作，或者，不管代码是否出错都要执行的语句，再加上finally{}
        *       关闭流操作，为了防止对象为空的调用关闭错误，最好再执行前，加一层非空的判断
        * */

        //statment和connection对象在try{}语块中，那么在finally{}中是无法调用的，
        // 需要将属性声明于try方法外,且赋值为null
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            String sql = "insert into dept values(70,'财务部','深圳')";
            int rows = statement.executeUpdate(sql);
            System.out.println("影响的行数："+rows);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(null!=statement){//如果connction获取连接出异常，那么statement就为null,再执行关闭就会报错，所以有必要判断一下
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null!= connection){//这里也是一样的道理
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
