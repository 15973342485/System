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

@WebServlet("/ajaxRegisterServlet")
public class AjaxRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取参数
        String username = request.getParameter("username");
        String userType = request.getParameter("userType");
        System.out.println(username);
        System.out.println(userType);

        //判断用户类型
        if ("admin".equals(userType)) {
            //管理员
            //封装对象
            //封装管理员dao对象
            AdminDao adminDao = new AdminDao();
            boolean flag = adminDao.isUserName(username);
            if(flag){
                //存在
                //数据库中已经有相同email的用户
                //通过response 对象给客户端一个提示
                response.getWriter().write("该邮箱已存在");
            }else {
                //不存在
                    response.getWriter().write("邮箱可注册");

            }

        } else if ("photographer".equals(userType)) {
            //摄影师
            //封装对象
            //封装管理员dao对象
            PhotographerDao photographerDao = new PhotographerDao();
            boolean flag = photographerDao.isUserName(username);
            if(flag){
                //存在
                //数据库中已经有相同email的用户
                //通过response 对象给客户端一个提示
                response.getWriter().write("该邮箱已存在");
            }else {
                response.getWriter().write("邮箱可注册");
            }
        } else if ("user".equals(userType)) {
            //用户
            //封装对象
            //封装管理员dao对象
            UserDao userDao = new UserDao();
            boolean flag = userDao.isUserName(username);
            if(flag){
                //存在
                //数据库中已经有相同email的用户
                response.getWriter().write("该邮箱已存在");
            }else {
                response.getWriter().write("邮箱可注册");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
