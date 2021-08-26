<%--
  Created by IntelliJ IDEA.
  User: keept
  Date: 2021-08-26
  Time: 오후 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수정할 비밀번호 입력</title>
</head>
<body>
<form action="../boardPwdCheck.bbs" method="post">
    <input type="hidden" name="boardDataId" value="<%= request.getParameter("id")%>">
    <table>
        <tr>
            <td><label for="password">비밀번호</label></td>
            <td><input type="password" name="password" id="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="[확인]">[취소]
            </td>
        </tr>
    </table>
</form>
</body>
</html>