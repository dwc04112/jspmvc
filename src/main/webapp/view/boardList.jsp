<%--
  Created by IntelliJ IDEA.
  User: keept
  Date: 2021-08-24
  Time: 오후 4:56
  To change this template use File | Settings | File Templates.
--%>
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
            <!-- calibration : Key,Value 값을 '처리에 의미 있는 있는 범위 내로' 데이터 처리 -->
            <!-- validation  : Key,Value 값 검증(처리할 수 있는 데이터 타입인가? 데이터 포맷인가?) -->
            <form action="boardList.bbs?page=1" name="reqPagePerRow" method="post">
                <input type="text" name="pagePerRow" id="pagePerRow" value="${pagePerRow}"/>
            </form>
            <button onclick="checkPagePerRow()">확인</button>
            <%--                <input type="submit" value="확인"/>--%>

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

    <c:forEach items="${boardRowList}" var="row">
        <tr>
            <td>
                ${row.id}
                <!--여기 수정해야함-->
            </td>
            <td>${row.author}</td>
            <td>
                <c:if test="${row.depth > 0}">
                    <c:forEach var="i" begin="0" end="${row.depth -1}" >
                        <c:out value="[RE:]"/>
                    </c:forEach>

                </c:if>
                <a href="boardRead.bbs?id=${row.id}">${row.subject}</a>
                <!-- 제목을 누르면 글 읽기에 해당하는 controller 요청-->
            </td>
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
<script>
    function checkPagePerRow() {
        // input name="pagePerRow"의 데이터 가져와서
        const value = document.getElementById("pagePerRow").value;
        console.log('value', value);
        // 숫자로 변환 가능한지 체크
        if(!isNaN(Number(value))){
            // 변환가능 : submit
            reqPagePerRow.submit();
        } else {
            // 불가능 : alert('숫자를 정확히 입력 해 주세요')
            alert('숫자를 정확히 입력 해 주세요');
        }
    }
</script>
나와야하는 페이지 선택 갯수 : ${totalPageCount}

</body>
</html>