package com.lhq.servlet;

import com.lhq.bean.PageBean;
import com.lhq.bean.User;
import com.lhq.dao.UserDao;

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

@WebServlet(urlPatterns = "/AdminManagerUserServlet")
public class AdminManagerUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        String uid = request.getParameter("uid");

        //判断是否为空
        if("".equals(uid)){
            uid = null;
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
            UserDao userDao = new UserDao();
            String sql = "select count(*) from user";
            totalRows = userDao.getUserCount(sql);

            //5、起始行 startRow
            Integer startRow = (currentPage-1)*pageSize;

            StringBuffer sqlRow = new StringBuffer("select * from user ");
            //判断是否查询单个用户
            if(uid != null){
                sqlRow.append("where uid = " + uid + " ");
            }

            sqlRow.append("limit ").append(startRow).append(",").append(pageSize);

            List<User> userList = userDao.userListByPage(sqlRow.toString());
            //PageBean pageBean = new PageBean(currentPage, pageSize, beforePage, afterPage, totalRows, totalPages, companyList);
            PageBean pageBean = new PageBean(currentPage, pageSize,  totalRows,  userList);

            request.getSession().setAttribute("pageBean",pageBean);

            request.getRequestDispatcher("AdminManagerUser.jsp").forward(request,response);
        }else if("删除".equals(method)){
            //创建Userdao
            UserDao userDao = new UserDao();
            Boolean flag = userDao.deleteUser(uid);
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
            String cardID = request.getParameter("cardID");
            String telephone = request.getParameter("telephone");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String addtimesStr = request.getParameter("addtimes");

            //数据处理
            Integer id = Integer.parseInt(uid);

            Date addtimes = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                addtimes = sdf.parse(addtimesStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //封装数据
            User user = new User(id,username,password,name,telephone,cardID,address,addtimes);

            //保存数据
            UserDao userDao = new UserDao();
            boolean flag = userDao.saveUserAll(user);
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
