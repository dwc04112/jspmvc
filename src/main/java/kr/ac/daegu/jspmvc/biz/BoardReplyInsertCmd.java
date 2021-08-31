package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardReplyInsertCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int newPid = Integer.parseInt(request.getParameter("boardPid"));

        int newId;
        int depth;
        int porder;
        String subject = request.getParameter("subject");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        String password = request.getParameter("password");
        // enduser로부터 입력받은 데이터 잘 들어왔는지 확인 log
        System.out.println("subject=" + subject);
        System.out.println("author=" + author);
        System.out.println("content=" + content);
        System.out.println("password=" + password);

        // db에 접근해서 데이터 가져오는 인스턴스
        BoardDAO dao = new BoardDAO();

        try {
            // board 테이블에 들어갈 id값을 가져오기 : board.id중에서 가장 높은 id값 + 1
            newId = dao.getBoardNewId();
            depth = dao.getBoardDepth();
            // dao 기능 호출해서 enduser가 입력한 데이터를 insert
            dao.insertBoardReplyContent(newId, subject, author, content, password,newPid, depth, porder);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
