package com.lhq.servlet;

import com.lhq.bean.Admin;
import com.lhq.bean.Photographer;
import com.lhq.bean.User;
import com.lhq.dao.AdminDao;
import com.lhq.dao.PhotographerDao;
import com.lhq.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取参数
        String username = request.getParameter("userName-1");
        String password = request.getParameter("password-1");
        String userType = request.getParameter("userType");

        //判断用户类型
        if ("admin".equals(userType)) {
            //管理员
            //封装对象
            Admin admin = new Admin(null,username,password);
            //封装管理员dao对象
            AdminDao adminDao = new AdminDao();
            boolean flag = adminDao.isUserName(username);
            if(flag){
                //存在
                //数据库中已经有相同email的用户
                //通过response 对象给客户端一个提示
                show(response);
            }else {
                //不存在
                boolean b = adminDao.saveAdmin(admin);
                if(b){
                    //注册成功就跳转到登录界面  重定向
                    request.setAttribute("admin",admin);
                    response.sendRedirect("index.jsp");
                }else {
                    //注册失败就返回注册页面  ，请求转发
                    request.getRequestDispatcher("register.jsp").forward(request,response);
                }
            }

        } else if ("photographer".equals(userType)) {
            //摄影师
            //封装对象
            Photographer photographer = new Photographer(null,username,password);
            photographer.setAddtimes(new Date());
            //封装管理员dao对象
            PhotographerDao photographerDao = new PhotographerDao();
            boolean flag = photographerDao.isUserName(username);
            if(flag){
                //存在
                //数据库中已经有相同email的用户
                //通过response 对象给客户端一个提示
                show(response);
            }else {
                //不存在
                boolean b = photographerDao.savePhotographer(photographer);
                if(b){
                    //注册成功就跳转到登录界面  重定向
                    request.setAttribute("photographer",photographer);
                    response.sendRedirect("index.jsp");
                }else {
                    //注册失败就返回注册页面，请求转发
                    request.getRequestDispatcher("register.jsp").forward(request,response);
                }
            }
        } else if ("user".equals(userType)) {
            //用户
            //封装对象
            User user = new User(null,username,password);
            System.out.println(user);
            user.setAddtimes(new Date());
            //封装管理员dao对象
            UserDao userDao = new UserDao();
            boolean flag = userDao.isUserName(username);
            if(flag){
                //存在
                //数据库中已经有相同email的用户
                //通过response 对象给客户端一个提示
                show(response);
            }else {
                //不存在
                boolean b = userDao.saveUser(user);
                if(b){
                    //注册成功就跳转到登录界面  重定向
                    request.setAttribute("user",user);
                    response.sendRedirect("index.jsp");
                }else {
                    //注册失败就返回注册页面  ，请求转发
                    request.getRequestDispatcher("register.jsp").forward(request,response);
                }
            }
        }
    }

    private void show(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("alert('申请注册的账号已经被注册！');");
        writer.write("window.location.href = 'register.jsp'");
        writer.write("</script>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
