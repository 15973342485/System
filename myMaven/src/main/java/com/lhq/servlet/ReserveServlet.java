package com.lhq.servlet;

import com.lhq.bean.Reservelist;
import com.lhq.dao.ReservelistDao;

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

@WebServlet(urlPatterns = "/ReserveServlet")
public class ReserveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取请求方法
        String method = request.getParameter("method");
        //添加预约单
        if("addReserve".equals(method)){
            //获取参数
            String pidStr = request.getParameter("pid");
            String uidStr = request.getParameter("uid");
            String rtype = request.getParameter("rtype");
            String dateStr = request.getParameter("date");
            String texts = request.getParameter("texts");

            //数据处理
            Integer pid = Integer.parseInt(pidStr);
            Integer uid = Integer.parseInt(uidStr);

            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //封装对象
            Reservelist reservelist = new Reservelist(null,pid,uid,rtype,texts,date,"已提交");

            //创建ReservelistDao对象
            ReservelistDao reservelistDao = new ReservelistDao();
            //将对象保存到数据库
            Integer reservelistID = reservelistDao.saveReserveList(reservelist);

            if(reservelistID != null){
                //保存成功
                //保存预约单ID
                request.getSession().setAttribute("reservelistID",reservelistID);
                //设置对象的ID
                reservelist.setLid(reservelistID);
                //保存预约单对象
                request.getSession().setAttribute("reservelist",reservelist);
                //重定向到用户主界面
                response.sendRedirect("mainUser.jsp");
            }else {
                //保存失败
                //请求转发到添加预约单界面
                request.getRequestDispatcher("addReserveList.jsp").forward(request,response);
            }
        }else if("deleteReserve".equals(method)){//删除预约单
                //获取参数
                String lid = request.getParameter("lid");
                String uid = request.getParameter("uid");
                //创建dao
                ReservelistDao reservelistDao = new ReservelistDao();
                Boolean flag = reservelistDao.deleteReservelist(lid);
                if(flag){
                    //删除成功
                    request.getRequestDispatcher("ReserveListServlet?uid=" + uid).forward(request,response);
                }else{
                    //删除失败
                    PrintWriter writer = response.getWriter();
                    writer.write("<script>");
                    writer.write("alert('删除失败！');");
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
