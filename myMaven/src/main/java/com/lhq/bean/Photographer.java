package com.lhq.bean;

import java.util.Date;

public class Photographer {
    private Integer pid;
    private String username;
    private String password;
    private String name;
    private String telephone;
    private Date addtimes;
    private String introduction;

    public Photographer() {
    }

    public Photographer(Integer pid, String username, String password, String name, String telephone, Date addtimes, String introduction) {
        this.pid = pid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.addtimes = addtimes;
        this.introduction = introduction;
    }

    public Photographer(Integer pid, String username, String password) {
        this.pid = pid;
        this.username = username;
        this.password = password;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getAddtimes() {
        return addtimes;
    }

    public void setAddtimes(Date addtimes) {
        this.addtimes = addtimes;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Photographer{" +
                "pid='" + pid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", addtimes='" + addtimes + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
