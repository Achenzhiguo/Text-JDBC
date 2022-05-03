package com.czg.jdbcDao.impl;

import com.czg.jdbcDao.BaseDao;
import com.czg.jdbcDao.DeptDao;
import com.czg.pojo.Dept;

import java.util.List;

/**deptdao的实现类
 *
 * @Auther: erdongchen
 * @Date: 2022/5/3 - 05 - 03 - 1:54
 * @Description: com.czg.jdbcDao.impl
 * @version: 1.0
 */
public class DeptDaoImpl extends BaseDao implements DeptDao {
    @Override//jdk1.5是不用写的,修改级别为1.8
    public int addDept(Dept dept) {
        String sql = "insert into dept set deptno = ? ,dname = ? loc = ?";
        return baseUpdate(sql,dept.getDeptno(),dept.getDname(),dept.getLoc());
    }
    @Override
    public List<Dept> findAll() {
        String sql = "select * from dept";
        return baseQuary(Dept.class,sql);
    }
}
