package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.BoardDAO;
import kr.ac.daegu.jspmvc.model.CommentDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardCommentInsertCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //요청받은 key,value들
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        String commentAuthor = request.getParameter("commentAuthor");
        String commentContent = request.getParameter("commentContent");
        int newId;
        //log
        System.out.println("boardId = " + boardId);
        System.out.println("commentAuthor = " + commentAuthor);
        System.out.println("commentContent = " + commentContent);

        BoardDAO dao = new BoardDAO();

        try {
            newId = dao.getCommentNewId();
            dao.insertCommnet(newId, boardId, commentAuthor, commentContent);


          //  request.setAttribute("NewIdCount", newId);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return true;
    }
}
