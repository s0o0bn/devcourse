<%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 19.
  Time: 오전 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <script>
        alert('<%=request.getAttribute("msg")%>');
<%--        alert('<%=request.getAttribute("path")%>');--%>
        location.href = "<%=request.getAttribute("path")%>";
    </script>
</body>
</html>
