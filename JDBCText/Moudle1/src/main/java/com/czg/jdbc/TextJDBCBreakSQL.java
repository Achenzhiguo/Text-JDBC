package com.czg.jdbc;

import com.czg.pojo.Account;
import com.czg.pojo.Emp;
import jdk.nashorn.internal.ir.ReturnNode;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * 演示SQL注入攻击
 *      攻击的要点，通过字符串对SQL语句进行破坏
 *
 * @Auther: erdongchen
 * @Date: 2022/5/1 - 05 - 01 - 14:36
 * @Description: com.czg.jdbc
 * @version: 1.0
 */
public class TextJDBCBreakSQL {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user = "root";
    private static String password = "root";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String uname = sc.next();
        System.out.println("请输入密码");
        String pwd = sc.next();

        Account account = getAccount(uname,pwd);
        System.out.println(null!=account?"登录成功":"登录失败");
        System.out.println(account);
        sc.close();
    }
    public static Account  getAccount(String uname,String pwd){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet =null;
        Account account = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            String sql="select * from account where username ='"+uname+"' and password ='"+pwd+"'";
//            String sql = "select * from account whwere username = ' "+uname+"' and password = '"+pwd+"'";
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Double balance = resultSet.getDouble("balance");
                account = new Account(username,password,balance);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(null!=resultSet){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

        }return account;
    }

}
