<%@ page import="com.example.jpa.domain.dto.BoardThumbnailDTO" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-20
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판목록</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<a href="<%=request.getContextPath()%>/board/write">[글쓰기 하러가기]</a><br>
<table border="1">
    <%
        Page<BoardThumbnailDTO> pageData = (Page<BoardThumbnailDTO>)request.getAttribute("pageData");
        for(BoardThumbnailDTO b: pageData.getContent()){
    %>
    <tr>
        <td><%=b.no()%></td>
        <td><a href="<%=request.getContextPath()%>/board/<%=b.no()%>"><%=b.title()%></a></td>
        <td><%=b.writer()%></td>
        <td><%=b.createdAt()%></td>
        <td><%=b.readCount()%></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="5">
            <%
                // 1 2 3 4 5 [6] 7 8 9 10
                int current = pageData.getNumber();
                int last = Math.min(pageData.getTotalPages(), current + 5);
                for (int i = 1; i <= 5 && current - i > 0; i++) {

            %>
            <a href="<%=request.getContextPath()%>/board?page=<%=current - i%>"><%=current - i%></a>
            <a href="<%=request.getContextPath()%>/board?page=<%=pageData.getNumber()%>"><%=pageData.getNumber()%></a>
            <%
                }

                for (int i = 1; i <= 5 && current + i <= last; i++) {
            %>
            <a href="<%=request.getContextPath()%>/board?page=<%=current + i%>"><%=current + i%></a>
            <%
                }
            %>
<%--            <%--%>
<%--                int nowPage = (int) pageData.get("pageData");--%>
<%--                int startPage =  (int)  pageData.get("startPage");--%>
<%--                int endPage = (int)  pageData.get("endPage");--%>
<%--                int totalPage =  (int) pageData.get("totalPageCount");--%>

<%--                if(1<startPage){--%>
<%--            %>--%>
<%--            <a href="<%=request.getContextPath()%>/board/list?pageData=<%=startPage-1%>">[이전]</a>--%>
<%--            <%--%>
<%--                }--%>

<%--                for(int p=startPage; p<=endPage; p++){--%>
<%--            %>--%>
<%--            <a href="<%=request.getContextPath()%>/board/list?pageData=<%=p%>"> <%=p%>페이지 </a>--%>
<%--            <%--%>
<%--                }--%>
<%--                if(endPage < totalPage){--%>
<%--                    %>--%>
<%--            <a href="<%=request.getContextPath()%>/board/list?pageData=<%=endPage+1%>">[다음]</a>--%>
<%--                    <%--%>
<%--                }--%>
<%--            %>--%>
        </td>
    </tr>
</table>

</body>
</html>
