<%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 20.
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<a href="<%=request.getContextPath()%>/board/list.do">[목록보기]</a>
<a href="<%=request.getContextPath()%>/board/writeForm.do">[새 글 작성]</a>
</body>
</html>
