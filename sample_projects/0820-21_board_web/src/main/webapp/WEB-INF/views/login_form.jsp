<%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 20.
  Time: 오후 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/member/login.do" method="post">
    <table>
        <tr>
            <td>ID: </td>
            <td><input type="text" name="userId"/></td>
        </tr>
        <tr>
            <td>PW: </td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="로그인">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
