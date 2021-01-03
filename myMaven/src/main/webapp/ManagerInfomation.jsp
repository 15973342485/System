<%--
  Created by IntelliJ IDEA.
  User: 15185
  Date: 2021/1/1
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ include file="mavenProject.jsp"%>
<html>
<head>
    <title>作品管理</title>
    <style>
        *{
            text-decoration: none;
        }
    </style>
    <link href="style.css" rel="stylesheet" type="text/css" />
</head>


<body>
<div class="header">
    <div class="block_header">
        <div class="logo"><a href="index.html"><img src="images/logo.gif" width="401" height="145" border="0" alt="logo" /></a></div>
        <div class="menu">
            <ul>
                <li><a href="index.html" class="active">首页</a></li>
                <li><a href="photographerInfomation.jsp">个人编辑</a></li>
                <li><a href="${path}/ReserveInfomationServlet?pid=${photographer.getPid()}">管理预约</a></li>
                <li><a href="${path}/ManagerInfomationServlet?pid=${photographer.getPid()}">管理作品</a></li>
                <li><a href="index.jsp">登录</a></li>
            </ul>
        </div>
        <div class="clr"> </div>
    </div>
</div>
<div align="center" style="color: black;font-size: 30px;margin-top: 20px;margin-bottom: 20px">顾客摄影信息</div>
<div align="center">
    <table  border="1px" align="center" valign="middle" width="1000px" style="font-size: 20px">
        <thead align="center" valign="middle">
        <td>用户ID</td>
        <td>类型</td>
        <td>主题</td>
        <td>图片</td>
        <td>图片</td>
        <td>评价</td>
        <td colspan="2">操作</td>
        </thead>
        <c:if test="${not empty sessionScope.pageBean.list}">
            <c:forEach items="${sessionScope.pageBean.list}" var="infomation">
                <form method="post" action="${path}/UpdateInfomationServlet?method=find&fid=${infomation.fid}">
                    <tbody>
                    <tr height="50px" valign="middle" align="center">
                        <td name="uid">${infomation.uid}</td>
                        <td name="itype">${infomation.itype}</td>
                        <td name="iname">${infomation.iname}</td>
                        <td><img name="img1" src="images/${infomation.img1}" width="200px" alt="未上传"></td>
                        <td><img name="img2" src="images/${infomation.img2}" width="200px" alt="未上传"></td>
                        <td name="evalute">${infomation.evalute}</td>
                        <td>
                            <input type="submit" id="update" value="修改" />
                        </td>
                    </tr>
                    </tbody>
                    <input type="hidden" value="${infomation.fid}" name="fid">
                    <input type="hidden" value="${infomation.pid}" name="pid">
                    <input type="hidden" value="${infomation.uid}" name="uid">
                    <input type="hidden" value="${infomation.times}" name="times">

                </form>
            </c:forEach>
        </c:if>
    </table>
</div>




<div align="center" style="margin-top: 20px; font-size: 25px">
    <a href="javascript:beforePage('${sessionScope.pageBean.beforePage}')">上一页</a>&nbsp;&nbsp;&nbsp;
    共${sessionScope.pageBean.totalPages}页&nbsp;&nbsp;&nbsp;共${sessionScope.pageBean.totalRows}行&nbsp;&nbsp;&nbsp;
    第${sessionScope.pageBean.currentPage}页&nbsp;&nbsp;&nbsp;每页${sessionScope.pageBean.pageSize}行&nbsp;&nbsp;&nbsp;
    <a href="javascript:afterPage('${sessionScope.pageBean.afterPage}')">下一页</a>
</div>

<div style="position: fixed; bottom: 0;width: 100%">
    <div class="FBG">
        <div class="Fbg_resize">
            <div class="Twitter">
                <p><img src="images/Twitter.gif" alt="picture" width="129" height="96"/></p>
                <p><strong>Lorem Ipsum has been</strong> The industry's standard dummy text ever since thes. <a
                        href="#"><img src="images/read_more.gif" alt="picture" width="68" height="20" border="0"/></a>
                </p>
            </div>
            <div class="con">
                <h2>Contact us</h2>
                <ul>
                    <li>Name of the Company</li>
                    <li> 2901 Marmora Road, Glassgow, D04 59GR</li>
                    <li> Telephone: +1 234 567 8910</li>
                </ul>
                <ul>
                    <li>FAX: +1 234 567 8910</li>
                    <li>E-mail: <a href="#">mail@yoursitename.com</a></li>
                </ul>
            </div>
        </div>
        <div class="clr"></div>
    </div>
    <div class="footer">
        <div class="resize">
            <div>Copyright © Sitename.com. <a href="http://dreamtemplate.com/">dreamtemplate.com</a>. All Rights
                Reserved<br/>
                <a href="index.html">Home</a> | <a href="contact.html">Contact</a> | <a href="blog.html">RSS</a></div>
        </div>
        <p class="clr"></p>
    </div>
</div>
</body>
<script>
    function beforePage(page) {
        window.location = "${path}/ManagerInfomationServlet?currentPage=" + page + "&pageSize=${sessionScope.pageBean.pageSize}&pid=${photographer.getPid()}";
    }

    function afterPage(page) {
        window.location = "${path}/ManagerInfomationServlet?currentPage=" + page + "&pageSize=${sessionScope.pageBean.pageSize}&pid=${photographer.getPid()}";
    }
</script>

</html>
