package com.czg.jdbc2;

import sun.dc.pr.PRError;

import java.sql.*;

/**
 * 批量插入数据
 *
 * @Auther: erdongchen
 * @Date: 2022/5/2 - 05 - 02 - 10:29
 * @Description: com.czg.jdbc2
 * @version: 1.0
 */
public class Text2 {

    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&CachePrepStmts=true";
    private static String user = "root";
    private static String password = "root";

    //这是一个main方法，主程序的入口 ：
    public static void main(String[] args) {
        textAddBatch();
    }

    private static int textAddBatch() {
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet =null;
        int i = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            String sql = "insert into user values(default ,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            for (i = 1; i <10001 ; i++) {
                preparedStatement.setString(1,"name");
                preparedStatement.setString(2,"loc");
                preparedStatement.addBatch();//将修改的放入一个批次中
                if(i%1000==0){//这样鞋是可以设置回滚点,若未提交成功可以在这设置回滚点
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();//清楚批处理中的数据
                }
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            System.out.println("批量提交了");
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
        }
      return i;
    }


}
