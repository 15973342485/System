package com.lhq.bean;

import java.util.Date;

public class Reservelist {
    private Integer lid;
    private Integer pid;
    private Integer uid;
    private String rtype;
    private String texts;
    private Date times;
    private String state;

    public Reservelist() {
    }

    public Reservelist(Integer lid, Integer pid, Integer uid, String rtype, String texts, Date times, String state) {
        this.lid = lid;
        this.pid = pid;
        this.uid = uid;
        this.rtype = rtype;
        this.texts = texts;
        this.times = times;
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

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Reservelist{" +
                "lid='" + lid + '\'' +
                ", pid='" + pid + '\'' +
                ", uid='" + uid + '\'' +
                ", rtype='" + rtype + '\'' +
                ", texts='" + texts + '\'' +
                ", times=" + times +
                ", state='" + state + '\'' +
                '}';
    }
}
