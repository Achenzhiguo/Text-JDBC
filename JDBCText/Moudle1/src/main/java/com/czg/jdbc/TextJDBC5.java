package com.czg.jdbc;

import java.sql.*;

/**
 * 查询
 *
 * @Auther: erdongchen
 * @Date: 2022/4/30 - 04 - 30 - 17:22
 * @Description: com.czg.jdbc
 * @version: 1.0
 */
public class TextJDBC5 {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user = "root";
    private static String password = "root";

    public static void main(String[] args) {
        //查询全部员工信息
        textQuary();
    }

    /**
     * 查询全部员工信息
     */
    public static void textQuary(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet =null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            String sql = "select * from emp";
            resultSet = statement.executeQuery(sql);

            //这里不萌直接打印，必须判断以下，如果单条打印，可以给出每一个字段的索引
            while(resultSet.next()){
                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int mgr = resultSet.getInt("mgr");
                Date hiredate = resultSet.getDate("hiredate");
                double sal = resultSet.getDouble("sal");
                double comm = resultSet.getDouble("comm");
                int deptno = resultSet.getInt("deptno");
                System.out.println(""+empno+""+ename+""+job+""+mgr+""+hiredate+""+sal+""+comm+""+deptno);
            }
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
