<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="mavenProject.jsp" %>
<html>
<head>
    <title>添加预约信息</title>
    <style>
        * input:required, textareainput:required, textarea:required {
        }

        required {
        }

        #top_bit {
            width: 760px;
            margin: 0 auto;
        }

        form {
            width: 300px;
            margin: 20px auto;
        }

        p {
            line-height: 1.6;
        }

        input, textarea, select {
            background-color: #fff;
            border: 1px solid #ccc;
            font-size: 20px;
            width: 300px;
            min-height: 30px;
            display: block;
            margin-bottom: 16px;
            margin-top: 8px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-transition: all 0.5s ease-in-out;
            -moz-transition: all 0.5s ease-in-out;
            transition: all 0.5s ease-in-out;
        }

        textarea {
            min-height: 200px;
        }

        input:focus, textarea:focus {
            -webkit-box-shadow: 0 0 25px #ccc;
            -moz-box-shadow: 0 0 25px #ccc;
            box-shadow: 0 0 25px #ccc;
            -webkit-transform: scale(1.05);
            -moz-transform: scale(1.05);
            transform: scale(1.05);
        }

        input:not(:focus), textarea:not(:focus) {
            opacity: 0.5;
        }

        input:required, textarea:required {
        }

        input:valid, textarea:valid {
        }

        input:focus:invalid, textarea:focus:invalid {
        }

        input[type=submit] {
            padding: 10px;
            background: none;
            opacity: 1.0;
        }
    </style>
    <link href="style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function validate() {
            var cardID = document.getElementById("cardID");
            var telephone = document.getElementById("telephone");
            var telephonePattern = /^1[3456789]\d{9}$/;
            var cardIDPattert = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;

            if (cardID.value == "") {
                alert("身份证不能为空！");
                cardID.focus();
                return false;
            } else if (!cardIDPattert.test(cardID.value)) {
                alert("请输入正确的身份证格式！（18位）");
                cardID.focus();
                return false;
            }
            if (telephone.value == "") {
                alert("密码不能为空！");
                telephone.focus();
                return false;
            } else if (!telephonePattern.test(telephone.value)) {
                alert("请输入正确的手机号码格式");
                cardID.focus();
                return false;
            }
            return true;
        }
    </script>
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

<div>
    <div id="top_bit">
        <form action="${path}/editUserInfomationServlet?method=editUserInfomation&uid=${sessionScope.user.uid}&username=${sessionScope.user.username}"
              method="post" onsubmit="return validate();">
            <label>账号:</label>
            <input type="text" name="username" value="${user.getUsername()}" readonly="readonly"/>
            <label>姓名:</label>
            <input type="text" name="name" required placeholder="请输入姓名" value="${sessionScope.user.name}"/>
            <label>身份证号:</label>
            <input type="text" name="cardID" id="cardID" required placeholder="请输入身份证"
                   value="${sessionScope.user.cardID}"/>
            <label>手机号码:</label>
            <input type="text" name="telephone" ID="telephone" required placeholder="请输入手机号码"
                   value="${sessionScope.user.telephone}"/>
            <label>地址:</label>
            <div data-toggle="distpicker">
                <select id="eprovinceName" data-province="${sessionScope.provinceName}" name="provinceName"
                        value="${sessionScope.provinceName}"></select>
                <select id="ecityName" data-city="${sessionScope.cityName}" name="cityName"
                        value="${sessionScope.cityName}"></select>
                <select id="edistrictName" data-district="${sessionScope.districtName}" name="districtName"
                        value="${sessionScope.districtName}"></select>
            </div>
            <input type="submit" value="保      存"/>
            <input type="hidden" value="${sessionScope.user.password}" name="password">
            <input type="hidden" value="${sessionScope.user.addtimes}" name="addtimes">

        </form>
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


</body>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/distpicker.data.js"></script>
<script src="js/distpicker.js"></script>
<script src="js/main.js"></script>
<script>
    $("#tijiao").click(function () {
        var province = $("#eprovinceName").find("option:selected").text();
        var city = $("#ecityName").find("option:selected").text();
        var district = $("#edistrictName").find("option:selected").text();
    });
</script>
</html>