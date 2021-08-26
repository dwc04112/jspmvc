
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수정할 비밀번호 입력</title>
</head>
<body>
<input type="hidden" name="boardDataId" value="<%= request.getParameter("id")%>">
<table>
    <tr>
        <td><label for="password">비밀번호</label></td>
        <td><input type="password" name="password" id="password"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <a href="../boardPwdCheck.bbs">[확인]</a>[취소]
        </td>
    </tr>
</table>

</body>
</html>