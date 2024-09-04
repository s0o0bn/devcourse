<%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-14
  Time: 오전 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 작성 화면</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/board.do?action=new" method="post">
    <input type="text" placeholder="제목을 입력하세요." id="title" name="title"/><br/>
    <textarea placeholder="내용을 입력하세요." name="content"></textarea><br/>
    <input type="submit" value="작성완료">
</form>
</body>
</html>
