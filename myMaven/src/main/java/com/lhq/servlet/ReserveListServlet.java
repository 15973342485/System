package com.lhq.servlet;

import com.lhq.bean.PageBean;
import com.lhq.bean.Reservelist;
import com.lhq.dao.ReservelistDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ReserveListServlet")
public class ReserveListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String uid = request.getParameter("uid");
        //1、每页多少行  pageSize
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = null;  //每页多少行
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.valueOf(pageSizeStr);
        }else {
            pageSize = 5;
        }
        //2、当前是第几行  currentPage
        String currentPageStr = request.getParameter("currentPage");
        Integer currentPage = null;  //当前查询几行
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.valueOf(currentPageStr);
        }else {
            currentPage = 1;
        }
        //3、一共多少行数据 totalRows
        Integer totalRows = 0;
        ReservelistDao reservelistDao = new ReservelistDao();
        String sql = "select count(*) from reservelist where uid = ?";
        totalRows = reservelistDao.getReserveCount(sql,uid);

        //5、起始行 startRow
        Integer startRow = (currentPage-1)*pageSize;

        StringBuffer sqlRow = new StringBuffer("SELECT * FROM reservelist WHERE uid = ? ");

        sqlRow.append("limit ").append(startRow).append(",").append(pageSize);

        List<Reservelist> reservelistList = reservelistDao.reservelistListByPage(sqlRow.toString(),uid);
        //PageBean pageBean = new PageBean(currentPage, pageSize, beforePage, afterPage, totalRows, totalPages, companyList);
        PageBean pageBean = new PageBean(currentPage, pageSize,  totalRows,  reservelistList);

        request.getSession().setAttribute("pageBean",pageBean);

        request.getRequestDispatcher("Reservelistpage.jsp").forward(request,response);
    }
}
