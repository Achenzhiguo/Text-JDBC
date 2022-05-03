package com.czg.jdbcDao;

import com.czg.util.PropertiesUtil;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 定义一个连接池
 *
 * @Auther: erdongchen
 * @Date: 2022/5/3 - 05 - 03 - 16:41
 * @Description: com.czg.jdbcDao
 * @version: 1.0
 */
public class MyConnectionPoor {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static int initSize;
    private static int maxSize;
    private static LinkedList<Connection> pool;
    private static Logger logger;

    static{//静态代码块读取配置文件中的信息
        logger = Logger.getLogger(MyConnectionPoor.class);
        PropertiesUtil propertiesUtil = new PropertiesUtil("/jdbc.properties");
        driver = propertiesUtil.getProperties("driver");
        url = propertiesUtil.getProperties("url");
        user = propertiesUtil.getProperties("user");
        password = propertiesUtil.getProperties("password");
        initSize = Integer.parseInt(propertiesUtil.getProperties("initSize"));
        maxSize = Integer.parseInt(propertiesUtil.getProperties("maxSize"));
        // 加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.fatal("加载驱动出异常了"+driver,e);
        }
        // 初始化pool
        pool=new LinkedList<Connection>();
        // 创建5个链接对象
        for (int i = 0; i <initSize ; i++) {
            Connection connection = initConnection();
            if(null != connection){
                pool.add(connection);
                logger.info("初始化连接"+connection.hashCode()+"放入连接池");
            }
        }
    }
    // 私有的初始化一个链接对象的方法
    private static Connection initConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            logger.error("链接获取失败",e);
        }
        return null;
    }
    // 共有的向外界提供链接对象的
    public static Connection getConnection(){
        Connection connection =null;
        if(pool.size()>0){
            connection= pool.removeFirst();// 移除集合中的第一个元素
            logger.debug("连接池中还有连接:"+connection.hashCode());
        }else{
            connection = initConnection();
            logger.debug("连接池空,创建新连接:"+connection.hashCode());
        }
        return connection;
    }
    // 共有的向连接池归还连接对象的方法
    public static void returnConnection(Connection connection){
        if(null != connection){
            try {
                if(!connection.isClosed()){
                    if(pool.size()<maxSize){
                        try {
                            connection.setAutoCommit(true);// 调整事务状态
                            logger.debug("设置连接:"+connection.hashCode()+"自动提交为true");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        pool.addLast(connection);
                        logger.warn("连接池未满,归还连接:"+connection.hashCode());
                    }else{
                        try {
                            connection.close();
                            logger.info("连接池满了,关闭连接:"+connection.hashCode());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    logger.warn("连接:"+connection.hashCode()+"已经关闭,无需归还");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            logger.warn("传入的连接为null,不可归还");
        }
    }

}
