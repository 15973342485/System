package com.lhq.servlet;

import com.lhq.bean.Infomation;
import com.lhq.bean.Photographer;
import com.lhq.bean.User;
import com.lhq.dao.InfomationDao;
import com.lhq.dao.PhotographerDao;
import com.lhq.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/UpdateInfomationServlet")
@MultipartConfig
public class UpdateInfomationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置请求和响应编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getParameter("method");

        if("update".equals(method)){
            Part part = request.getPart("img1");
            Part part1 = request.getPart("img2");
            String iname = request.getParameter("iname");
            String fid = request.getParameter("fid");


            //上传的文件名head1.jpg

            String fileName = part.getSubmittedFileName();
            String fileName1 = part1.getSubmittedFileName();

            //创建保存文件目录
            String dir = this.getServletContext().getRealPath("/images");
            //String dir = "D:\\myMaven\\src\\main\\webapp\\images";
            //判断 目录是否存在
            File imgDir = new File(dir);
            if (!imgDir.exists()){
                imgDir.mkdirs();
            }

            System.out.println(dir);
            //信息对象
            InfomationDao infomationDao = new InfomationDao();

            //上传到服务器文件路径imgDir+'/'+fileName
            part.write(dir+"/"+fileName);
            part1.write(dir+"/"+fileName1);
            boolean b = infomationDao.updatePicture(fid,iname,fileName,fileName1);


            Infomation infomation =  infomationDao.getResumeBasicInfoById(fid);
            request.getSession().setAttribute("infomation",infomation);

            //请求转发
            request.getRequestDispatcher("mainPhotographer.jsp").forward(request,response);

        }else if("find".equals(method)){
            String uid = request.getParameter("uid");
            String pid = request.getParameter("pid");
            String fid = request.getParameter("fid");
            UserDao userDao = new UserDao();
            PhotographerDao photographerDao = new PhotographerDao();
            User user = userDao.findUser(uid);
            Photographer photographer = photographerDao.findPhotographer(pid);

            request.getSession().setAttribute("userInfo",user);
            request.getSession().setAttribute("photographerInfo",photographer);
            request.getSession().setAttribute("fid",fid);

            if(user != null && photographer != null){
                request.getRequestDispatcher("updateInfomation.jsp").forward(request,response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
