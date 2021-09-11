package kr.ac.daegu.jspmvc.biz;

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

public class LoginCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        System.out.println("id = " + id);
        System.out.println("password = " + password);

        // DAO
        MemberDAO memDAO = new MemberDAO();
        // id 기준으로 로그인 정보를 가져옴.
        MemberDTO member = null;
        try {
            member = memDAO.getLoginData(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 비밀번호 매칭
        return isPasswordMatch(password, member);
    }

    private boolean isPasswordMatch(String inputPassword, MemberDTO member) {
        String passwordSalt = inputPassword + member.getSalt();
        String encodedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(passwordSalt.getBytes(StandardCharsets.UTF_8));
            encodedPassword = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("=========여기는 LoginCmd 입니다=======");
        System.out.println("inputPassword = " + inputPassword);
        System.out.println("salt = " + passwordSalt);
        System.out.println("encodedPassword = " + encodedPassword);
        System.out.println("dbPassword = " + member.getPassword());
        System.out.println("=========여기는 LoginCmd 입니다=======");
        return member.getPassword().equals(encodedPassword);
    }
}