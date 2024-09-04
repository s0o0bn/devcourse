<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 19.
  Time: 오전 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="<%=request.getContextPath()%>/board.do?action=list">[목록보기]</a>
    <%
        Optional<String> loginId = (Optional<String>) request.getAttribute("loginId");
        if (loginId.isEmpty()) {
    %>
    <a href="<%=request.getContextPath()%>/auth.do?action=login">[로그인하기]</a>
    <a href="<%=request.getContextPath()%>/auth.do?action=signup">[회원가입하기]</a>
    <%
    }
        else {
    %>
    <a href="<%=request.getContextPath()%>/auth.do?action=logout">[로그아웃하기]</a>
    <a href="<%=request.getContextPath()%>/board.do?action=new">[작성하러가기]</a>
    <%
        }
    %>
</body>
</html>
