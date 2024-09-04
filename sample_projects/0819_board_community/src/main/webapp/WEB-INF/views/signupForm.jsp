<%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 19.
  Time: 오후 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 화면</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/auth.do?action=signup" method="post">
    NAME : <input type="text" name="name"/><br>
    ID : <input type="text" name="userId"/><br>
    PW : <input type="password" name="password"/><br>
    <input type="submit" value="회원가입">
</form>
</body>
</html>
