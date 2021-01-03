package com.lhq.dao;

import com.lhq.bean.Reservelist;
import com.lhq.utils.DBUtils;

import java.util.List;

public class ReservelistDao {
    public Integer saveReserveList(Reservelist reservelist) {
        String sql = "insert into reservelist(lid, pid, uid, rtype, texts, times, state) values(?,?,?,?,?,?,?);";
        return DBUtils.updateForPrimary(sql,null, reservelist.getPid(), reservelist.getUid(), reservelist.getRtype(), reservelist.getTexts(), reservelist.getTimes(), reservelist.getState());
    }

    public Integer getReserveCount(String sql,String uid) {
        return DBUtils.getCount(sql,uid);
    }

    public List<Reservelist> reservelistListByPage(String sql, String uid) {
        return DBUtils.getList(Reservelist.class,sql,uid);
    }

    public Boolean deleteReservelist(String lid) {
        String sql = "DELETE FROM reservelist WHERE lid = ?;";
        return  DBUtils.delete(sql,lid);
    }

    public Boolean updateState(String state, String lid) {
        String sql = "UPDATE reservelist SET state = ? WHERE lid = ? ;";
        return DBUtils.delete(sql,state,lid);
    }
}
