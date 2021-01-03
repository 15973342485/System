package com.lhq.dao;

import com.lhq.bean.User;
import com.lhq.utils.DBUtils;

import java.util.List;

public class UserDao {
    public static User getAdminByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        User user = DBUtils.getSingleObject(User.class, sql,username, password);
        return user;
    }

    public static Integer getUserCount(String sql) {
        return DBUtils.getCount(sql);
    }

    public boolean isUserName(String username) {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        Integer count = DBUtils.getCount(sql, username);
        return count > 0 ? true : false;
    }

    public boolean saveUser(User user) {
        String sql = "insert into user(username,password,addtimes) value(?,?,?)";
        return DBUtils.save(sql,user.getUsername(),user.getPassword(),user.getAddtimes());
    }

    public Boolean updateUser(User user) {
        String sql = "update user \n" +
                "set \n" +
                "  name = ?,\n" +
                "  telephone = ?,\n" +
                "  cardID = ?,\n" +
                "  address = ? \n" +
                "where uid = ?;\n";
        return DBUtils.save(sql, user.getName(), user.getTelephone(), user.getCardID(), user.getAddress(), user.getUid());
    }

    public User findUser(String uid) {
        String sql = "SELECT * FROM user WHERE uid = ?";
        return DBUtils.getSingleObject(User.class,sql,uid);
    }

    public List<User> userListByPage(String sql) {
        return DBUtils.getList(User.class,sql);
    }

    public Boolean deleteUser(String uid) {
        String sql = "DELETE FROM user WHERE uid = ? ;";
        return DBUtils.delete(sql,uid);
    }

    public boolean saveUserAll(User user) {
        String sql = "update user \n" +
                "set \n" +
                "  username = ?,\n" +
                "  password = ?,\n" +
                "  name = ?,\n" +
                "  telephone = ?,\n" +
                "  cardID = ?,\n" +
                "  address = ?,\n" +
                "  addtimes = ? \n" +
                "where uid = ?;";
        return DBUtils.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getTelephone(),user.getCardID(),user.getAddress(),user.getAddtimes(),user.getUid());
    }
}
