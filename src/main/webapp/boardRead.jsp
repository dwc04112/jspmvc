<%--
  Created by IntelliJ IDEA.
  User: keept
  Date: 2021-08-25
  Time: 오후 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 읽기</title>
</head>
<body>
<!-- 글번호, 제목, 작성자, 내용, 작성일, 작성시각, 조회수(+1), 댓글수 -->

<!-- [글 목록 보기][수정하기][삭제하기] 글 목록보기 클릭하면 boardList.bbs로 foward -->
<form action="../boardRead.bbs" method="post">
    <table>

        <tr>
            <td colspan="2">글 목록</td>
        </tr>
        <tr>
            <td><label for="subject">제목</label></td>
            <td><output type="text" name="subject" id="subject"/></td>
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
            <td><label for="writeDate">작성일</label></td>
            <td><input type="Date" name="writeDate" id="writeDate"/></td>
        </tr>
        <tr>
            <td><label for="wirteTime">작성시간</label></td>
            <td><input type="Date" name="wirteTime" id="wirteTime"/></td>
        </tr>
        <tr>
            <td><label for="readCount">조회수</label></td>
            <td><input type="Date" name="readCount" id="readCount"/></td>
        </tr>
        <tr>
            <td><label for="commentCount">댓글수</label></td>
            <td><input type="Date" name="commentCount" id="commentCount"/></td>
        </tr>
        <tr>
            <!--취소 버튼을 누르면 boardList.bbs 로 가도록 처리해 보시오-->
            <td colspan="2">
                <a href="../boardList.bbs">[글 목록보기]</a>[수정하기][삭제하기]</td>
        </tr>
    </table>

</body>
</html>