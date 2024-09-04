<%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 21.
  Time: 오전 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Hello World~~~~!!
<form action="<%=request.getContextPath()%>/bye" method="get">
    param1 : <input type="text" name="param1"><br>
    param2 : <input type="number" name="param2"><br>
    <input type="submit" value="전송">
</form>
</body>
</html>
