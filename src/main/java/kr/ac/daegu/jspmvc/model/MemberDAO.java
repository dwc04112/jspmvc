package kr.ac.daegu.jspmvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
    public MemberDTO getLoginData(String id) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        pstmt = conn.prepareStatement("select * from member where id = ?");
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();

        MemberDTO memberDTO = new MemberDTO();
        if (rs.next()) {
            memberDTO.setId(rs.getString("id"));
            memberDTO.setPassword(rs.getString("password"));
            memberDTO.setSalt(rs.getString("salt"));
        }

        return memberDTO;
    }

    public int getMemberNewMid() throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // newId를 가져오는 쿼리
        pstmt = conn.prepareStatement("select max(mId) + 1 AS newMid from Member");
        rs = pstmt.executeQuery();

        int newMid = 0;
        if (rs.next()) {
            newMid = rs.getInt("newMid");
            return newMid;
        }

        // 예외 발생
        throw new SQLException("글 컨텐츠를 새로 입력하기 위한 아이디값 받아오기를 실패하였습니다.");
    }

    public void insertJoinMember(int newMid, String id, String password, String salt) throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;

        // 쿼리 준비 & db 쿼리

        pstmt = conn.prepareStatement("insert into member values (?,?,?,?)");
        pstmt.setInt(1, newMid);
        pstmt.setString(2, id);
        pstmt.setString(3, password);
        pstmt.setString(4, salt);
        pstmt.executeUpdate();
    }

    public ArrayList<MemberDTO> getMemberIdList() throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        pstmt = conn.prepareStatement("select id from `member` m ");
        rs = pstmt.executeQuery();

        // 글 목록을 반환할 ArrayList
        ArrayList<MemberDTO> MemberIdList = new ArrayList<MemberDTO>();

        // db에서 데이터를 row단위로 가져와서
        // list에 넣는다.
        while (rs.next()) {
            String id = rs.getString("id");

            MemberDTO memDTO = new MemberDTO();
            memDTO.setId(id);

            MemberIdList.add(memDTO);


        }
        // db로부터 데이터 잘 들어왔는지 확인 (log 찍어봄)
        for (MemberDTO memDTO : MemberIdList) {
            System.out.println(memDTO.toString());
        }
        return MemberIdList;
    }

    public int compareId(String id) throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //EndUser 에게 입력받은 id값을 DB에 넣어 중복값이 있는지 확인하는 쿼리
        pstmt = conn.prepareStatement("select count(id) as countId from `member` where id=?");
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();
        int countId;
        // countId는 위 쿼리에 Id값을 넣으면 중복되는 값을 카운트한다. 없으면 0

        if (rs.next()) {
            countId = rs.getInt("countId");
            System.out.println("DB에서 출력 :: 아이디 0?1? = " + countId);
            if(countId == 0){
                return countId;
            }else if(countId == 1){
                return countId;
            }
        }
        throw new SQLException("같은 ID값 찾기 과정을 실패했습니다.");
    }
}