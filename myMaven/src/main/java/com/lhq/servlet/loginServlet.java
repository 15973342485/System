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
import java.util.List;

@WebServlet(urlPatterns = "/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取参数
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        //判断用户类型
        if("admin".equals(userType)){
            //管理员
            //创建管理员的dao对象
            AdminDao adminDao = new AdminDao();
            Admin admin = adminDao.getAdminByUsernameAndPassword(username,password);
            //判断管理员是否存在
            if(admin != null){
                //把管理员对象存储到session
                request.getSession().setAttribute("admin",admin);
                //重定向到管理员主界面
                response.sendRedirect("mainAdmin.jsp");
            }else{
                show(response);
                //请求转发
                request.getRequestDispatcher("index.isp").forward(request,response);
            }
        }else if("photographer".equals(userType)){
            //摄影师
            //创建摄影师的dao对象
            PhotographerDao photographerDao = new PhotographerDao();
            Photographer photographer = photographerDao.getAdminByUsernameAndPassword(username,password);
            //判断摄影师是否存在
            if(photographer != null){
                //把摄影师对象存储到session
                request.getSession().setAttribute("photographer",photographer);
                //重定向到摄影师主界面
                response.sendRedirect("mainPhotographer.jsp");
            }else{
                show(response);
                //请求转发
                request.getRequestDispatcher("index.isp").forward(request,response);
            }
        }else if("user".equals(userType)){
            //用户
            //创建用户的dao对象
            UserDao userDao = new UserDao();
            User user = userDao.getAdminByUsernameAndPassword(username,password);
            //判断用户是否存在
            if(user != null){
                //获取地址
                if(user.getAddress() != null) {
                    String address = user.getAddress();
                    String[] strArr = address.split("-");
                    String provinceName = null;
                    String cityName = null;
                    String districtName = null;
                    if (strArr.length == 0) {
                        provinceName = "—— 省 ——";
                        cityName = "—— 市 ——";
                        districtName = "—— 区 ——";
                    } else if (strArr.length == 1) {
                        provinceName = strArr[0];
                        cityName = "—— 市 ——";
                        districtName = "—— 区 ——";
                    } else if (strArr.length == 2) {
                        provinceName = strArr[0];
                        cityName = strArr[1];
                        districtName = "—— 区 ——";
                    } else if (strArr.length == 3) {
                        provinceName = strArr[0];
                        cityName = strArr[1];
                        districtName = strArr[2];
                    }
                    request.getSession().setAttribute("provinceName", provinceName);
                    request.getSession().setAttribute("cityName", cityName);
                    request.getSession().setAttribute("districtName", districtName);
                }

                request.getSession().setAttribute("user",user);

                //得到摄影师集合
                PhotographerDao photographerDao = new PhotographerDao();
                List<Photographer> photographerList = photographerDao.getPhotographerList();
                /*for(Photographer photographer: photographerList){
                    System.out.println(photographer);
                }*/
                //存放在请求域
                request.getSession().setAttribute("photographerList",photographerList);
                //请求转发到用户主界面
                request.getRequestDispatcher("mainUser.jsp").forward(request,response);
            }else{
                show(response);
                //请求转发
                request.getRequestDispatcher("index.isp").forward(request,response);
            }
        }

    }

    private void show(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("alert('用户名或者密码错误！');");
        writer.write("window.location.href = 'index.jsp'");
        writer.write("</script>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }

}
