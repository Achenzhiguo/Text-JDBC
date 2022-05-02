package com.czg.pojo;

/**
 * @Auther: erdongchen
 * @Date: 2022/5/2 - 05 - 02 - 10:45
 * @Description: com.czg.pojo
 * @version: 1.0
 */
public class User {

    private Integer id;
    private String neme;
    private String loc;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", neme='" + neme + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public User() {
    }

    public User(Integer id, String neme, String loc) {
        this.id = id;
        this.neme = neme;
        this.loc = loc;
    }
}
