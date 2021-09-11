<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<form action="login.bbs" method="post">
    <table>
        <tr>
            <td><label for="id">아이디</label></td>
            <td><input type="text" name="id" id="id"/></td>
        </tr>
        <tr>
            <td><label for="password">비번</label></td>
            <td><input type="text" name="password" id="password"/></td>
        </tr>
        <tr>
            <input type="submit" value="로그인"/>
            <a href="signUp.jsp">회원가입</a>
        </tr>
    </table>
</form>
</body>
</html>