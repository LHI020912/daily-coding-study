package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	//connection pool 사용하기 위한 DataSource 인스턴스 생성
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> memberList = new ArrayList<MemberVO>();
	    String sql = "SELECT MEMID, MEMPWD, MEMNAME, MEMEMAIL, MEMJOINDATE FROM member2";

	    try (Connection conn = dataFactory.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            // SQL 문에서 나열한 순서대로 하나씩 꺼냅니다.
	            String id = rs.getString("MEMID");       // 1번째
	            String pwd = rs.getString("MEMPWD");     // 2번째
	            String name = rs.getString("MEMNAME");   // 3번째
	            String email = rs.getString("MEMEMAIL"); // 4번째
	            Date joinDate = rs.getDate("MEMJOINDATE"); // 5번째 (날짜 타입 확인!)

	            // VO 생성자 순서에 맞게 넣어줍니다.
	            MemberVO dto = new MemberVO(id, pwd, name, email, joinDate);
	            memberList.add(dto);
	        }
		}catch(Exception e) {
			System.out.println("회원 정보 조회 실패");
			e.printStackTrace();
		}
		return memberList;
	}
	
	// 회원 추가
	public void addMember(MemberVO m) {
		try {
			conn = dataFactory.getConnection();
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "INSERT INTO member2 (memid, mempwd, memname, mememail)" + " VALUES(?, ? ,? ,?)";			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	// 회원 단건 조회
	public MemberVO findMember(String _id) {
		MemberVO memInfo = null;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from  member2 where memid=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _id);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("memid");
				String pwd = rs.getString("mempwd");
				String name = rs.getString("memname");
				String email = rs.getString("mememail");
				Date joinDate = rs.getDate("memjoinDate");
				memInfo = new MemberVO(id, pwd, name, email, joinDate);
			}
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}
	
	// 회원 수정
	public void modMember(MemberVO memberVO) {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		try {
			String query = "update member2 set mempwd=?, memname=?, mememail=? where memid=?";
			conn=dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();			
		}catch(Exception e) {
			e.printStackTrace();
		}			
	}
	
	// 회원 삭제
	public void delMember(String _id) {
		try {
		String query = "delete from member2 where memid=?";
		conn = dataFactory.getConnection();
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1,_id);
		pstmt.executeUpdate();
		pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}