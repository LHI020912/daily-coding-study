package sec01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
	// 회원가입
	public int join(MemberVO vo) {
		int result = 0;
		try {
			Connection con = DBConnect.getConnection();
			PreparedStatement pstmt = null;
			
			String sql = "insert into member(memid, mempwd, memname, mememail) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getMemId());
			pstmt.setString(2, vo.getMemPwd());
			pstmt.setString(3, vo.getMemName());
			pstmt.setString(4, vo.getMemEmail());
			
			result = pstmt.executeUpdate(); // 성공 시 1 반환
			
			DBConnect.close(con, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 로그인 확인
	public MemberVO login(String id, String pwd) {
		MemberVO vo=null;
		try {
			Connection con = DBConnect.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select * from member where memid=? and mempwd=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemberVO();
				vo.setMemId(rs.getString("memId"));
				vo.setMemPwd(rs.getString("memPwd"));
			}
			DBConnect.close(con, pstmt, rs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	// 회원 조회
	public ArrayList<MemberVO> listMembers() {
		ArrayList<MemberVO> list = new ArrayList<>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBConnect.getConnection();
			String sql = "select * from member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMemId(rs.getString("memId"));
				vo.setMemPwd(rs.getString("memPwd"));
				vo.setMemName(rs.getString("memName"));
				vo.setMemEmail(rs.getString("memEmail"));
				list.add(vo);
			}
			DBConnect.close(con, pstmt, rs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
