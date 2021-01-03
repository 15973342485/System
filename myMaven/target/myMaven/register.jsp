
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@include file="mavenProject.jsp" %>
<html>
<head>
    <title>register</title>
   <link href="css/login_register.css" rel="stylesheet"/>
    <script crossorigin="anonymous" src="js/jquery.js"></script>


    <script type="text/javascript">

        //失去焦点
        $(function () {
            $('#userName-1').blur(function () {
                var username = $('#userName-1').val();
                var userType = $('input:radio:checked').val();

                // 使用ajax发送请求
                $.ajax({
                    //url，写要访问的地址，这里访问的是servlet映射地址
                    url: 'ajaxRegisterServlet',
                    cache: false,  // 是否缓存
                    //参数  相当于地址栏后面的参数
                    data: 'username='+username+'&userType='+userType,
                        // {username:username,password:password,userType:userType},
                    //请求类型
                    type: 'get',
                    //成功的回调   问成功后，执行的方法，参数是后台的结果
                    success: function (result) {
                        $('#msg').text(result);
                    }

                })
            })
        })

    function validate() {
    var userName = document.getElementById("userName-1");
    var password = document.getElementById("password-1");
    var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

    if (userName.value == "") {
        alert("邮箱不能为空！");
        userName.focus();
        return false;
    } else if (!pattern.test(userName.value)) {
        alert("请输入正确的邮箱格式！");
        userName.focus();
        return false;
    }
    if (password.value == "") {
        alert("密码不能为空！");
        password.focus();
        return false;
    } else if (password.value.length<5 || password.value.length>12) {
        alert("密码长度不符合要求，请输入5-12位密码!");
        password.focus();
        return false;
    }
    return true;
    }

    </script>
</head>
<body>
<div class="container">

    <!-- register module -->
    <div class="register-box">
        <div id="register-title">
            Register
        </div>

        <form action="${path}/registerServlet" method="post" onsubmit="return validate();">
            <div class="input">
                <input type="text" id="userName-1" placeholder="Create your userName" name="userName-1" autofocus> <span id="msg" style="color: red"></span>
            </div>
            <div class="input">
                <input type="password" id="password-1" placeholder="Create your password" name="password-1">
            </div>

            <div id="type">
                <input type="radio" name="userType" value="admin" disabled> 管理员&nbsp;&nbsp;&nbsp;
                <input type="radio" name="userType" value="photographer" > 摄影师&nbsp;&nbsp;&nbsp;
                <input type="radio" name="userType" value="user" checked="checked"> 用户
            </div>

            <div id="register-1">
                <input type="submit" value="Register" id="register-btn" /> &nbsp;
            </div>

            <div id="login-1">
                <input type="button" value="To Login" id="to-login-btn" />
            </div>
        </form>
            <div id="welcome-1">
                <img src="https://www.shiinaliu.com/pic/loading.gif" />
            </div>

    </div>

</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script type="module" src="<c:url value="/js/register.js"></c:url>"></script>
</body>
</html>