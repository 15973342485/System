package com.lhq.dao;

import com.lhq.bean.Photographer;
import com.lhq.bean.User;
import com.lhq.utils.DBUtils;

import java.util.List;

public class PhotographerDao {
    public Photographer getAdminByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM photographer WHERE username = ? AND password = ?";
        Photographer photographer = DBUtils.getSingleObject(Photographer.class, sql,username, password);
        return photographer;
    }

    public boolean isUserName(String username) {
        String sql = "SELECT COUNT(*) FROM photographer WHERE username = ?";
        Integer count = DBUtils.getCount(sql, username);
        return count > 0 ? true : false;
    }

    public boolean savePhotographer(Photographer photographer) {
        String sql = "insert into photographer(username,password,addtimes) value(?,?,?)";
        return DBUtils.save(sql,photographer.getUsername(),photographer.getPassword(),photographer.getAddtimes());
    }

    public List<Photographer> getPhotographerList() {
        String sql = "SELECT * FROM photographer";
        return DBUtils.getList(Photographer.class,sql);
    }

    public Boolean updatePhotographer(Photographer photographer) {
        String sql ="UPDATE photographer \n" +
                "SET \n" +
                "  username = ?,\n" +
                "  password = ?,\n" +
                "  name = ?,\n" +
                "  telephone = ?,\n" +
                "  introduction = ? \n" +
                "WHERE pid = ? ;\n";
        return DBUtils.update(sql,photographer.getUsername(),photographer.getPassword(),photographer.getName(),photographer.getTelephone(),photographer.getIntroduction(),photographer.getPid());
    }

    public Photographer findPhotographer(String pid) {
        String sql = "SELECT * FROM photographer WHERE pid = ?";
        return DBUtils.getSingleObject(Photographer.class,sql,pid);
    }

    public Integer getPhotographerCount(String sql) {
        return DBUtils.getCount(sql);
    }

    public List<Photographer> photographerListByPage(String sql) {
        return DBUtils.getList(Photographer.class,sql);
    }

    public Boolean deletephotographer(String pid) {
        String sql = "DELETE FROM photographer WHERE pid = ? ;";
        return DBUtils.delete(sql,pid);
    }

    public boolean savephotographerAll(Photographer photographer) {
        String sql = "update \n" +
                "  photographer \n" +
                "set \n" +
                "  pid = ?,\n" +
                "  username = ?,\n" +
                "  password = ?,\n" +
                "  name = ?,\n" +
                "  telephone = ?,\n" +
                "  addtimes = ?,\n" +
                "  introduction = ? \n" +
                "where pid = ? ;";
        return DBUtils.update(sql,photographer.getPid(),photographer.getUsername(),photographer.getPassword(),photographer.getName(),photographer.getTelephone(),photographer.getAddtimes(),photographer.getIntroduction(),photographer.getPid());
    }
}
