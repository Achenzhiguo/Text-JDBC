package com.czg.jdbc2;

import java.sql.*;

/**
 * 事务相关
 * 手动控制事务
 * 回滚点,的设置
 *
 * @Auther: erdongchen
 * @Date: 2022/5/2 - 05 - 02 - 11:15
 * @Description: com.czg.jdbc2
 * @version: 1.0
 */
public class Text3 {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&CachePrepStmts=true";
    private static String user = "root";
    private static String password = "root";

    //这是一个main方法，主程序的入口 ：
    public static void main(String[] args) {
        textTrade();
    }

    private static String textTrade() {
        Connection connection = null;
        PreparedStatement preparedStatement= null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);

            /*
             * JDBC 默认是自动提交事务
             * 每条DML都是默认提交事务的,多个preparedStatement.executeUpdate();都会提交一次事务
             * 如果想手动控制事务,那么就不能让事务自动提交
             * 通过Connection对象控制connection.setAutoCommit(false);
             * 如果不设置 默认值为true,自动提交,设置为false之后就是手动提交了
             * 无论是否发生回滚,事务最终会一定要提交的 提交我们建议放在finally之中进行提交
             * 如果是转账的过程中出现异常了,那么我们就要执行回滚,回滚操作应该方法catch语句块中
             *
             * */
            //手动关闭事务的自动提交
            connection.setAutoCommit(false);
            String sql = "update myaccount set balance =balance- ? where id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            //张三转出100
            preparedStatement.setDouble(1,100);
            preparedStatement.setInt(2,1);
            preparedStatement.executeUpdate();

//            //手动抛出异常
            int i= 1/0;
            //李四得到100
            preparedStatement.setDouble(1,-100);
            preparedStatement.setInt(2,2);
            preparedStatement.executeUpdate();


        } catch (Exception e) {//注意这里的回滚点,一定是设置在出异常跳进的那个异常出,如果异常走的是其他的语句,那你这个回滚点就等于没设置
            if(null != connection){
                try {
                    connection.rollback();//再抛出异常后的语句中设置回滚点
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        } finally {
            if(null!=connection){
                try {
                    connection.commit();//是否异常出现,都要进行提交,放在finnaly语句块中
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
        }
        return "zhixing";//随便给了个值,防止报错,主要是观察事务回滚及提交,
    }



}
