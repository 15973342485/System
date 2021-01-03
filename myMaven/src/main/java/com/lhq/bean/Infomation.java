package com.lhq.bean;

import java.util.Date;

public class Infomation {
    private Integer fid;
    private Integer pid;
    private Integer uid;
    private String itype;
    private String iname;
    private String img1;
    private String img2;
    private Date times;
    private String evalute;

    public Infomation() {
    }

    public Infomation(Integer fid, Integer pid, Integer uid, String itype, String iname, String img1, String img2, Date times, String evalute) {
        this.fid = fid;
        this.pid = pid;
        this.uid = uid;
        this.itype = itype;
        this.iname = iname;
        this.img1 = img1;
        this.img2 = img2;
        this.times = times;
        this.evalute = evalute;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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

    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getEvalute() {
        return evalute;
    }

    public void setEvalute(String evalute) {
        this.evalute = evalute;
    }

    @Override
    public String toString() {
        return "Infomation{" +
                "fid=" + fid +
                ", pid=" + pid +
                ", uid=" + uid +
                ", itype='" + itype + '\'' +
                ", iname='" + iname + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", times=" + times +
                ", evalute='" + evalute + '\'' +
                '}';
    }
}
