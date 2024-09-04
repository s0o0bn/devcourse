<%@ page import="com.example.board_web.model.dto.BoardDTO" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 20.
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<%
    BoardDTO board = (BoardDTO) request.getAttribute("board");
%>
<table border="1">
    <tr>
        <td>글번호 : </td>
        <td><%=board.getNo()%></td>
    </tr>
    <tr>
        <td>제목 : </td>
        <td><%=board.getTitle()%></td>
    </tr>
    <tr>
        <td>작성자 : </td>
        <td><%=board.getWriter()%></td>
    </tr>
    <tr>
        <td>작성일시 : </td>
        <td><%=board.getRegDate()%></td>
    </tr>
    <tr>
        <td>조회수 : </td>
        <td><%=board.getReadCount()%></td>
    </tr>
    <tr>
        <td>내용 : </td>
        <td><%=board.getContent()%></td>
    </tr>
</table>
<%
    if (userId != null && userId.equals(board.getWriter())) {
%>
<a href="<%=request.getContextPath()%>/board/update.do?&no=<%=board.getNo()%>">[수정하기]</a>
<a href="<%=request.getContextPath()%>/board/delete.do?&no=<%=board.getNo()%>">[삭제하기]</a>
<%
    }
%>
<a href="<%=request.getContextPath()%>/board/list.do">[게시판 목록으로]</a>
</body>
</html>
