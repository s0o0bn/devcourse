<%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 19.
  Time: 오전 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String idValue = (String) request.getAttribute("userId");
    idValue = idValue == null ? "" : idValue;
%>
    <form action="<%=request.getContextPath()%>/auth.do?action=login" method="post">
        ID : <input type="text" name="userId" value="<%=idValue%>"/><br>
        PW : <input type="password" name="password"/><br>
        <div>
            <input type="checkbox" name="remember-me" id="remember-me"/>
            <label for="remember-me">아이디 기억하기</label>
            <br>
        </div>
        <input type="submit" value="로그인">
    </form>
</body>
</html>
