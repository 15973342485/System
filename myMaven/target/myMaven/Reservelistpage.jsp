<%--
  Created by IntelliJ IDEA.
  User: 15185
  Date: 2020/12/2
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ include file="mavenProject.jsp" %>
<html>
<head>
    <title>分页查询预约单</title>
    <style>
        * {
            text-decoration: none;
        }
    </style>
    <link href="style.css" rel="stylesheet" type="text/css"/>
</head>


<body>

<div class="header">
    <div class="block_header">
        <div class="logo"><a href="index.html"><img src="images/logo.gif" width="401" height="145" border="0"
                                                    alt="logo"/></a></div>
        <div class="menu">
            <ul>
                <li><a href="index.html" class="active">首页</a></li>
                <li><a href="editUserInfomation.jsp">个人编辑</a></li>
                <li><a href="${path}/ReserveListServlet?uid=${user.getUid()}">预约</a></li>
                <li><a href="${path}/UserInfomationServlet?method=search&uid=${user.getUid()}">作品</a></li>
                <li><a href="index.jsp">登录</a></li>
            </ul>
        </div>
        <div class="clr"></div>
    </div>
</div>
<div align="center" style="color: black;font-size: 30px;margin-top: 15px;margin-bottom: 15px">预约单</div>
<div>
    <div>
        <table border="1px" align="center" valign="middle" width="1000px" style="font-size: 20px">
            <thead align="center" valign="middle">
            <td>摄影师ID</td>
            <td>用户ID</td>
            <td>类型</td>
            <td>添加时间</td>
            <td>状态</td>
            <td>备注</td>
            <td colspan="2">操作</td>
            </thead>
            <c:if test="${not empty sessionScope.pageBean.list}">
                <c:forEach items="${sessionScope.pageBean.list}" var="reservelist">
                    <tbody>
                    <tr height="50px" valign="middle" align="center">
                        <td>${reservelist.pid}</td>
                        <td>${reservelist.uid}</td>
                        <td>${reservelist.rtype}</td>
                        <td><fmt:formatDate value="${reservelist.times}" pattern="yyyy-MM-dd"/></td>
                        <td>${reservelist.state}</td>
                        <td>${reservelist.texts}</td>
                        <td>
                            <a href="${path}/ReserveServlet?method=deleteReserve&lid=${reservelist.lid}&uid=${reservelist.uid}">删
                                除</a></td>
                    </tbody>
                </c:forEach>
            </c:if>
            <tr>
                <td colspan="8" align="center" height="50px" style="font-size: 20px"><a class="btn btn-primary" href="addReserveList.jsp">添加预约单</a></td>
            </tr>
        </table>
    </div>


    <div align="center" style="margin-top: 20px; font-size: 25px">
        <a href="javascript:beforePage('${sessionScope.pageBean.beforePage}')">上一页</a>&nbsp;&nbsp;&nbsp;
        共${sessionScope.pageBean.totalPages}页&nbsp;&nbsp;&nbsp;共${sessionScope.pageBean.totalRows}行&nbsp;&nbsp;&nbsp;
        第${sessionScope.pageBean.currentPage}页&nbsp;&nbsp;&nbsp;每页${sessionScope.pageBean.pageSize}行&nbsp;&nbsp;&nbsp;
        <a href="javascript:afterPage('${sessionScope.pageBean.afterPage}')">下一页</a>
    </div>
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


<script>
    function beforePage(page) {
        window.location = "${path}/ReserveListServlet?currentPage=" + page + "&pageSize=${sessionScope.pageBean.pageSize}&uid=${user.getUid()}";
    }

    function afterPage(page) {
        window.location = "${path}/ReserveListServlet?currentPage=" + page + "&pageSize=${sessionScope.pageBean.pageSize}&uid=${user.getUid()}";
    }

    /*1.给第一个复选框添加点击事件*/
    document.getElementById("firstCheckbox").onclick = function (currentPage, pageSize, totalRows) {
        <!--2.然后就是获取下面列表中的所有复选框事件-->
        var checkBoxs = document.getElementsByName("ziCheckbox");
        //3.遍历
        for (var i = 0; i < checkBoxs.length; i++) {
            //设置这些checkBoxs[i]的check状态=firstCheckBox.check的状态
            checkBoxs[i].checked = this.checked;
            console.log(checkBoxs[i]);
        }


    }


    function deleteReserve(lid) {
        //访问路径
        location.href = "${path}/ReserveServlet?method=deleteReserve&lid=" + lid + "&uid=${reservelist.uid}";
    }
</script>
</body>
</html>
