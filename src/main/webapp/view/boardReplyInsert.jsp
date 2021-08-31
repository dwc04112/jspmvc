<%--
  Created by IntelliJ IDEA.
  User: 사용자
  Date: 2021-08-25
  Time: 오후 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글추가하기</title>
</head>
<body>
<form action="../boardReplyInsert.bbs" method="post">
    <input type="hidden" name="boardPId" value="<%= request.getParameter("id")%>">
    <input type="hidden" name="boardDepth" value="<%= request.getParameter("depth")%>">
    <input type="hidden" name="boardPorder" value="<%= request.getParameter("porder")%>">
    <table>
        <tr>
            <td colspan="2">글 추가하기</td>
        </tr>
        <tr>
            <td><label for="subject">제목</label></td>
            <td><input type="text" name="subject" id="subject"/></td>
        </tr>
        <tr>
            <td><label for="author">작성자</label></td>
            <td><input type="text" name="author" id="author"/></td>
        </tr>
        <tr>
            <td><label for="content">내용</label></td>
            <td><input type="text" name="content" id="content"/></td>
        </tr>
        <tr>
            <td><label for="password">수정삭제비밀번호</label></td>
            <td><input type="password" name="password" id="password"/></td>
        </tr>
        <tr>
            <!--취소 버튼을 누르면 boardList.bbs 로 가도록 처리해 보시오-->
            <td colspan="2"><input type="submit" value="[글쓰기]"/>
                <a href="../boardList.bbs">[취소]</a></td>
        </tr>
    </table>
</form>
</body>
</html>
