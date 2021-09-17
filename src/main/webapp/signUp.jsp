<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <script type="text/javascript">

        function tocheckpw1(){
            var pw = document.getElementById("password").value;
            var pwCheck = document.getElementById("passwordCheck").value;

            if(pwCheck === ""){         // ==과 ===차이 기준 : 타입의 값과 타입까지 비교하냐?
                document.getElementById('pwsame').innerHTML = "비밀번호 확인을 입력해주세요."
            }else {
                if (pw !== pwCheck) {
                    //alert('비밀번호가 틀렸습니다. 다시 입력해주세요');
                    document.getElementById('pwsame').innerHTML = "비밀번호가 일치하지 않습니다."
                    return false;
                } else {
                    document.getElementById('pwsame').innerHTML = "비밀번호가 일치합니다."
                }
            }
        }

        function idcheck(){
            //0912====
            //중복확인 누르면 (아이디가 입력되었을때) idCheckCmd로 이동해서 입력한 아이디값이 중복되는지 확인한다
            //있으면 1 없으면 0

            var id = document.getElementById("id").value;
            if(id.length<1 || id==null){
                alert("중복체크할 아이디를 입력하십시오");
                return false;
            }
            /*
            var url = "idCheck.bbs?id="+ id;
            window.open(url, "get", "height = 180, width = 300");
            return true;
             */
            location.replace("idCheck.bbs?id="+ id);
        }
        function ConfId() {
            document.getElementById("finalConf").value = "idNonPass";
        }

        function tocheckpw2(){
            var pw = document.getElementById("password").value;
            var pwCheck = document.getElementById("passwordCheck").value;
            var Conf = document.getElementById("finalConf").value;

            if(${memIdCheck==0}){
                if(pw.length<1 || pw===null){
                    alert("비밀번호를 입력해주세요");  // 비밀번호를 입력 안하고 로그인
                    return false;
                }else {
                    if (pw !== pwCheck) {
                        alert('비밀번호가 틀렸습니다. 다시 입력해주세요');
                        return false;   // 비밀번호 불일치 로그인
                    }else{
                        if(Conf === "idPass") {
                            alert("회원가입 완료!")   //로그인 성공
                        }else if(Conf ==="idNonPass"){
                            alert("중복확인 후 아이디를 변경하지 말아주세요. 다시 중복확인");
                            return false;   //중복확인 후 id입력감지 = NonPass
                        }
                    }
                }
            }else{
                alert("중복확인을 실행해주세요");
                return false;
            }
        }






    </script>
    <title>회원가입 화면</title>
</head>
<body>
<form action="signUp.bbs" method="post" onsubmit="return tocheckpw2()" data-ajax ="false" >
    <table>
            <tr>
                <td><label for="id">아이디</label></td>
                <!--0917여기서 CofId란 중복확인을 끝나고 id에 키 입력이 있으면 다시 NonPass 즉 중복확인 실패 상태로 되돌림-->
                <td><input type="text" name="id" id="id" value="${idValue}" onkeydown="ConfId()"/></td>
                <!--0912 중복된 아이디가 있으면 어떻게 처리할까? 0912==-->
                <td><input type="button" value="중복확인" onclick="return idcheck()"/></td>
                <td style="color: rosybrown;">
                <!--아래 value가 pass면 중복확인 통과/ NonPass면 중복확인 실패-->
                <td><input type="hidden" id="finalConf" value="idPass"></td>
            </tr>
        <tr>
            <td><label for="password">비번</label></td>
            <td><input type="text" name="password" id="password"  onkeyup="tocheckpw1()"/></td>
        </tr>
        <tr>
            <td><label for="passwordCheck">비번확인</label></td>
            <td><input type="text" name="passwordCheck" id="passwordCheck"  placeholder="비밀번호 확인" onkeyup="tocheckpw1()"></td>
            <td colspan="2" id ="pwsame" style="color:rosybrown;"></td>
        </tr>

        <tr>
            <!-- javascript로 비번확인 체크 로직까지 넣어서 회원가입 요청 처리 -->
            <!-- 같으면 signUp.bbs 요청 날림 -->
            <!-- 다르면 alert('비번을 정확히 입력 바랍니다') -->
            <input type="submit" value="회원가입"/>
        </tr>
    </table>
</form>
나와야 하는 숫자 1 or 0 ::: ${memIdCheck}
<c:if test="${memIdCheck==1}">
    <script> alert("이미 존재하는 아이디 입니다.") </script>
</c:if>
<c:if test="${memIdCheck==0}">
    <script> alert("사용 가능한 아이디 입니다.") </script>
</c:if>

</body>
</html>

