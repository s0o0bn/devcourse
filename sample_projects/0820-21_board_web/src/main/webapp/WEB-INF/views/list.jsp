<%@ page import="java.util.Optional" %>
<%@ page import="com.example.board_web.model.dto.BoardDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 20.
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>List</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<table border="1">
    <%
        List<BoardDTO> bList = (List<BoardDTO>) request.getAttribute("boards");
        for(BoardDTO b: bList){
    %>
    <tr>
        <td><%=b.getNo()%></td>
        <td><a href="<%=request.getContextPath()%>/board/read.do?no=<%=b.getNo()%>"><%=b.getTitle()%></a></td>
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
    if (userId != null) {
%>
<a href="<%=request.getContextPath()%>/board/writeForm.do">[게시글 작성하러 가기]</a>
<%
    }
%>
</body>
</html>
