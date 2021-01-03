package com.lhq.bean;

import java.util.Date;

public class User {
    private Integer uid;
    private String username;
    private String password;
    private String name;
    private String telephone;
    private String cardID;
    private String address;
    private Date addtimes;

    public User() {
    }

    public User(Integer uid, String username, String password, String name, String telephone, String cardID, String address, Date addtimes) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.cardID = cardID;
        this.address = address;
        this.addtimes = addtimes;
    }

    public User(Integer uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAddtimes() {
        return addtimes;
    }

    public void setAddtimes(Date addtimes) {
        this.addtimes = addtimes;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", cardID='" + cardID + '\'' +
                ", address='" + address + '\'' +
                ", addtimes='" + addtimes + '\'' +
                '}';
    }
}
