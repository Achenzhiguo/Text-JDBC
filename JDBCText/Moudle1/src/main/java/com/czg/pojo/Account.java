package com.czg.pojo;

import java.io.Serializable;

/**
 * @Auther: erdongchen
 * @Date: 2022/5/1 - 05 - 01 - 14:50
 * @Description: com.czg.pojo
 * @version: 1.0
 */
public class Account implements Serializable {

    private  String username;
    private  String password;
    private  Double balance;


    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account(String username, String password, Double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public Account() {
    }
}

