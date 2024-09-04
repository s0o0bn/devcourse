<%@ page import="com.example.board_community.model.board.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 19.
  Time: 오전 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 작성 화면</title>
</head>
<body>
<%
    BoardDTO board = (BoardDTO) request.getAttribute("board");
%>
<form action="<%=request.getContextPath()%>/board.do?action=update&no=<%=board.getNo()%>" method="post">
    <input type="text" placeholder="제목을 입력하세요." value="<%=board.getTitle()%>" id="title" name="title"/><br/>
    <textarea placeholder="내용을 입력하세요." name="content"><%=board.getContent()%></textarea><br/>
    <input type="submit" value="작성완료">
</form>
</body>
</html>
