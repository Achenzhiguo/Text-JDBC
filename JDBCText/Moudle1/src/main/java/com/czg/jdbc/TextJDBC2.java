package com.czg.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 优化TextJDBC版本2：驱动的加载---反射来获取驱动
 *      1、根据第一个项目于导包，创建实例时我们得知、
 *              接口是由jdk提供，实现子类是在我们引入的以依赖中实现的
 *
 * @Auther: erdongchen
 * @Date: 2022/4/30 - 04 - 30 - 13:53
 * @Description: com.czg.jdbc
 * @version: 1.0
 */
public class TextJDBC2 {

    //这是一个main方法，主程序的入口 ：
    public static void main(String[] args) throws Exception{//开始阶段，直接抛出异常，不要让异常影响代码的实现，后期优化

        /*实现一个需求：像dept表中添加一条数据*/

        //1、通过反射来加载驱动   //2、注册驱动
        /*
        * 可以通过反射来加载驱动：点进去Driver类发现，已经创建并加载饿了驱动
        * class.forName(com.mysql.ci.jdbc.Driver);
            事实上，不屑也会加载注册
        * */
//        Driver driver = new com.mysql.cj.jdbc.Driver();
        Class.forName("com.mysql.cj.jdbc.Driver");


        //3、获取连接Connection

        String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url,user,password);
        //4、获取语句对象
        Statement statement = connection.createStatement();

        //5、执行sql语句，返回结果
        String sql = "insert into dept values(60,'财务部','西安')";
        /**
         * update,delete,insert等增加，删除，插入都是调取executeUpdate()方法；
         * select 查询调用的是executeQuary()方法；
         */
        int rows = statement.executeUpdate(sql);
        System.out.println("影响的行数："+rows);

        //6、关闭连接
        statement.close();
        connection.close();

    }
}
