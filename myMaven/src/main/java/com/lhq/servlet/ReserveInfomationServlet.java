package com.lhq.servlet;

import com.lhq.bean.PageBean;
import com.lhq.bean.ReserveInfomation;
import com.lhq.dao.ReserveInfomationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ReserveInfomationServlet")
public class ReserveInfomationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String pid = request.getParameter("pid");
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
        ReserveInfomationDao reserveInfomationDao = new ReserveInfomationDao();
        String sql = "select count(*) from reservelist where pid = ?";
        totalRows = reserveInfomationDao.getReserveCount(sql,pid);

        //5、起始行 startRow
        Integer startRow = (currentPage-1)*pageSize;

        StringBuffer sqlRow = new StringBuffer("select \n" +
                "lid,\n" +
                "pid,\n" +
                "user.uid,\n" +
                "name,\n" +
                "telephone,\n" +
                "rtype,\n" +
                "address,\n" +
                "times,\n" +
                "texts,\n" +
                "state \n" +
                "from \n" +
                "reservelist,user \n" +
                "where \n" +
                "reservelist.uid = user.uid and pid = ?");

        sqlRow.append("limit ").append(startRow).append(",").append(pageSize);

        List<ReserveInfomation> reservelistInfomationList = reserveInfomationDao.reservelistListByPage(sqlRow.toString(),pid);
        //PageBean pageBean = new PageBean(currentPage, pageSize, beforePage, afterPage, totalRows, totalPages, companyList);
        PageBean pageBean = new PageBean(currentPage, pageSize,  totalRows,  reservelistInfomationList);

        request.getSession().setAttribute("pageBean",pageBean);

        request.getRequestDispatcher("ManageReservelistpage.jsp").forward(request,response);
    }
}
