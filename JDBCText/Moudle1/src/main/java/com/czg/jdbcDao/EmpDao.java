package com.czg.jdbcDao;

import com.czg.pojo.Emp;

import java.util.List;

/**
 * @Auther: erdongchen
 * @Date: 2022/5/2 - 05 - 02 - 16:35
 * @Description: com.czg.jdbcDao
 * @version: 1.0
 */
public interface EmpDao {
    /**
     * 增加一条数据值emp表
     * @param emp
     * @return
     */
    int textAdd(Emp emp);

    /**
     * 修改信息
     * @param emp
     * @return
     */
    int textUpdate(Emp emp);

    /**
     * 根据员工编号删除信息
     * @param empno
     * @return
     */
    int textDelete(int empno);

    /**
     * 查询员工信息
     * @return
     */
    List<Emp> findAll();
}

