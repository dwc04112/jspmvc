package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.common.PasswordEncoder;
import kr.ac.daegu.jspmvc.model.BoardDTO;
import kr.ac.daegu.jspmvc.model.MemberDAO;
import kr.ac.daegu.jspmvc.model.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class SignUpCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newMid;
        int memberId; //입력한 id값이 이미 존재하면 1 없으면0
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        // String salt = request.getParameter("salt");
        String salt = String.valueOf(UUID.randomUUID());    // 무작위 문자열 : 회원가입 할 때 생성되어 DB의 salt 컬럼에 insert된다
        //String passwordSalt =  password + salt;

        System.out.println("입력받은 id = " + id);
        System.out.println("입력받은 pwd = " + password);



/*
        try{


            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();;
            digest.update(passwordSalt.getBytes(StandardCharsets.UTF_8));
            password= String.format("%040x",new BigInteger(1,digest.digest()));
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
 */


        MemberDAO memDAO = new MemberDAO();

        String encodedPassword = PasswordEncoder.getEncodePassword(password, salt);
        System.out.println("해싱 pwd = " + encodedPassword);

        try {
            memberId = memDAO.compareId(id);
            if(memberId==0) {
                // board 테이블에 들어갈 id값을 가져오기 : board.id중에서 가장 높은 id값 + 1
                newMid = memDAO.getMemberNewMid();
                // dao 기능 호출해서 enduser가 입력한 데이터를 insert
                memDAO.insertJoinMember(newMid, id, encodedPassword, salt);
                return true;
            }else{
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
