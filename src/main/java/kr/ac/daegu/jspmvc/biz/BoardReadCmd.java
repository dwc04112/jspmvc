package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.BoardDAO;
import kr.ac.daegu.jspmvc.model.BoardDTO;
import kr.ac.daegu.jspmvc.model.CommentDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardReadCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parsing
        // 데이터의 형변환(참조형 -> 기본형)
        // java wrapper class
        int id = Integer.parseInt(request.getParameter("id"));
        // db에 접근해서 데이터 가져오는 인스턴스
        BoardDAO dao = new BoardDAO();

        // dao 기능 호출해서 가져온 db 데이터를 저장하는 컬렉션
        ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();

        // dao의 기능중 id값으로 한건의 데이터를 담는 객체 준비.
        BoardDTO boardData = new BoardDTO();
        try {
            // 조회수 +1
            // boardRowPlusReadCount() : readCount 업데이트 해라, id : 어느 row의 id값인지?, 1 : 얼마나 업데이트 할건지?
            dao.boardRowPlusReadCount(id, 1);
            boardData = dao.getBoardData(id);



            list = dao.getBoardCommentList();
            /*
             * 가져온 db 데이터 리스트를 어떻게 jsp로 보여줄것인가?
             * */

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //  boardRead.jsp에 보여줄 데이터를 셋
        request.setAttribute("boardData", boardData);
        // 댓글 보여주기
        request.setAttribute("CommentRowList", list);
        return true;
    }
}