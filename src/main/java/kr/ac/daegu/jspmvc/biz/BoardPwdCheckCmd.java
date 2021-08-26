package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.BoardDAO;
import kr.ac.daegu.jspmvc.model.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardPwdCheckCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("boardDataId"));
        String userInputPassword = request.getParameter("password");
        BoardDAO dao = new BoardDAO();

        try {
            // id값 기준 db board 테이블 조회하여 1개의 board 데이터row 가져옴.
            BoardDTO selectedData = dao.getBoardData(id);

            System.out.println("입력id, password=" + id + ", " + userInputPassword);
            System.out.println("dbid, password=" + selectedData.getId() + ", " + selectedData.getPassword());

            // client로부터 입력받은 패스워드와 board 데이터 row의 패스워드 비교
            if(userInputPassword.equals(selectedData.getPassword())){
                // 기존 글 내용 가져와서 보여주기
                request.setAttribute("boardData", selectedData);
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}