package com.czg.jdbcDao.impl;

import com.czg.jdbcDao.BaseDao;
import com.czg.jdbcDao.EmpDao;
import com.czg.pojo.Emp;

import java.util.List;

/**
 *
 * EmpDao的实现类
 *
 * @Auther: erdongchen
 * @Date: 2022/5/2 - 05 - 02 - 16:37
 * @Description: com.czg.jdbcDao.impl
 * @version: 1.0
 */
public class EmpDaoImpl extends BaseDao implements EmpDao {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mysql80?UseSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user = "root";
    private static String password = "root";


   @Override
    public int textAdd(Emp emp) {//baseUpdate方法中,参数列表中定义的是可变参数,为的就是兼容对象,把属性拆解开来
        String sql ="insert into Emp values(?,?,?,?,?,?,?,?)";
        return baseUpdate(sql,emp.getEmpno(),emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno());
    }
    @Override
    public int textUpdate(Emp emp) {
        String sql = "update emp set empno=?,ename=?,job=?,mgr= ?,hiredate=?,sal= ?,comm= ?,deptno=? where empno= ?";
        return baseUpdate(sql,emp.getEmpno(),emp.getEname(), emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno(),emp.getEmpno());
    }
    @Override
    public int textDelete(int empno) {
        String sql = "delete from emp where empno = ?";
        return baseUpdate(sql,empno);
    }

    @Override
    public List<Emp> findAll() {//因为返回值类型不同,或者说不知道查询那个具体表,所以这里将具体的字节码文件也传入,通过反射获取对象,属性
        String sql = "select * from emp";
        return baseQuary(Emp.class,sql);

    }


}