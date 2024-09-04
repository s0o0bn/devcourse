<%@ page import="com.example.board_community.model.board.BoardDTO" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-14
  Time: 오전 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 상세화면</title>
</head>
<body>
    <%
        BoardDTO board = (BoardDTO) request.getAttribute("board");
        Optional<String> userId = (Optional<String>) request.getAttribute("userId");
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
        if (userId.isPresent() && userId.get().equals(board.getWriter())) {
    %>
    <a href="<%=request.getContextPath()%>/board.do?action=update&no=<%=board.getNo()%>">[수정하기]</a>
    <a href="<%=request.getContextPath()%>/board.do?action=delete&no=<%=board.getNo()%>">[삭제하기]</a>
    <%
        }
    %>
    <a href="<%=request.getContextPath()%>/board.do?action=list">[게시판 목록으로]</a>
</body>
</html>
