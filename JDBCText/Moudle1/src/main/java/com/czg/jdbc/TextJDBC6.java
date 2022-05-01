package com.czg.jdbc;

import com.czg.pojo.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 查询员工信息并封装进对象
 *
 * @Auther: erdongchen
 * @Date: 2022/5/1 - 05 - 01 - 10:24
 * @Description: com.czg.jdbc
 * @version: 1.0
 */
public class TextJDBC6 {

    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user = "root";
    private static String password = "root";

    public static void main(String[] args) {
        //查询全部员工信息
        List<Emp> emps = textQuary();//这里的调用方法小细节，静态方法只能调静态方法，注意static关键字的使用
        //简单集合的遍历

      /*
        for (int i = 0; i <emps.size() ; i++) {
            System.out.println(emps.get(i));
        }
        */

         //使用迭代器遍历集合

        /*
        Iterator<Emp> iterator = emps.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
       */

        //增强for循环遍历
        for (Emp emp : emps){
            System.out.println(emp);
        }
    }

    /**
     * 查询全部员工信息
     */
    public static List<Emp> textQuary(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet =null;
        List<Emp> list = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            String sql = "select * from emp";
            resultSet = statement.executeQuery(sql);
            list = new ArrayList<Emp>();
            while(resultSet.next()){
                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int mgr = resultSet.getInt("mgr");
                Date hiredate = resultSet.getDate("hiredate");//日期推介java.util.Data
                double sal = resultSet.getDouble("sal");
                double comm = resultSet.getDouble("comm");
                int deptno = resultSet.getInt("deptno");

                Emp emp = new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
                list.add(emp);
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
            return list;//方法是有返回值的，注意return的位置
        }
    }
}
