<%@ page import="com.example.jpa.domain.dto.BoardDTO" %>
<%@ page import="com.example.jpa.domain.dto.FileDTO" %><%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-20
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 상세화면</title>
</head>
<body>
<%@ include file="common/header.jsp"%>

<%
    BoardDTO bbb = (BoardDTO) request.getAttribute("bbb");
%>
<table border="1">
    <tr>
        <td>글번호 : </td>
        <td><%=bbb.no()%></td>
    </tr>
    <tr>
        <td>제목 : </td>
        <td><%=bbb.title()%></td>
    </tr>
    <tr>
        <td>작성자 : </td>
        <td><%=bbb.writer()%></td>
    </tr>
    <tr>
        <td>작성일시 : </td>
        <td><%=bbb.createdAt()%></td>
    </tr>
    <tr>
        <td>조회수 : </td>
        <td><%=bbb.readCount()%></td>
    </tr>
    <tr>
        <td>내용 : </td>
        <td><%=bbb.content()%></td>
    </tr>
    <%
        if(bbb.fileDTOList()!=null && !bbb.fileDTOList().isEmpty()){
    %>
    <tr>
        <td colspan="2">
            <%
            for(FileDTO f: bbb.fileDTOList()){
            %>
                <a href="<%=request.getContextPath()%>/board/download?fno=<%=f.no()%>">첨부파일 : <%=f.originalName()%></a><br>
            <%
            }
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<a href="#">[수정하기]</a>
<a href="<%=request.getContextPath()%>/board">[게시판 목록으로]</a>
<div id="divComment">
    <table border="1" id="tableComment">
        <tr><td>작성된 댓글이 없습니다.</td></tr>
    </table>
    작성자:<input type="text" id="uuu"><br>
    댓글 : <input type="text" id="ttt"><br>
    <button id="btnSend">댓글등록</button>
</div>
<script>
    async function getComments(){
        let url = 'http://localhost:8888/comment/list/'+<%=bbb.no()%>;

        let resp = await fetch(url);
        let result = await resp.json(); // response 헤더에 Content-type이 application/json으로 되어있었을 때 json 함수로 파싱해서 받는게 가능함.
        console.log('resp', resp);
        console.log('result', result);
        let table = document.getElementById('tableComment');
        if(result.length>0)
            table.innerHTML = '';

        for(let i=0; i<result.length; i++){
            let tr = document.createElement("tr");
            let tdWriter = document.createElement("td");
            let tdComment = document.createElement("td");
            tdWriter.appendChild(document.createTextNode(result[i].writer));
            tdComment.appendChild(document.createTextNode(result[i].content))
            tr.appendChild(tdWriter);
            tr.appendChild(tdComment);
            table.appendChild(tr);
        }
    }
    getComments();

    document.getElementById('btnSend').onclick = async function(){
        let uuu = document.getElementById('uuu').value;
        let ttt = document.getElementById('ttt').value;

        // alert('작성자:'+uuu+"//내용:"+ttt+" 라는 내용을 백엔드 서버에게 전송해주세요.");
        <%--let url = '<%=request.getContextPath()%>/comment/write?cwriter='+uuu+'&ccontent='+ttt+'&bno='+<%=bbb.getNo()%>;--%>
        <%--let url = 'http://localhost:8888/comment/list/comment/write?cwriter='+uuu+'&ccontent='+ttt+'&bno='+<%=bbb.getNo()%>;--%>
        let url = 'http://localhost:8080/api/board/<%=bbb.no()%>/comment';
        let body = JSON.stringify({
            writer: uuu,
            content: ttt
        });

        // fetch를 실행하면 백엔드에게 데이터를 보내고 response를 받게 되는데 그 response를 아직 안받았어도 아래 문장으로 내려가버림.
        let resp = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: body
        });
        let respData = await resp.text(); // 응답 response 객체에서 유효한 데이터를 뽑아내는 과정도 시간이 걸릴 수 있으니 기다렸다가 아래 문장 실행하러 가라

        // alert('백엔드 응답!! ' + respData);
        getComments();
    }
</script>
</body>
</html>
