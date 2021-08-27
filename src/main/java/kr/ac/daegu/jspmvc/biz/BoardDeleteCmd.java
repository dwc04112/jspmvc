package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardDeleteCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 정수의 모양을 한 String = "0" , "11234"
        // request.getParameter("boardDataId")로 들어오는 값에 문자열이 섞여있을 경우
        // NullPointerException 발생
        // 좀더 방어적인 코드 : NPE 발생 가능성 차단
        int id = Integer.parseInt(request.getParameter("boardDataId")); //여기서 그냥 id를 넣어서 틀렸었나?
        // db 접속해서 id에 해당하는 글 삭제
        BoardDAO dao = new BoardDAO();
        try {
            dao.deleteBoardData(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
