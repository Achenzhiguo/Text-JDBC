package com.czg.pojo;

import java.io.Serializable;

/**事务练习转账表
 *
 * @Auther: erdongchen
 * @Date: 2022/5/2 - 05 - 02 - 11:18
 * @Description: com.czg.pojo
 * @version: 1.0
 */
public class MyAccount implements Serializable {
    private Integer id;
    private String uname;
    private Double balance;


    @Override
    public String toString() {
        return "MyAccount{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public MyAccount(Integer id, String uname, Double balance) {
        this.id = id;
        this.uname = uname;
        this.balance = balance;
    }

    public MyAccount() {
    }
}