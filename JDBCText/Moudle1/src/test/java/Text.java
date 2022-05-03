import com.czg.jdbcDao.MyConnectionPoor;

import java.sql.Connection;

/**
 * @Auther: erdongchen
 * @Date: 2022/5/3 - 05 - 03 - 18:06
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */


public class Text {
    //这是一个main方法，主程序的入口 ：
    public static void main(String[] args) {
        Connection connection = MyConnectionPoor.getConnection();
    }

}
