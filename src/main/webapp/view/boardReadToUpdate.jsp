
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

글컨텐츠 수정/삭제를 위한 패스워드가 일치하여 제공하는 글 수정 화면 입니다.
<form action="boardUpdate.bbs" method="post">
    <input type="hidden" name="id" value="${boardData.id}">
<table>
    <tr>
        <td>글번호</td>
        <td>${boardData.id}</td>
    </tr>
    <tr>
        <td><label for="subject">제목</label></td>
        <td><input type="text" name="subject" id="subject" value="${boardData.subject}"></td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>${boardData.author}</td>
    </tr>
    <tr>
        <td><label for="content">내용</label></td>
        <td><input type="text" name="content" id="content" value="${boardData.content}"></td>
    </tr>
    <tr>
        <td>작성일</td>
        <td>${boardData.writeDate}</td>
    </tr>
    <tr>
        <td>작성시각</td>
        <td>${boardData.writeTime}</td>
    </tr>
    <tr>
        <td>조회수</td>
        <td>${boardData.readCount}</td>
    </tr>
    <tr>
        <td>댓글수</td>
        <td>${boardData.commentCount}</td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="[수정하기]">
            <a href="boardList.bbs">[취소하기]</a>
        </td>
    </tr>
</table>
</form>
</body>
</html>