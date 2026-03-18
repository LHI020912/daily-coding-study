package sec05;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO implements IStudentDAO{
	// 인터페이스 구현 클래스
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnect dbCon = null;
	StudentDTO std = null;
	ArrayList<StudentDTO> stdList = null;
	
	// 생성자에 DB 연결
	public StudentDAO() {
		con = DBConnect.getConnection();
	}
	
	@Override
	public void insertStudent(StudentDTO dto) {
		// 학생 정보 입력
		try {
			String sql = "insert into student values(?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			// 바인딩 변수 설정
			pstmt.setString(1, dto.getStdNo());
			pstmt.setString(2, dto.getStdName());
			pstmt.setInt(3, dto.getStdYear());
			pstmt.setString(4, dto.getStdAddress());
			pstmt.setDate(5, new java.sql.Date(dto.getStdBirth().getTime()));
			pstmt.setString(6, dto.getDptNo());
			int result = pstmt.executeUpdate();
			
			if(result > 0)
				System.out.println("학생 등록 성공 !");
			
			
		}catch(SQLException e) {
			System.out.println("학생 등록 실패");
			e.printStackTrace();
			
		}finally {
			DBConnect.close(pstmt);
		}
	}

	@Override
	public ArrayList<StudentDTO> getAllStudent() {
		// 전체 학생 정보 조회
		stdList = new ArrayList<StudentDTO>();
		
		try {
			String sql = "select * from student order by stdNo";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();//결과받아오기
			
			while(rs.next()) { // ArrayList에 하나씩 집어 넣어줄거임
				String stdNo = rs.getString(1); // 0번으로 시작하는 이유는 row num도 가져오기때문임
				String stdName = rs.getString(2);
				int stdYear = rs.getInt(3);
				String stdAddress = rs.getString(4);
				Date stdBrith = rs.getDate(5);
				String dptNo = rs.getString(6);
				
				std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBrith, dptNo);
				stdList.add(std); // ArrayList에 add를 이용해 추가
			}
			
		}catch(SQLException e) {
			System.out.println("오류 발생");
			e.printStackTrace();
		
		}finally {
			DBConnect.close(pstmt,rs);
		}
		return stdList;
	}

	@Override
	public StudentDTO detailStudent(String stdNo) {
		// 한명 학생 정보 조회
		try {
			String sql = "select * from student where stdNo = ?"; // ?를 이용해서 바인딩 진행
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,stdNo);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				stdNo = rs.getString(1);
				String stdName = rs.getString(2);
				int stdYear = rs.getInt(3);
				String stdAddress = rs.getString(4);
				Date stdBrith = rs.getDate(5);
				String dptNo = rs.getString(6);
				
				std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBrith, dptNo);
			
			}else {
				System.out.println("학번에 해당하는 학생 정보가 없습니다.");
			}
			
		}catch(Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt,rs);
		}
		
		return std;
	}

	@Override
	public void deleteStudent(String stdNo) {
		// 학생 정보 삭제
		String sql = "delete from student where stdNo=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stdNo);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0)
				System.out.println("학번 "+ stdNo +"의 학생 정보 삭제 성공");
			else
				System.out.println("학번 "+ stdNo +"의 학생 정보가 없습니다.");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt);
		}
		
	}

	@Override
	public void updateStudent(StudentDTO dto) {
		// 모든 컬럼 업데이트 처리
		String sql = "update student set stdName =?, stdYear =?"
				+ "stdAddress =?, stdBirth=?, dptNo=? where stdNo=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getStdName());
			pstmt.setInt(2, dto.getStdYear());
			pstmt.setString(3, dto.getStdAddress());
			pstmt.setDate(4, new java.sql.Date(dto.getStdBirth().getTime()));
			pstmt.setString(5, dto.getDptNo());
			
			int result = pstmt.executeUpdate();
			if(result > 0)
				System.out.println("학번 "+ dto.getStdNo() + "의 학생 정보 수정 완료");
			else
				System.out.println("학번 "+ dto.getStdNo() + "의 학생 정보가 없음");
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt);
		}
		
		
	}

	@Override
	public ArrayList<StudentDTO> searchStudent(String dptNo) {
		// 같은과 학생 검색
		stdList = new ArrayList<StudentDTO>();
		String sql = "select * from student where dptNo =?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dptNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					std = new StudentDTO(rs.getString(1),rs.getString(2),rs.getInt(3)
							,rs.getString(4),rs.getDate(5),rs.getString(6));
					stdList.add(std);
				}while(rs.next());
			}else {
				System.out.println(dptNo + "학과의 학생 정보는 없습니다.");
			}
			
		}catch(Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt,rs);
		}
		return stdList;
	}

	@Override
	public boolean searchStudentNo(String stdNo) {
		// 정보 입력 시 중복 학번 확인용
		String sql = "select * from student where stdNo =? ";
		boolean res = true;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stdNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 전달된 학번으로 검색된 레코드가 있다는 의미
				res = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt,rs);
		}
		return true;
	}

	@Override
	public ArrayList<StudentDTO> searchStudentName(String dptName) {
		// 같은과 학생 검색 (학과명으로 검색)
		stdList = new ArrayList<StudentDTO>();
		String sql = "select * from student where dptNo =(select dptNo from department where dptName=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dptName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					std = new StudentDTO(rs.getString(1),rs.getString(2),rs.getInt(3)
							,rs.getString(4),rs.getDate(5),rs.getString(6));
					stdList.add(std);
				}while(rs.next());
			}else {
				System.out.println(dptName + "학과의 학생 정보는 없습니다.");
			}
			
		}catch(Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt,rs);
		}
		return stdList;
	}
}