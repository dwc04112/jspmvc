package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.MemberDAO;
import kr.ac.daegu.jspmvc.model.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class idCheckCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0912===

        String id = request.getParameter("id");


        MemberDAO memDAO = new MemberDAO();
        try {
            System.out.println("받은 아이디는 ? =" + id);
            int memberId = memDAO.compareId(id);
            System.out.println("중복확인 버튼 눌렀을때 :: 중복된 ID값이 있나요?? 있으면 1 없으면 0 == " +memberId);
            request.setAttribute("memIdCheck", memberId);
            if(memberId==0) {
                request.setAttribute("idValue", id);
            }else{
                id ="새로운 아이디 입력";
                request.setAttribute("idValue", id);
            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
