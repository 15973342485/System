<%--
  Created by IntelliJ IDEA.
  User: 15185
  Date: 2023/3/1
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
<div align="center" style="color: black;font-size: 30px;margin-top: 15px;margin-bottom: 15px">摄影信息</div>


<div align="center" style="margin-bottom: 15px">
    <form action="${path}/UserInfomationServlet?method=search&uid=${user.getUid()}" method="post">
        <input type="text" name="fid" placeholder="请输入信息id" style="width: 400px; height:50px;">
        <input type="submit" value="查 询" style="width: 100px; height:50px;">
    </form>
</div>
<div align="center">
    <table border="1px" align="center" valign="middle" width="1000px" style="font-size: 20px">
        <thead align="center" valign="middle">
        <td>信息档案编号</td>
        <td>摄影师编号</td>
        <td>用户编号</td>
        <td>类型</td>
        <td>主题</td>
        <td>图片完成时间</td>
        <td>评价</td>
        <td colspan="2">操作</td>
        </thead>
        <c:if test="${not empty sessionScope.pageBean.list}">
            <c:forEach items="${sessionScope.pageBean.list}" var="infomation">
                <form method="post" action="${path}/UserInfomationServlet?uid=${user.getUid()}" enctype="multipart/form-data">
                    <tbody>
                    <tr height="50px" valign="middle" align="center">
                        <td>
                        <input type="text" name="fid" width="5px" value="${infomation.fid}" placeholder="信息ID" readonly="readonly"/>
                        </td>
                        <td>
                            <input type="text" name="pid" width="5px" value="${infomation.pid}" placeholder="摄影师ID" readonly="readonly"/>
                        </td>
                        <td>
                            <input type="text" name="uid" width="5px" value="${infomation.uid}" placeholder="用户ID" readonly="readonly"/>
                        </td>
                        <td>
                            <input type="text" name="itype" width="10px" value="${infomation.itype}" placeholder="类型" />
                        </td>
                        <td>
                            <input type="text" name="iname" width="15px" value="${infomation.iname}" placeholder="主题" />
                        </td>
                        <td>
                            <input type="text" name="times" width="15px" value="<fmt:formatDate value="${infomation.times}" pattern="yyyy-MM-dd"/>" placeholder="注册时间"/>
                        </td>
                        <td>
                            <select name="evalute">
                                    <c:if test="${infomation.evalute == '1'}">
                                        <option value="1" selected>1</option>
                                    </c:if>
                                    <c:if test="${infomation.evalute != '1'}">
                                        <option value="1">1</option>
                                    </c:if>
                                <c:if test="${infomation.evalute == '2'}">
                                    <option value="2" selected>2</option>
                                </c:if>
                                <c:if test="${infomation.evalute != '2'}">
                                    <option value="2">2</option>
                                </c:if>
                                <c:if test="${infomation.evalute == '3'}">
                                    <option value="3" selected>3</option>
                                </c:if>
                                <c:if test="${infomation.evalute != '3'}">
                                    <option value="3">3</option>
                                </c:if>
                                <c:if test="${infomation.evalute == '4'}">
                                    <option value="4" selected>4</option>
                                </c:if>
                                <c:if test="${infomation.evalute != '4'}">
                                    <option value="4">4</option>
                                </c:if>
                                <c:if test="${infomation.evalute == '5'}">
                                    <option value="5" selected>5</option>
                                </c:if>
                                <c:if test="${infomation.evalute != '5'}">
                                    <option value="5">5</option>
                                </c:if>
                            </select>
                        </td>
                        <td>
                            <input type="submit" name="method" value="删除" />
                        </td>
                        <td>
                            <input type="submit" name="method" value="修改" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <img src="images/${infomation.img1}" width="250px" alt="未加载"/>
                        </td>
                        <td colspan="5">
                            <img src="images/${infomation.img2}" width="250px" alt="未加载"/>
                        </td>
                    </tr>
                    </tbody>
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
        window.location = "${path}/UserInfomationServlet?method=search&currentPage=" + page + "&pageSize=${sessionScope.pageBean.pageSize}&uid=${user.getUid()}";
    }

    function afterPage(page) {
        window.location = "${path}/UserInfomationServlet?method=search&currentPage=" + page + "&pageSize=${sessionScope.pageBean.pageSize}&uid=${user.getUid()}";
    }
</script>

</html>
