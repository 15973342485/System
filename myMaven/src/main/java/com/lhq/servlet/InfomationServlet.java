package com.lhq.servlet;

import com.lhq.bean.Infomation;
import com.lhq.dao.InfomationDao;
import com.lhq.dao.ReservelistDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/InfomationServlet")
public class InfomationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取请求方法
        String method = request.getParameter("method");
        if("update".equals(method)){
            //获取参数
            String lid = request.getParameter("lid");
            String pid = request.getParameter("pid");
            String uid = request.getParameter("uid");
            String state = request.getParameter("state");
            String type = request.getParameter("type");
            //创建预约单Dao
            ReservelistDao reservelistDao = new ReservelistDao();
            Boolean flag = false;
            if(!"已完成".equals(state)){
                //更新
                flag = reservelistDao.updateState(state,lid);
            }else{
                //删除
                //保存到作品信息再删除
                //创建对象，进行封装
                Infomation infomation = new Infomation();
                infomation.setPid(Integer.parseInt(pid));
                infomation.setUid(Integer.parseInt(uid));
                infomation.setItype(type);
                infomation.setTimes(new Date());
                InfomationDao infomationDao = new InfomationDao();
                boolean b = infomationDao.saveInfomation(infomation);
                if(b){
                    flag = reservelistDao.deleteReservelist(lid);
                }
            }
            if(flag){
                //成功
                request.getRequestDispatcher("mainPhotographer.jsp").forward(request,response);
            }else{
                //失败
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('更新或删除失败！');");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
