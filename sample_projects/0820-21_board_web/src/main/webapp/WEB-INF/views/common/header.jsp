<%--
  Created by IntelliJ IDEA.
  User: yoonsoobin
  Date: 2024. 8. 20.
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userId = (String) session.getAttribute("loginId");
    if (userId != null) {
%>
        <%=userId%>님 로그인 중입니다.
        <a href="<%=request.getContextPath()%>/member/logout.do">[로그아웃]</a>
<%
    } else {
%>
        <a href="<%=request.getContextPath()%>/member/loginForm.do">[로그인]</a>
        <a href="#">[회원가입]</a>
<%
    }
%>
<hr>
