package com.lhq.servlet;

import com.lhq.bean.PageBean;
import com.lhq.bean.Photographer;
import com.lhq.bean.User;
import com.lhq.dao.PhotographerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/AdminManagerPhotographerServlet")
public class AdminManagerPhotographerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getParameter("method");
        String pid = request.getParameter("pid");

        //判断是否为空
        if("".equals(pid)){
            pid = null;
        }
        if("search".equals(method)){
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
            PhotographerDao photographerDao = new PhotographerDao();
            String sql = "select count(*) from photographer";
            totalRows = photographerDao.getPhotographerCount(sql);

            //5、起始行 startRow
            Integer startRow = (currentPage-1)*pageSize;

            StringBuffer sqlRow = new StringBuffer("select * from photographer ");
            //判断是否查询单个用户
            if(pid != null){
                sqlRow.append("where pid = " + pid + " ");
            }

            sqlRow.append("limit ").append(startRow).append(",").append(pageSize);

            List<Photographer> PhotographerList = photographerDao.photographerListByPage(sqlRow.toString());
            //PageBean pageBean = new PageBean(currentPage, pageSize, beforePage, afterPage, totalRows, totalPages, companyList);
            PageBean pageBean = new PageBean(currentPage, pageSize,  totalRows,  PhotographerList);

            request.getSession().setAttribute("pageBean",pageBean);

            request.getRequestDispatcher("AdminManagerPhotographer.jsp").forward(request,response);
        }else if("删除".equals(method)){
            //创建PhotographerDao
            PhotographerDao photographerDao = new PhotographerDao();
            Boolean flag = photographerDao.deletephotographer(pid);
            if(!flag){
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('删除失败！');");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
            request.getRequestDispatcher("mainAdmin.jsp").forward(request,response);
        }else if("修改".equals(method)){
            //获取请求参数
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String telephone = request.getParameter("telephone");
            String password = request.getParameter("password");
            String addtimesStr = request.getParameter("addtimes");
            String introduction = request.getParameter("introduction");

            //数据处理
            Integer id = Integer.parseInt(pid);

            Date addtimes = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                addtimes = sdf.parse(addtimesStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //封装数据
            Photographer photographer = new Photographer(id,username,password,name,telephone,addtimes,introduction);

            //保存数据
            PhotographerDao photographerDao = new PhotographerDao();
            boolean flag = photographerDao.savephotographerAll(photographer);
            if(!flag){
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('更新失败！');");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
            request.getRequestDispatcher("mainAdmin.jsp").forward(request,response);
        }

    }
}
