package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.common.PasswordEncoder;
import kr.ac.daegu.jspmvc.model.MemberDAO;
import kr.ac.daegu.jspmvc.model.MemberDTO;
import sun.security.util.Password;

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
        String encodePassword = PasswordEncoder.getEncodePassword(inputPassword, member.getSalt());
        return member.getPassword().equals(encodePassword);
    }
}