package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.BoardDAO;
import kr.ac.daegu.jspmvc.model.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * 글 목록 조회 요청이 들어왔을 때 DB에서 데이터를 가져옴
 * view로 보낼 데이터 셋의 정의.
 * */
public class BoardListCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // db에 접근해서 데이터 가져오는 인스턴스
        BoardDAO dao = new BoardDAO();
        // dao 기능 호출해서 가져온 db 데이터를 저장하는 컬렉션
        ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

        try {
            list = dao.getBoardList(); // 여기까지는 성공

            /*
             * 가져온 db 데이터 리스트를 어떻게 jsp로 보여줄것인가?
             * */
            request.setAttribute("boardRowList", list);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}