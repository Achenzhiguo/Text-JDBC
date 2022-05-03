package com.czg.util;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 工具类
 * 读取配置文件中的数据
 *
 * @Auther: erdongchen
 * @Date: 2022/5/3 - 05 - 03 - 16:45
 * @Description: com.czg.util
 * @version: 1.0
 */
public class PropertiesUtil {

    private Properties properties;

    //定义一个构造方法,根据路径读取

    /*
    * 注意.properties文件的存储位置,
    *       如果是普通Java项目,放哪都行,以下这个方法就是相对灵活一点
    *       如果是maven项目与,那一定要放在resources目录下,否怎读取不到,报空指针异常
    *               如果想放在非resources目录下,那么请在pom.xml中进行相关的配置
    * */
    public PropertiesUtil (String path){
        properties = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义一个根据key取出配置文件中的数据的方法
    public String getProperties(String key){
        return properties.getProperty(key);
    }
}
