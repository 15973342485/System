<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@include file="mavenProject.jsp" %>
<html>
<head>
    <title>添加预约信息</title>
    <style>
        *input:required, textareainput:required, textarea:required{} required{}
        #top_bit{width: 760px;margin: 0 auto;}
        form{width: 300px;margin: 20px auto;}
        p{line-height: 1.6;}
        input, textarea,select{background-color: #fff;
            border: 1px solid #ccc;
            font-size: 20px;width: 300px;
            min-height: 30px;
            display: block;
            margin-bottom: 16px;margin-top: 8px;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;-webkit-transition: all 0.5s ease-in-out;-moz-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;}
        textarea{min-height: 200px;}
        input:focus, textarea:focus{-webkit-box-shadow: 0 0 25px #ccc;-moz-box-shadow: 0 0 25px #ccc;box-shadow: 0 0 25px #ccc;-webkit-transform: scale(1.05);-moz-transform: scale(1.05);transform: scale(1.05);}
        input:not(:focus), textarea:not(:focus){opacity: 0.5;}
        input:required, textarea:required{}
        input:valid, textarea:valid{}
        input:focus:invalid, textarea:focus:invalid{}
        input[type=submit]{padding: 10px;background: none;opacity: 1.0;}
    </style>
</head>
<body>
<div id="top_bit">
    <form action="${path}/ReserveServlet?method=updateReserve" method="post">
        <label >摄影师ID:</label>
        <select name="pid" >
            <c:if test="${not empty sessionScope.photographerList}">
                <c:forEach items="${sessionScope.photographerList}" var="photographer">
                    <option value="${photographer.pid}" >${photographer.name}</option>
                </c:forEach>
            </c:if>

        </select>


        <label >顾客ID:</label>
        <input type="text" name="uid" required placeholder="顾客ID" value="${user.getUid()}" readonly="readonly"/>
        <label >类型:</label>
        <select name="rtype" >
            <option value="亲子图">亲子图</option>
            <option value="结婚照">结婚照</option>
            <option value="个人照">个人照</option>
            <option value="全家福">全家福</option>
        </select>
        <label >预约日期:</label>
        <input type="date" name="date" value="${sessionScope.reservelist.times}"/>
        <label >备注:</label>
        <textarea name="texts" required placeholder="${sessionScope.reservelist.texts}" ></textarea>
        <input type="submit" value="保      存" />
    </form>
</div>
</body>
</html>