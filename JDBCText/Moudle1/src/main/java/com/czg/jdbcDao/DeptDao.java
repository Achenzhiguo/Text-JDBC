package com.czg.jdbcDao;

import com.czg.pojo.Dept;

import java.util.List;

/**
 * @Auther: erdongchen
 * @Date: 2022/5/2 - 05 - 02 - 16:35
 * @Description: com.czg.jdbcDao
 * @version: 1.0
 */
public interface DeptDao {

    int addDept(Dept dept);

    List<Dept> findAll();

}
