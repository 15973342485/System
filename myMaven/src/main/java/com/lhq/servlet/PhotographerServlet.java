package com.lhq.servlet;

import com.lhq.bean.Photographer;
import com.lhq.dao.PhotographerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/PhotographerServlet")
public class PhotographerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取请求方法
        String method = request.getParameter("method");
        //更新
        if("update".equals(method)){
            //获取参数
            String pidStr = request.getParameter("pid");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String telephone = request.getParameter("telephone");
            String addtimesStr = request.getParameter("addtimes");
            String introduction = request.getParameter("introduction");

            //数据处理
            int pid = Integer.parseInt(pidStr);
            Date addtimes = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                addtimes = sdf.parse(addtimesStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //封装对象
            Photographer photographer = new Photographer(pid,username,password,name,telephone,addtimes,introduction);
            //保存session
            request.getSession().setAttribute("photographer",photographer);
            //保存到数据库
            PhotographerDao photographerDao = new PhotographerDao();
            Boolean flag = photographerDao.updatePhotographer(photographer);

            if(flag){
                //保存成功
                //重定向到摄影师主界面
                response.sendRedirect("mainPhotographer.jsp");
            }else {
                //保存失败
                //请求转发到编辑摄影师界面
                request.getRequestDispatcher("photographerInfomation.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
