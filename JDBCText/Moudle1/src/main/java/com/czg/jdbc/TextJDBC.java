package com.czg.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @Auther: erdongchen
 * @Date: 2022/4/30 - 04 - 30 - 13:53
 * @Description: com.czg.jdbc
 * @version: 1.0
 */
public class TextJDBC {

    //这是一个main方法，主程序的入口 ：
    public static void main(String[] args) throws Exception{//开始阶段，直接抛出异常，不要让异常影响代码的实现，后期优化

        /*实现一个需求：像dept表中添加一条数据*/

        //1、加载驱动
        /*  这里的Driver是一个Java.sql包下的一个接口，不能直接new对象，只能找他的实现子类
            Driver driver = new com.mysql.cj.jdbc.Driver()
            点进这个接口找他的实现子类，com.mysql.cj.jdbc.Driver就是他的一个实现子类。
        * */
        Driver driver = new com.mysql.cj.jdbc.Driver();
        //2、注册驱动
        DriverManager.registerDriver(driver);
        //3、获取连接Connection
        /*
        * user:用户名
        * password:密码
        * url:统一资源定位符，定位链接数据库的  （就是一个网址的格式：https://127.0.1:8080/indext.html?参数1=值&参数2=值...）
        *   协议：             jdbc:mysql
        *   IP:                127.0.0.1/localhost
        *   端口:               3306
        *   数据库名字:          mysql80
        *   参数:               常用的参数及含义
        *           UseSSL=false                        是否使用SSL认证机制，不使用设置为false
        *           useUnicode=true                     是否使用Unicode字符集，使用设置为true
        *           characterEncoding=UTF-8             指定使用UTF-8
        *           serverTimezone=Asia/Shanghai        指定时区为亚洲上海       或者可以这样些serverTimezone=GMT%2B8
        *
        *
        *整体url结构：   协议：//IP：端口/资源路径？参数名= 参数值&参数名=参数值&参数名=参数值&...
        *          jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
        * */
        String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url,user,password);
        //4、获取语句对象
        Statement statement = connection.createStatement();

        //5、执行sql语句，返回结果
        String sql = "insert into dept values(50,'财务部','西安')";
        /**
         * update,delete,insert等增加，删除，插入都是调取executeUpdate()方法；
         * select 查询调用的是executeQuary()方法；
         */
        int rows = statement.executeUpdate(sql);
        System.out.println("影响的行数："+rows);

        //6、关闭连接
        //注意关闭连接的顺序，先开后关
        statement.close();
        connection.close();

    }
}
