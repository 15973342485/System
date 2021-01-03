package com.lhq.servlet;

import com.lhq.bean.Infomation;
import com.lhq.bean.PageBean;
import com.lhq.dao.InfomationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/UserInfomationServlet")
@MultipartConfig
public class UserInfomationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getParameter("method");
        String fid = request.getParameter("fid");
        String uid = request.getParameter("uid");

        //判断是否为空
        if("".equals(fid)){
            fid = null;
        }
        if("search".equals(method)){
            //1、每页多少行  pageSize
            String pageSizeStr = request.getParameter("pageSize");
            Integer pageSize = null;  //每页多少行
            if(pageSizeStr != null && pageSizeStr.length() > 0){
                pageSize = Integer.valueOf(pageSizeStr);
            }else {
                pageSize = 1;
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
            InfomationDao infomationDao = new InfomationDao();
            String sql = "select count(*) from information where uid = " + uid;
            totalRows = infomationDao.getinfomationCount(sql);

            //5、起始行 startRow
            Integer startRow = (currentPage-1)*pageSize;

            StringBuffer sqlRow = new StringBuffer("select * from information where uid = " + uid);
            //判断是否查询单个用户
            if(fid != null){
                sqlRow.append(" AND fid = " + fid);
            }

            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
  
            List<Infomation> infomationList = infomationDao.infomationListByPage(sqlRow.toString());
            //PageBean pageBean = new PageBean(currentPage, pageSize, beforePage, afterPage, totalRows, totalPages, companyList);
            PageBean pageBean = new PageBean(currentPage, pageSize,  totalRows,  infomationList);

            request.getSession().setAttribute("pageBean",pageBean);

            request.getRequestDispatcher("userInfomation.jsp").forward(request,response);
        }else if("删除".equals(method)){
            //创建PhotographerDao
            InfomationDao infomationDao = new InfomationDao();
            Boolean flag = infomationDao.deleteinfomation(fid);
            if(!flag){
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('删除失败！');");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
            request.getRequestDispatcher("mainUser.jsp").forward(request,response);
        }else if("修改".equals(method)){
            //获取请求参数
            String pid = request.getParameter("pid");

            String itype = request.getParameter("itype");
            String iname = request.getParameter("iname");
            String addtimesStr = request.getParameter("times");
            String evalute = request.getParameter("evalute");



            //信息对象
            InfomationDao infomationDao = new InfomationDao();


            //数据处理
            Integer id = Integer.parseInt(fid);
            Integer u = Integer.parseInt(uid);
            Integer p = Integer.parseInt(pid);

            Date addtimes = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                addtimes = sdf.parse(addtimesStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //封装对象
            Infomation infomation = new Infomation(id,p,u,itype,iname,null,null,addtimes,evalute);

            //保存到数据库
            Boolean b = infomationDao.saveInfomationAll(infomation);

            request.getRequestDispatcher("mainUser.jsp").forward(request,response);
        }

    }
}
