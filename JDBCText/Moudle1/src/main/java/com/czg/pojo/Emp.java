package com.czg.pojo;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Date;

/**
 * 封装实体类,将数据库查询到的结果封装成对象
 *                          永久保存
 *                          进行参数的传递
 * resultset这个集合只能临时查询数据，查询结束也就关闭了
 *
 *注意事项：
 *      实体类实现序列化接口----养成习惯
 *      实体类类名和数据可表名一样
 *      实体类属性名称和数据库的字段一一对应
 *      个数和列书一样
 *      属性类型和字段类型一致
 *      所有的属性设置私有
 *      属性类型建议写成包装类
 *      日期推介些java.util.Date
 *      所有的寿星必须有get和set方法
 *      必须具备空参构造器
 *
 *
 *
 *
 *
 *
 * @Auther: erdongchen
 * @Date: 2022/5/1 - 05 - 01 - 10:10
 * @Description: com.czg.pojo
 * @version: 1.0
 */
public class Emp implements Serializable {//自己封装的类习惯性的实现序列化接口，Serializable这个接口里面啥都没有，就是一个标识，可以来理解为一个通行证

    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public Emp() {
    }

    public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }
}


