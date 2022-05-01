package com.czg.jdbc2;

import com.czg.pojo.Emp;
import org.omg.CORBA.INTERNAL;

import javax.xml.bind.annotation.XmlType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用preparestatement实现CRUD
 *
 * @Auther: erdongchen
 * @Date: 2022/5/1 - 05 - 01 - 16:53
 * @Description: com.czg.jdbc2
 * @version: 1.0
 */
public class Text1 {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user = "root";
    private static String password = "root";

    public static void main(String[] args) {

//        //增加一名员工信息
//       textInsert();

        //修改一名员工信息
//        textUpdat();

        //删除一个员工信息
//        textDelete();

        //查询全部员工信息
        textQuary();

    }

    /**
     * 删除员工信息
     * @return
     */
    private static int textDelete() {
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet =null;
        int rows = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            String sql = "delete from emp where empno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,8008);
            rows = preparedStatement.executeUpdate();//这里不需要也不能传入sql,会报错
            System.out.println(rows);
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
        return rows;
    }

    /**
     * 更新员工信息
     * @return
     */
    private static int textUpdat() {
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet =null;
        int rows = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            String sql = "update emp set empno =? where ename =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,9001);
            preparedStatement.setString(2,"lisi");

            rows = preparedStatement.executeUpdate();//这里不需要也不能传入sql,会报错
            System.out.println(rows);
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
        return rows;
    }

    /**
     * 增加一个员工的信息
     * @return
     */
    private static int textInsert() {
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet =null;
        int rows = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            String sql = "insert into Emp values(?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,9000);
            preparedStatement.setString(2,"lisi");
            preparedStatement.setString(3,"商务");
            preparedStatement.setInt(4,30);
            preparedStatement.setDate(5,new Date(System.currentTimeMillis()));
            preparedStatement.setDouble(6,1600);
            preparedStatement.setDouble(7,200.22);
            preparedStatement.setInt(8,30);

            rows = preparedStatement.executeUpdate();//这里不需要也不能传入sql,会报错
            System.out.println(rows);
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
        return rows;
    }

    /**
     * 查询名字里带A的员工
     */
    public static void textQuary(){
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet =null;
        List<Emp> list = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            String sql = "select * from emp where ename like ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%A%");
            resultSet = preparedStatement.executeQuery();
            list = new ArrayList<Emp>();

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
                Emp emp = new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
                list.add(emp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(null!=resultSet){//官弁resultSet----------这里其实不用关闭也可以，statemet关闭也会吧resUltset关闭掉
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
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
}
