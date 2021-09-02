
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

    // 페이지당 몇개의 글목록을 보여줄것인지?
    public static final int DEFAULT_PAGE_PER_ROW = 10;

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // db에 접근해서 데이터 가져오는 인스턴스
        BoardDAO dao = new BoardDAO();
        // dao 기능 호출해서 가져온 db 데이터를 저장하는 컬렉션
        ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

        // 한페이지당 보여줄 글의 갯수 (default 3)
        String pagePerRowStr = request.getParameter("pagePerRow");
        int pagePerRow = DEFAULT_PAGE_PER_ROW;
        if(pagePerRowStr != null){
            pagePerRow = Integer.parseInt(pagePerRowStr);
        }


        // pageNum의 요청을 받는다.
        String page = request.getParameter("page");

        int pageNum;
        if(page == null){
            pageNum = 1;
        } else {
            pageNum = Integer.parseInt(request.getParameter("page"));
        }

        try {
            //dao.CountCommentNum(pageNum, pagePerRow);
            list = dao.getBoardList(pageNum, pagePerRow);
            /*
             * 페이지 번호가 몇번까지 나올 수 있는가?
             * */
            // (board테이블 전체 글의 갯수, row갯수)
            int totalRowCount = dao.getBoardTotalRowCount();
            // 전체 페이지 수 산출
            int totalPageCount = getTotalPageCount(pagePerRow, totalRowCount);

            /*
             * 가져온 db 데이터 리스트를 어떻게 jsp로 보여줄것인가?
             * */
            request.setAttribute("boardRowList", list);
            request.setAttribute("pagePerRow", pagePerRow);
            request.setAttribute("totalPageCount", totalPageCount);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /*
    *   전체 글의 개수 = N (totalRowCount)
        1페이지당 보여줄 글의 개수 = R (pagePerRow)
        전체 페이지 수 = T (return)
        If(N%R == 0){
          T = N/R
        } else {
          T = N/R + 1
        }
        N=8, R=3
        8 % 3 == 2이므로
        T = 8 / 3 + 1, T = 3
        N=51, R=3
        51 % 3 == 0이므로
        T = 51 / 3, T= 17
    * */
    private int getTotalPageCount(int pagePerRow, int totalRowCount) {
        if (totalRowCount % pagePerRow == 0) {
            return totalRowCount / pagePerRow;
        } else {
            return (totalRowCount / pagePerRow) + 1;
        }
    }
}