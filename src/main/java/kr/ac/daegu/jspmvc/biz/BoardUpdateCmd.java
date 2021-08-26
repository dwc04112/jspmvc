package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardUpdateCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터들
        int id = Integer.parseInt(request.getParameter("id"));
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        // db 접속하여 업데이트 쿼리 날림.
        BoardDAO dao = new BoardDAO();
        try {
            dao.updateBoardContent(id, subject, content);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return true; // 의미없음
    }
}