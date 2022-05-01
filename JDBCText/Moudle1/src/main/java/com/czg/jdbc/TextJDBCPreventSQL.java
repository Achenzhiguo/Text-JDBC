package com.czg.jdbc;

import com.czg.pojo.Account;

import java.sql.*;
import java.util.Scanner;

/**
 * 使用预编译preparestatement对象阿来防止sql注入
 *
 * @Auther: erdongchen
 * @Date: 2022/5/1 - 05 - 01 - 16:05
 * @Description: com.czg.jdbc
 * @version: 1.0
 */
public class TextJDBCPreventSQL {
    //URL中新增了两个参数,useServerPrepStmts=true   &  CachePrepStmts=true
    //                          开启预编译               使用预编译缓存
    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&CachePrepStmts=true";
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
        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        Account account = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);

            /*
            Statement和PreparedStatment的关系和区别:
                关系：public interface PreparedStatement extends Statement
                区别
                    PreparedStatment安全性高,可以避免SQL注入
                    PreparedStatment简单不繁琐,不用进行字符串拼接
                    PreparedStatment性能高，用在执行多个相同数据库DML操作时,可以减少sql语句的编译次数
            */
            String sql="select * from account where username = ? and password = ?";
//            statement = connection.createStatement();
            //获取预编译对象
            preparedStatement = connection.prepareStatement(sql);//这里要先传入sql
            //prestatement对象先设置值再调用执行
            preparedStatement.setString(1,uname);
            preparedStatement.setString(2,pwd);
            resultSet = preparedStatement.executeQuery();//已经传入sql,无需再传
            System.out.println(sql);

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
            if(null!=preparedStatement){
                try {
                    preparedStatement.close();
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
