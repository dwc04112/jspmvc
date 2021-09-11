<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <script type="text/javascript">

        function tocheckpw1(){
            var pw = document.getElementById("password").value;
            var pwCheck = document.getElementById("passwordCheck").value;

            if(pwCheck == ""){
                document.getElementById('pwsame').innerHTML = "비밀번호 확인을 입력해주세요."
            }else {
                if (pw != pwCheck) {
                    //alert('비밀번호가 틀렸습니다. 다시 입력해주세요');
                    document.getElementById('pwsame').innerHTML = "비밀번호가 일치하지 않습니다."
                    return false;
                } else {
                    document.getElementById('pwsame').innerHTML = "비밀번호가 일치합니다."
                }
            }
        }
        function tocheckpw2(){
            var pw = document.getElementById("password").value;
            var pwCheck = document.getElementById("passwordCheck").value;
            if (pw != pwCheck) {
                alert('비밀번호가 틀렸습니다. 다시 입력해주세요');
                return false;
            }else if(pw == pwCheck){
                alert('회원가입 완료!')
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
            <td><input type="text" name="id" id="id"/></td>
        </tr>
        <tr>
            <td><label for="password">비번</label></td>
            <td><input type="text" name="password" id="password" onkeyup="tocheckpw1()"/></td>
        </tr>
        <tr>
            <td><label for="passwordCheck">비번확인</label></td>
            <td><input type="text" name="passwordCheck" id="passwordCheck"  placeholder="비밀번호 확인" onkeyup="tocheckpw1()"></td>
            <td id ="pwsame" style="color:rosybrown;"></td>
        </tr>

        <tr>
            <!-- javascript로 비번확인 체크 로직까지 넣어서 회원가입 요청 처리 -->
            <!-- 같으면 signUp.bbs 요청 날림 -->
            <!-- 다르면 alert('비번을 정확히 입력 바랍니다') -->
            <input type="submit" value="회원가입"/>
        </tr>
    </table>
</form>

</body>
</html>

