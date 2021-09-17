<%--
  Created by IntelliJ IDEA.
  User: 유승엽
  Date: 2021-09-13
  Time: 오후 2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    window.opener.location.href=""
</script>
<center>
    <%
        Object idCheck = request.getAttribute("memIdCheck");
        int idCheck1 = (Integer)idCheck;
        if(idCheck1 ==0){
        %>사용 가능한 아이디입니다.<br><%
        }else{ %>
        이미 존재하는 아이디입니다.<br> 다시한번 입력해주세요.<br><%
        }%>

    <span><a href="#" onclick="self.close()">[확인]</a></span>
</center>
숫자는 ? :: ${memIdCheck}
</body>
</html>
