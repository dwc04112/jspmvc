
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
        <td>
            <!--처음만든 댓글 수 구하기-->
            <c:forEach items="${CommentRowList}" var="row">
                <c:if test="${boardData.id == row.id}">
                    <c:set var="i" value="${i+1}"/>
                </c:if>
            </c:forEach>
            <c:out value="${i}"/>
            <c:if test="${i==null}">
                <c:set var="i" value="0"/>
            </c:if>
        </td>
    </tr>
    <tr>
        <!-- 두번째 만든 댓글 수 구하기-->
        <td>새로만든 댓글 수</td>
        <td>${boardData.commentCount}</td>
    </tr>



    <tr>
        <td colspan="2">
            <a href="boardList.bbs">[글 목록 보기]</a>
            <a href="./view/boardPasswordToUpdate.jsp?id=${boardData.id}">[수정하기]</a>
            <a href="./view/boardPasswordToDelete.jsp?id=${boardData.id}">[삭제하기]</a>
            <!-- ORDER : 패스워드 확인 하고 boardPasswordToDelete.jsp -->
        </td>
    </tr>
    <tr>
        <td><label for ="commentAuthor">작성자</label></td>
        <td><laber for ="commentContent">댓글 내용</laber></td>
    </tr>
    <form action="commentInsert.bbs" method="post">
    <tr>
        <input type="hidden" name="boardId" value="${boardData.id}"/>
        <td><input type="text" name="commentAuthor" id="commentAuthor"/></td>
        <td><input type="text" name="commentContent" id ="commentContent"/></td>
        <td><input type="submit" value="[확인]"/></td>
    </tr>
    </form>
</table>
================================
<table>
    <!-- 작성한 댓글목록 출력해야함 -->
    <tr>

        <td>작성자</td>
        <td>내용</td>
        <td>작성일</td>
        <td>작성시각</td>
    </tr>
    <c:forEach items="${CommentRowList}" var="row">
        <c:if test="${boardData.id == row.id}">
        <tr>

            <td>${row.author}</td>
            <td>${row.content}</td>
            <td>${row.writeDate}</td>
            <td>${row.writeTime}</td>
        </tr>
        </c:if>
    </c:forEach>
</table>
<!--댓글 입력하는 창-->


<!-- 작성한 댓글목록 출력해야함 -->


<!-- [글 목록 보기][수정하기][삭제하기] 글 목록보기 클릭하면 boardList.bbs로 foward -->
글 읽기 화면입니다.

</body>
</html>