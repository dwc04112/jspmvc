package kr.ac.daegu.jspmvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
    public MemberDTO getLoginData(String id) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        pstmt = conn.prepareStatement("select * from member where id = ?");
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();

        MemberDTO memberDTO = new MemberDTO();
        if(rs.next()){
            memberDTO.setId(rs.getString("id"));
            memberDTO.setPassword(rs.getString("password"));
            memberDTO.setSalt(rs.getString("salt"));
        }

        return memberDTO;
    }

    public int getMemberNewMid()  throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // newId를 가져오는 쿼리
        pstmt = conn.prepareStatement("select max(mId) + 1 AS newMid from Member");
        rs = pstmt.executeQuery();

        int newMid = 0;
        if(rs.next()){
            newMid = rs.getInt("newMid");
            return newMid;
        }

        // 예외 발생
        throw new SQLException("글 컨텐츠를 새로 입력하기 위한 아이디값 받아오기를 실패하였습니다.");
    }

    public void insertJoinMember(int newMid, String id, String password, String salt)  throws ClassNotFoundException, SQLException {
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
}