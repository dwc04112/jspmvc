package kr.ac.daegu.jspmvc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

// DatabaseAccessObject : 이 객체가 db에 접속해서 쿼리를 날리고 결과를 리턴해주는 책임
public class BoardDAO {
    private static final String DB_URL  = "jdbc:mariadb://localhost:3306/dgd";
    private static final String DB_USER = "root";
    private static final String DB_PW   = "0000";

    public static boolean getConnection() throws SQLException, ClassNotFoundException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        return true;
    }

    public ArrayList<BoardDTO> getBoardList(int pageNum, int pagePerRow)
            throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 페이징 처리에 따라 rowNum의 시작과 끝값을 변수처리
        int startRowNum;
        int endRowNum = pageNum*pagePerRow;
        if(pageNum == 1){
            startRowNum = pageNum;
        } else {
            startRowNum = (pagePerRow*(pageNum-1))+1;
        }

        // 쿼리 준비 & db 쿼리
        pstmt = conn.prepareStatement("select *from(select board.*,row_number() over(ORDER By pid asc, porder asc) as rowNum from board order by pid asc, porder asc)tb  where tb.rowNum between "+startRowNum+" and "+endRowNum);
        rs = pstmt.executeQuery();

        // 글 목록을 반환할 ArrayList
        ArrayList<BoardDTO> boardRowList = new ArrayList<BoardDTO>();

        // db에서 데이터를 row단위로 가져와서
        // list에 넣는다.
        while(rs.next()) {
            int id = rs.getInt("id");
            String subject = rs.getString("subject");
            String author = rs.getString("author");
            String content = rs.getString("content");
            Date writeDate = rs.getDate("writeDate");
            Time writeTime = rs.getTime("writeTime");
            int readCount = rs.getInt("readCount");
            int commentCount = rs.getInt("commentCount");
            int depth = rs.getInt("depth");

            BoardDTO dto = new BoardDTO();
            dto.setId(id);
            dto.setSubject(subject);
            dto.setAuthor(author);
            dto.setContent(content);
            dto.setWriteDate(writeDate);
            dto.setWriteTime(writeTime);
            dto.setReadCount(readCount);
            dto.setCommentCount(commentCount);
            dto.setDepth(depth);

            boardRowList.add(dto);
        }

        // db로부터 데이터 잘 들어왔는지 확인 (log 찍어봄)
        for(BoardDTO dto: boardRowList){
            System.out.println(dto.toString());
        }

        return boardRowList;
    }

    public void CountCommentNum(/*int pageNum, int pagePerRow*/ int maxId) throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        /*
        int startRowNum;
        int endRowNum = pageNum*pagePerRow;
        if(pageNum == 1){
            startRowNum = pageNum;
        } else {
            startRowNum = (pagePerRow*(pageNum-1))+1;
        }

         */
        for(int i= 1; i<=maxId;i++) {
            pstmt = conn.prepareStatement("update board set commentCount=(select count(id) from comment where id=?) where id=?");
            pstmt.setInt(1, i);
            pstmt.setInt(2, i);
            pstmt.executeQuery();
        }
    }

    public int getBoardPorder(int pid, int depth,int porder)  throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        /*
        if (depth==1) {
            pstmt = conn.prepareStatement("select max(porder) + 1 AS newPorder from Board where board.pid=?");
            pstmt.setInt(1, pid);
            rs = pstmt.executeQuery();
        }else{
         }
         */
            pstmt = conn.prepareStatement("select porder+1 as newPorder from board where board.pid=? and board.porder =?");
            pstmt.setInt(1, pid);
            pstmt.setInt(2, porder);
            rs = pstmt.executeQuery();
            porder=porder+1;
            pstmt = conn.prepareStatement("update board set porder=porder+1 where porder >=? and pid=?");
            pstmt.setInt(1,porder);
            pstmt.setInt(2,pid);
            pstmt.executeUpdate();
        int newPorder = 0;
        if(rs.next()){
            newPorder = rs.getInt("newPorder");
            /*
            * id=2에서 글을 쓴다하면
            * id=3 pid=2가되고 pid가 2인 모든 order값중 max값에 +1    따라서 porder=1
            *
            * id2의 답글 (id=3)에서 답글을 쓴다하면
            * id=4 pid=2 그리고 id=3의 order값에 +1 을 하고 나머지 자기보다 크거나 같은order값은 모두 +1
            *
            * 원본글 id=2 에서 단 답글3개
            * id=3 porder=1 | id=4 porder=2 | id=5 porder=3
            * 여기서 id=3에서 다시 답글을 단다고 생각
            * porder+1 후 나머지 자기와 같거나 큰 porder+1
            *
            * 이렇게하려면 어떻게 해야할까??---
            * max(porder)+1 부분을 지우자
            * 우선 newPorder는 porder+1
            * */
            if(newPorder == 0){
            //여긴 답글이 달리는 부분이므로 porder는 무조건 1 이상이어야한다
                newPorder = 1;
                return newPorder;
            }else{
                return newPorder;
            }

        }
        // 예외 발생
        throw new SQLException("답글을 새로 입력하기 위한 오더값 받아오기를 실패하였습니다.");
    }



    public int getBoardNewId() throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // newId를 가져오는 쿼리
        pstmt = conn.prepareStatement("select max(id) + 1 AS newId from Board");
        rs = pstmt.executeQuery();

        int newId = 0;
        if(rs.next()){
            newId = rs.getInt("newId");
            return newId;
        }

        // 예외 발생
        throw new SQLException("글 컨텐츠를 새로 입력하기 위한 아이디값 받아오기를 실패하였습니다.");
    }

    public void insertBoardContent(int newId,
                                   String subject,
                                   String author,
                                   String content,
                                   String password,
                                   int pid,
                                   int depth, int porder) throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;

        // 쿼리 준비 & db 쿼리
        // insert into board values (1, 'testAuthor', 'testSubject', 'testContent', CURDATE(), CURTIME(), 0, 0)
        pstmt = conn.prepareStatement("insert into Board values (?, ?, ?, ?, CURDATE(), CURTIME(), 0, 0, ?, ?, ? , ?)");
        pstmt.setInt(1, newId);
        pstmt.setString(2, author);
        pstmt.setString(3, subject);
        pstmt.setString(4, content);
        pstmt.setString(5, password);
        pstmt.setInt(6,pid);
        pstmt.setInt(7,depth);
        pstmt.setInt(8,porder);
        pstmt.executeUpdate();

    }

    public BoardDTO getBoardData(int id) throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // 쿼리 실행시키고
        pstmt = conn.prepareStatement("select * from Board where id = ?");
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        // 반환 데이터를 리턴.
        BoardDTO data = new BoardDTO();
        if(rs.next()){
//            int id = rs.getInt("id");

            String subject = rs.getString("subject");
            String author = rs.getString("author");
            String content = rs.getString("content");
            Date writeDate = rs.getDate("writeDate");
            Time writeTime = rs.getTime("writeTime");
            int readCount = rs.getInt("readCount");
            int commentCount = rs.getInt("commentCount");
            String password = rs.getString("password");
            int pid = rs.getInt("pid");
            int depth = rs.getInt("depth");
            int porder = rs.getInt("porder");


            data.setId(id);

            data.setSubject(subject);
            data.setAuthor(author);
            data.setContent(content);
            data.setWriteDate(writeDate);
            data.setWriteTime(writeTime);
            data.setReadCount(readCount);
            data.setCommentCount(commentCount);
            data.setPassword(password);
            data.setPid(pid);
            data.setDepth(depth);
            data.setPorder(porder);

        }
        return data;
    }

    public void boardRowPlusReadCount(int rowId, int howMuch) throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;

        // 해당 아이디의 row에서 readCount를 +1 해주는 쿼리 실행
        pstmt = conn.prepareStatement("update Board set readCount=readCount + ? where id = ?");
        pstmt.setInt(1, howMuch);
        pstmt.setInt(2, rowId);
        pstmt.executeUpdate();
    }



    public void updateBoardContent(int id,
                                   String subject,
                                   String content) throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        // 해당 아이디의 row에서 subject와 content를 업데이트
        pstmt = conn.prepareStatement("update Board set subject=?, content=? where id = ?");
        pstmt.setString(1, subject);
        pstmt.setString(2, content);
        pstmt.setInt(3, id);
        pstmt.executeUpdate();

    }

    public void deleteBoardData(int id) throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        // 해당 아이디의 row를 삭제
        pstmt = conn.prepareStatement("delete from board where id = ?");
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public int getBoardTotalRowCount() throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // board테이블 전체 row 갯수
        pstmt = conn.prepareStatement("select count(*) as count from Board");
        rs = pstmt.executeQuery();

        if(rs.next()){
            return rs.getInt("count");
        }

        throw new SQLException("Board테이블의 전체 갯수를 가지고 올 수 없습니다.");
    }

    public void insertCommnet(int newCommentId,
                              int boardId,
                              String commentAuthor,
                              String commentContent) throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;

        // 새로운 댓글을 insert
        pstmt = conn.prepareStatement("insert into comment values (?, ?, ?, ?, CURDATE(), CURTIME())");
        pstmt.setInt(1, newCommentId);
        pstmt.setInt(2, boardId);
        pstmt.setString(3, commentAuthor);
        pstmt.setString(4, commentContent);
        pstmt.executeUpdate();
    }

    public int getCommentNewId()  throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // newId를 가져오는 쿼리
        pstmt = conn.prepareStatement("select max(Cid) + 1 AS newId from comment");
        rs = pstmt.executeQuery();

        int newId = 0;
        if(rs.next()){
            newId = rs.getInt("newId");
            return newId;
        }

        // 예외 발생
        throw new SQLException("글 컨텐츠를 새로 입력하기 위한 아이디값 받아오기를 실패하였습니다.");
    }

    public ArrayList<CommentDTO> getBoardCommentList()
            throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        pstmt = conn.prepareStatement("select * from comment");
        rs = pstmt.executeQuery();


        ArrayList<CommentDTO> RowCommentList = new ArrayList<CommentDTO>();
        // db에서 데이터를 row단위로 가져와서
        // list에 넣는다.

        while (rs.next()) {
            int Cid=rs.getInt("Cid");
            int id=rs.getInt("id");
            String author = rs.getString("author");
            String content = rs.getString("content");
            Date writeDate = rs.getDate("writeDate");
            Time writeTime = rs.getTime("writeTime");


            CommentDTO dto = new CommentDTO();
            dto.setCid(Cid);
            dto.setId(id);
            dto.setAuthor(author);
            dto.setContent(content);
            dto.setWriteDate(writeDate);
            dto.setWriteTime(writeTime);


            RowCommentList.add(dto);
        }
        // db로부터 데이터 잘 들어왔는지 확인 (log 찍어봄)
        for (CommentDTO dto : RowCommentList) {
            System.out.println(dto.toString());
        }

        return RowCommentList;
    }

    public int getBoardDepth() throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // newId를 가져오는 쿼리
        pstmt = conn.prepareStatement("select max(depth) + 1  from Board");
        rs = pstmt.executeQuery();

        int depth = 0;
        if(rs.next()){
            depth = rs.getInt("depth");
            return depth;
        }

        // 예외 발생
        throw new SQLException("글 컨텐츠를 새로 입력하기 위한 아이디값 받아오기를 실패하였습니다.");
    }




















/*
    public int CountComment(int boardId)  throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        pstmt = conn.prepareStatement("select count(comment.cid) AS NumCount from comment where comment.id =?");
        rs = pstmt.executeQuery();
        pstmt.setInt(1, boardId);
        pstmt.executeUpdate();


 */
}