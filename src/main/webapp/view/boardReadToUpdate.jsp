
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

글컨텐츠 수정/삭제를 위한 패스워드가 일치하여 제공하는 글 수정 화면 입니다.
<table>
    <tr>
        <td>글번호</td>
        <td>${boardData.id}</td>
    </tr>
    <tr>
        <td>제목</td>
        <td>${boardData.subject}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>${boardData.author}</td>
    </tr>
    <tr>
        <td>내용</td>
        <td>${boardData.content}</td>
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
            [수정하기]
            [취소하기]
        </td>
    </tr>
</table>
</body>
</html>