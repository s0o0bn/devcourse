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
    <title>Write</title>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<form action="<%=request.getContextPath()%>/board/write.do" method="post">
    <table>
        <tr>
            <td>Title: </td>
            <td><input type="text" placeholder="제목을 입력하세요." id="title" name="title"/></td>
        </tr>
        <tr>
            <td>Content: </td>
            <td><textarea placeholder="내용을 입력하세요." name="content"></textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="작성완료">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
