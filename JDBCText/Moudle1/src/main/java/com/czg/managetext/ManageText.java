package com.czg.managetext;

import com.czg.jdbcDao.DeptDao;
import com.czg.jdbcDao.EmpDao;
import com.czg.jdbcDao.impl.DeptDaoImpl;
import com.czg.jdbcDao.impl.EmpDaoImpl;
import com.czg.pojo.Dept;
import com.czg.pojo.Emp;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * @Auther: erdongchen
 * @Date: 2022/5/3 - 05 - 03 - 2:00
 * @Description: com.czg.noting
 * @version: 1.0
 */

public class ManageText {
        private static Scanner sc = new Scanner(System.in);
        private static EmpDao empDao = new EmpDaoImpl();
        private static DeptDao deptDao = new DeptDaoImpl();
        private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ;

        public static void main(String[] args) {
            while (true) {
                showMenu();
                System.out.println("请录入选项");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        case1();
                        break;
                    case 2:
                        case2();
                        break;
                    case 3:
                        case3();
                        break;
                    case 4:
                        case4();
                        break;
                    case 5:
                        case5();
                        break;
                    case 6:
                        case6();
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("请正确输入选项");
                }
            }
        }

        private static void case1() {
            List<Emp> emps = empDao.findAll();
            emps.forEach(System.out::println);
        }

        private static void case2() {
            List<Dept> depts = deptDao.findAll();
            depts.forEach(System.out::println);
        }

        private static void case3() {
            System.out.println("请输入要删除的员工编号");
            int empno = sc.nextInt();
            empDao.textDelete(empno);
        }

        private static void case4() {
            System.out.println("请输入员工编号");
            int empno = sc.nextInt();
            System.out.println("请输入员工姓名");
            String ename = sc.next();
            System.out.println("请输入员工职位");
            String job = sc.next();
            System.out.println("请输入员工上级");
            int mgr = sc.nextInt();
            System.out.println("请输入员工入职日期,格式为yyyy-MM-dd");
            Date hiredate = null;
            try {
                hiredate = simpleDateFormat.parse(sc.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("请输入员工工资");
            double sal = sc.nextDouble();
            System.out.println("请输入员工补助");
            double comm = sc.nextDouble();
            System.out.println("请输入员工部门号");
            int deptno = sc.nextInt();
            Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
            empDao.textUpdate(emp);
        }

        private static void case5() {
            System.out.println("请输入员工编号");
            int empno = sc.nextInt();
            System.out.println("请输入员工姓名");
            String ename = sc.next();
            System.out.println("请输入员工职位");
            String job = sc.next();
            System.out.println("请输入员工上级");
            int mgr = sc.nextInt();
            System.out.println("请输入员工入职日期,格式为yyyy-MM-dd");
            Date hiredate = null;
            try {
                hiredate = simpleDateFormat.parse(sc.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("请输入员工工资");
            double sal = sc.nextDouble();
            System.out.println("请输入员工补助");
            double comm = sc.nextDouble();
            System.out.println("请输入员工部门号");
            int deptno = sc.nextInt();
            Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
            empDao.textAdd(emp);
        }

        private static void case6() {
            System.out.println("请录入部门号");
            int deptno = sc.nextInt();
            System.out.println("请录入部门名称");
            String dname = sc.next();
            System.out.println("请录入部门位置");
            String loc = sc.next();
            Dept dept = new Dept(deptno, dname, loc);
            deptDao.addDept(dept);
        }

        public static void showMenu() {
            System.out.println("************************************");
            System.out.println("* 1 查看所有员工信息");
            System.out.println("* 2 查看所有部门信息");
            System.out.println("* 3 根据工号删除员工信息");
            System.out.println("* 4 根据工号修改员工信息");
            System.out.println("* 5 增加员工信息");
            System.out.println("* 6 增加部门信息");
            System.out.println("* 7 退出");
            System.out.println("************************************");
        }


}
