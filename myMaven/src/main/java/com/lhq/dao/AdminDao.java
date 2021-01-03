package com.lhq.dao;

import com.lhq.bean.Admin;
import com.lhq.utils.DBUtils;

public class AdminDao {
    public Admin getAdminByUsernameAndPassword(String username, String password) {
        //定义sql语句
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        Admin admin = DBUtils.getSingleObject(Admin.class, sql,username, password);
        return admin;
    }

    public boolean isUserName(String username) {
        String sql = "SELECT COUNT(*) FROM admin WHERE username = ?";
        Integer count = DBUtils.getCount(sql, username);
        return count > 0 ? true : false;
    }


    public boolean saveAdmin(Admin admin) {
        String sql = "insert into admin(username,password) value(?,?)";
        return DBUtils.save(sql,admin.getUsername(),admin.getPassword());
    }
}
