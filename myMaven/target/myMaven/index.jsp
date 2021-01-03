<%@ page import="com.lhq.bean.Admin" %>
<%@ page import="com.lhq.bean.Photographer" %>
<%@ page import="com.lhq.bean.User" %>
<%--
  Created by IntelliJ IDEA.
  User: lhq
  Date: 2020/12/27
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="mavenProject.jsp" %>


<html>
<head>
    <title>login</title>
    <link href="css/login_register.css" rel="stylesheet"/>

    <script type="text/javascript">
        function validate() {
            var userName = document.getElementById("userName");
            var password = document.getElementById("password");
            if (userName.value == "") {
                alert("邮箱不能为空！");
                userName.focus();
                return false;
            }
            if (password.value == "") {
                alert("密码不能为空！");
                password.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">

    <!-- login module -->
    <div class="login-box">
        <div id="login-title">
            Login
        </div>


        <form action="${path}/loginServlet" method="post" onsubmit="return validate();">
            <div class="input">
                <input type="text" id="userName" placeholder="Input your userName" name="userName" value="${username}">
            </div>
            <div class="input">
                <input type="password" id="password" placeholder="Input your password" name="password" value="${password}">
            </div>

            <div id="type">
                <input type="radio" name="userType" value="admin" > 管理员&nbsp;&nbsp;&nbsp;
                <input type="radio" name="userType" value="photographer" > 摄影师&nbsp;&nbsp;&nbsp;
                <input type="radio" name="userType" value="user" checked="checked"> 用户
            </div>

            <div id="login">
                <input type="submit" value="Login" id="login-btn" />
            </div>

            <div id="register">
                <input type="button" value="To Register" id="to-register-btn" />
            </div>
        </form>
            <div id="welcome">
                <img src="https://www.shiinaliu.com/pic/loading.gif" />
            </div>

    </div>

</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script type="module" src="<c:url value="/js/login.js"></c:url>"></script>
</body>
</html>