<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>비밀번호 입력</title>
</head>
<body>
글 삭제를 위한 비밀번호 호출 화면입니다.
삭제처리 하는 controller url 호출 boardPwdCheckToDelete.bbs
비번 맞으면 delete처리 하고 boardList.bbs foward
틀리면 틀렸습니다 jsp출력
<form action="../boardPwdCheckToDelete.bbs" method="post">

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