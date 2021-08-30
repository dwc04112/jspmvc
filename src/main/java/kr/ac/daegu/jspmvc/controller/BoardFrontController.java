package kr.ac.daegu.jspmvc.controller;

import kr.ac.daegu.jspmvc.biz.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.bbs") // jspmvc/*.bbs 으로 들어오는 모든 요청을 처리하는 서블릿
public class BoardFrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String cmdURI = requestURI.substring(contextPath.length());
        // log
        System.out.println("requestURI = " + requestURI);
        System.out.println("contextPath = " + contextPath);
        System.out.println("cmdURI = " + cmdURI);

        BoardCmd cmd = null;
        String viewPage = null;

        // 글 목록 조회 처리(/jspmvc/boardList.bbs)
        if (cmdURI.equals("/boardList.bbs")) {
            cmd = new BoardListCmd();
            cmd.execute(request, response);
            viewPage = "view/boardList.jsp";
        }
        // 글 추가하기
        if (cmdURI.equals("/boardInsert.bbs")) {
            // enduser가 작성한 글을 db에 insert 시키는
            // bizness logic을 작성
            cmd = new BoardInsertCmd();
            cmd.execute(request, response);
            viewPage = "boardList.bbs";
        }
        // 글 읽기
        if (cmdURI.equals("/boardRead.bbs")) {
            cmd = new BoardReadCmd();
            cmd.execute(request, response);
            viewPage = "view/boardRead.jsp";
        }
        /*
         * 수정하기 관련
         * */
        // 글의 패스워드 체크
        if (cmdURI.equals("/boardPwdCheckToUpdate.bbs")) {
            cmd = new BoardPwdCheckCmd();
            boolean isPasswordCorrect = cmd.execute(request, response);
            if (isPasswordCorrect) {
                viewPage = "view/boardReadToUpdate.jsp";
            } else {
                viewPage = "view/boardPwdCheckFalse.jsp";
            }
        }
        // 글 수정 처리
        if (cmdURI.equals("/boardUpdate.bbs")) {
            // 글 수정처리 bizlogic
            cmd = new BoardUpdateCmd();
            cmd.execute(request, response);
            // 바로 글 목록 화면으로
            viewPage = "boardList.bbs";
        }

        /*
         * 삭제하기 관련 패스워드 체크
         * */
        if (cmdURI.equals("/boardPwdCheckToDelete.bbs")) {
            cmd = new BoardPwdCheckCmd();
            boolean isPasswordCorrect = cmd.execute(request, response);
            if (isPasswordCorrect) {
                // 삭제 처리 하고
                cmd = new BoardDeleteCmd();
                cmd.execute(request, response);
                // boardList.bbs호출
                viewPage = "boardList.bbs";
            } else {
                viewPage = "view/boardPwdCheckFalse.jsp";
            }
        }


        //댓글관련

        if (cmdURI.equals("/commentInsert.bbs")) {
            // 댓글 submit bizlogic
            cmd = new BoardCommentInsertCmd();
            cmd.execute(request, response);
            // 댓글 입력 후 어떻게 view를 처리해줄까
            // 댓글 단 게시글의 번호 그대로 boardRead.bbs 해주자
            String boardId = request.getParameter("boardId");
            viewPage = "boardRead.bbs?id="+boardId;
        }

            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);


    }
}