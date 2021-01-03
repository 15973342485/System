package com.lhq.dao;

import com.lhq.bean.ReserveInfomation;
import com.lhq.utils.DBUtils;

import java.util.List;

public class ReserveInfomationDao {
    public Integer getReserveCount(String sql, String pid) {
        return DBUtils.getCount(sql,pid);
    }

    public List<ReserveInfomation> reservelistListByPage(String sql, String pid) {
        return DBUtils.getList(ReserveInfomation.class,sql,pid);
    }
}
