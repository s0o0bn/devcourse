<%@ page import="java.util.List" %>
<%@ page import="com.example.board_community.model.board.BoardDTO" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-13
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 목록</title>
</head>
<body>
<table border="1">
<%
    Optional<String> userId = (Optional<String>) request.getAttribute("userId");
    List<BoardDTO> bList = (List<BoardDTO>) request.getAttribute("boards");
    for(BoardDTO b: bList){
%>
        <tr>
            <td><%=b.getNo()%></td>
            <td><a href="<%=request.getContextPath()%>/board.do?action=detail&no=<%=b.getNo()%>"><%=b.getTitle()%></a></td>
            <td><%=b.getWriter()%></td>
            <td><%=b.getRegDate()%></td>
            <td><%=b.getReadCount()%></td>
        </tr>
<%
    }
%>
</table>
<a href="<%=request.getContextPath()%>/main.do">[메인으로]</a>
<%
    if (userId.isPresent()) {
%>
<a href="<%=request.getContextPath()%>/board.do?action=new">[게시글 작성하러 가기]</a>
<%
    }
%>
</body>
</html>
