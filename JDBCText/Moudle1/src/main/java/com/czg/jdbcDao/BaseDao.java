package com.czg.jdbcDao;
import com.czg.pojo.Emp;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽取总结：
 * 1，如果某个属性或者多个属性,被多次在语句块中使用
 *          向上提取:定义类型并赋初始值
 * 2.如果某个属性被多个方法重复使用
 *          向上提取至成员变量并赋初始值.如果不行更改属性值,private static final修饰
 * 3,如果某些代码块被多个方法使用,自身只是传入参数个数,或者类型不同,
 *          向上提取为公共方法,调用者只需关注参数个数和类型即可
 * 4,如果某些属性和方法被多次重复使用,自身只是多了一些很少的自己的参数和方法
 *          向上提取为父类,直接继承自父类即可使用父类的属性和方法
 *              接口也同理
 *
 * @Auther: erdongchen
 * @Date: 2022/5/3 - 05 - 03 - 1:35
 * @Description: com.czg.jdbcDao
 * @version: 1.0
 */
public class BaseDao {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user = "root";
    private static String password = "root";

    //    String sql = "select * from emp where ename like ? ";
    //查询方法不猛和增删改通用,因为返回值类型不同,但是不知道具体要查询的类,那么就把这个类的字节码文件也穿进去,利用反射读取属性,
    public List baseQuary(Class clazz, String sql, Object...args){
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet =null;
        List list = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            list = new ArrayList();

            //获取字节码文件的属性
            //设置属性可访问
            Field[] fields = clazz.getDeclaredFields();
            for (Field field :fields){
                field.setAccessible(true);
            }

            while(resultSet.next()){
                // 通过反射创建对象
                Object obj = clazz.newInstance();//默认在通过反射调用对象的空参构造方法
                for (Field field : fields) {// 临时用Field设置属性
                    String fieldName = field.getName();// empno  ename job .... ...
                    Object data = resultSet.getObject(fieldName);
                    field.set(obj,data);
                }
                list.add(obj);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
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
        return list;

    }


    //增删改中只有sql语句和参数不同,其余都是不变得,那么久可继续封装成一个方法,直接调用
    public int baseUpdate(String sql,Object...args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rows = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            rows = preparedStatement.executeUpdate();//这里不需要也不能传入sql,会报错
            System.out.println(rows);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows;
    }
}