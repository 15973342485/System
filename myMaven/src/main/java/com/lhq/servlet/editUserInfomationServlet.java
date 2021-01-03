package com.lhq.servlet;

import com.lhq.bean.User;
import com.lhq.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/editUserInfomationServlet")
public class editUserInfomationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取请求方法
        String method = request.getParameter("method");
        if("editUserInfomation".equals(method)){
            //编辑用户信息
            //获取请求参数
            String uidStr = request.getParameter("uid");
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String cardID = request.getParameter("cardID");
            String telephone = request.getParameter("telephone");
            String provinceName = request.getParameter("provinceName");
            String cityName = request.getParameter("cityName");
            String districtName = request.getParameter("districtName");
            String password = request.getParameter("password");
            String addtimesStr = request.getParameter("addtimes");


            //数据处理
            int uid = Integer.parseInt(uidStr);
            String address = provinceName + "-" + cityName + "-" + districtName;

            Date addtimes = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                addtimes = sdf.parse(addtimesStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //封装对象
            User user = new User(uid,username,password,name,telephone,cardID,address,addtimes);
            //保存数据
            UserDao userDao = new UserDao();
            Boolean flag = userDao.updateUser(user);
            //把用户信息保存到SESSION作用域
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("provinceName",provinceName);
            request.getSession().setAttribute("cityName",cityName);
            request.getSession().setAttribute("districtName",districtName);
            if(flag){
                //保存成功
                //重定向到用户主界面
                response.sendRedirect("mainUser.jsp");
            }else {
                //保存失败
                request.getRequestDispatcher("editUserInfomation.jsp").forward(request,response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
