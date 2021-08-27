<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl -->
<html>
<head>
    <title>전체 글 목록 보기</title>
</head>
<body>
<!-- BoardListCmd.java에서 가져온 데이터를 어떻게 jsp파일에서 html 형태로 보여줄 것인가? -->
<table>
    <tr>
        <td colspan="7">
            <label for="pagePerRow">페이지당 보여줄 글의 갯수 :</label>
            <form action="boardList.bbs?page=1" method="post">
                <input type="text" name="pagePerRow" id="pagePerRow" value="${pagePerRow}"/>
                <input type="submit" value="확인"/>
            </form>
        </td>
    </tr>
    <tr>
        <td>글번호</td>
        <td>작성자</td>
        <td>제목</td>
        <td>작성일</td>
        <td>작성시각</td>
        <td>조회수</td>
        <td>댓글갯수</td>
    </tr>
    <!-- jstl if 태그를 써서 글번호가 짝수인 글의 목록만 보여주시오. -->
    <c:forEach items="${boardRowList}" var="row">
        <tr>
            <td>${row.id}</td>
            <td>${row.author}</td>
            <td><a href="boardRead.bbs?id=${row.id}">${row.subject}</a></td><!-- 제목을 누르면 글 읽기에 해당하는 controller 요청-->
            <td>${row.writeDate}</td>
            <td>${row.writeTime}</td>
            <td>${row.readCount}</td>
            <td>${row.commentCount}</td>
        </tr>
    </c:forEach>
    <tr>
        <!-- boardInsert.jsp로 이동 되도록 처리 해 보시오. -->
        <td colspan="7"><a href="/jspmvc/view/boardInsert.jsp">[글 추가하기]</a></td>
    </tr>
    <tr>
        <td colspan="7">
            <c:forEach var="i" begin="1" end="${totalPageCount}">
            <a href="boardList.bbs?page=${i}&pagePerRow=${pagePerRow}">[<c:out value="${i}"/>]<a/>
                </c:forEach>
        </td>
    </tr>
</table>
나와야하는 페이지 선택 갯수 : ${totalPageCount}
</body>
</html>