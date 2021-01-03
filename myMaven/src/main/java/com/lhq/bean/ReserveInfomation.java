package com.lhq.bean;

import java.util.Date;

public class ReserveInfomation {
    private Integer lid;
    private Integer pid;
    private Integer uid;
    private String name;
    private String telephone;
    private String rtype;
    private String address;
    private Date times;
    private String texts;
    private String state;

    public ReserveInfomation() {
    }

    public ReserveInfomation(Integer lid, Integer pid, Integer uid, String name, String telephone, String rtype, String address, Date times, String texts, String state) {
        this.lid = lid;
        this.pid = pid;
        this.uid = uid;
        this.name = name;
        this.telephone = telephone;
        this.rtype = rtype;
        this.address = address;
        this.times = times;
        this.texts = texts;
        this.state = state;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ReserveInfomation{" +
                "lid=" + lid +
                ", pid=" + pid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rtype='" + rtype + '\'' +
                ", address='" + address + '\'' +
                ", times=" + times +
                ", texts='" + texts + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
