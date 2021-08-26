<%--
  Created by IntelliJ IDEA.
  User: keept
  Date: 2021-08-25
  Time: 오후 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>글 읽기</title>
</head>
<body>
<!-- 글번호, 제목, 작성자, 내용, 작성일, 작성시각, 조회수(+1), 댓글수 -->
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
            <a href="boardList.bbs">[글 목록 보기]</a>
            <a href="./view/boardPassword.jsp?id=${boardData.id}">[수정하기]</a>
            [삭제하기]
        </td>
    </tr>
</table>
<!-- [글 목록 보기][수정하기][삭제하기] 글 목록보기 클릭하면 boardList.bbs로 foward -->
글 읽기 화면입니다.

</body>
</html>