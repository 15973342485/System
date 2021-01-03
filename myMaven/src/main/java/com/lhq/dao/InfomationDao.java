package com.lhq.dao;

import com.lhq.bean.Infomation;
import com.lhq.utils.DBUtils;

import java.util.List;

public class InfomationDao {
    public boolean saveInfomation(Infomation infomation) {
        String sql = "INSERT INTO information (pid,uid,itype,times) VALUES (?,?,?,?);";
        return DBUtils.save(sql,infomation.getPid(),infomation.getUid(),infomation.getItype(),infomation.getTimes());
    }

    public Integer getReserveCount(String sql, String pid) {
        return DBUtils.getCount(sql,pid);
    }

    public List<Infomation> reservelistListByPage(String sql, String pid) {
        return DBUtils.getList(Infomation.class,sql,pid);
    }

    public boolean updatePicture(String fid, String iname, String fileName, String fileName1) {
        String sql = "UPDATE \n" +
                "information \n" +
                "SET \n" +
                "  iname = ?,\n" +
                "  img1 = ?,\n" +
                "  img2 = ? \n" +
                "WHERE fid = ?;\n";
        return DBUtils.update(sql,iname,fileName,fileName1,fid);

    }

    public Infomation getResumeBasicInfoById(String fid) {
        String sql = "SELECT * FROM information where fid = ?";
        return DBUtils.getSingleObject(Infomation.class,sql,fid);
    }

    public Integer getinfomationCount(String sql) {
        return DBUtils.getCount(sql);
    }

    public List<Infomation> infomationListByPage(String sql) {
        return DBUtils.getList(Infomation.class,sql);
    }

    public Boolean deleteinfomation(String fid) {
        String sql = "DELETE FROM information WHERE fid = ? ;";
        return DBUtils.delete(sql,fid);
    }

    public Boolean saveInfomationAll(Infomation infomation) {

        String sql = "UPDATE \n" +
                "  information \n" +
                "SET \n" +
                "  pid = ?,\n" +
                "  uid = ?,\n" +
                "  itype = ?,\n" +
                "  iname = ?,\n" +
                "  times = ?,\n" +
                "  evalute = ? \n" +
                "WHERE fid = ?;";
        return DBUtils.update(sql,infomation.getPid(),infomation.getUid(),infomation.getItype(),infomation.getIname(),infomation.getTimes(),infomation.getEvalute(),infomation.getFid());
    }
}
