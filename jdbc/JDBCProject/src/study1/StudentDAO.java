package study1;

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
		// dbCon = new DBConnect(); // 인스턴스 하나 연결
		// con = dbCon.getConnection(); // 메소드 이용하여 연결
		con = DBConnect.getConnection();
		// DBConnect 생성자에 static 추가하면 이렇게 연결 가능
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
			// pstmt.setDate(5, dto.getStdBirth()); // dto.getStdBirth() util.date반환, pstmt.setDate는 sql.date반환
			pstmt.setDate(5, new java.sql.Date(dto.getStdBirth().getTime()));
			// long 타입
			// pstmt.setString(5, dto.getStdBirth());
			pstmt.setString(6, dto.getDptNo());
			// Query 실행 (영향을 받은 행의 수 받아와 변수에 저장)
			int result = pstmt.executeUpdate();
			
			if(result > 0)
				System.out.println("학생 등록 성공 !");
			
		}catch(SQLException e) {
			System.out.println("학생 등록 실패");
			e.printStackTrace(); // 개발 진행중이니까 이걸 이용해서 어디서 문제가 났는 지 확인
			
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
			pstmt.setString(1,stdNo); // 매개변수로 받고 있어서 넘겨받아 바인딩 완성
			
			// 쿼리실행 후 결과 반환
			rs = pstmt.executeQuery(); // sql 전달하면 오류 발생(바인딩 되지 않은 문자가 있다는)
			
			// 받은 결과를 dto에 추가해서 반환(기본키 기준으로 검색 진행 = 반환결과 레코드는 0이거나 1)
			// 방법 1. while(rs.next()) {}
			if(rs.next()) {
				stdNo = rs.getString(1); // 매개변수로 받은 stdNo이 컬럼의 인덱스 안에 존재하는데 컬럼 값이 동일해야함. 그대로 받기
				String stdName = rs.getString(2);
				int stdYear = rs.getInt(3);
				String stdAddress = rs.getString(4);
				Date stdBrith = rs.getDate(5);
				String dptNo = rs.getString(6);
				
				std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBrith, dptNo);
			
			}else {
				System.out.println("학번에 해당하는 학생 정보가 없습니다.");
				// resultSet은 반환이 된 상태
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
			
		} catch(Exception e) {// 구조적으로 잘 못이 있을 때 들어옴
			e.printStackTrace(); // 개발중이라 쓴거고. 웹이면 에러페이지로 넘기는 작업해
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
			rs = pstmt.executeQuery(); // select 구문 = resultSet
			
			/*
			rs.next() = 내려가기만하지 다시 올라가진 않음, 결과만 안 나오는 거지 rs는 계속 답을 내보냄
			
			 
			if + while 동시사용 하지마. 할거면
			if + do while 사용해.
			
			if(rs.next()) {
				while(rs.next()) {
					std = new StudentDTO(rs.getString(1),rs.getString(2),rs.getInt(3)
							,rs.getString(4),rs.getDate(5),rs.getString(6));
					stdList.add(std);
				}
			}else {
				// 아무것도 나오지 않음을 대비하지 않아서 while문을 사용했었음
				// 이번엔 여러개가 검색됐는데 값이 나오지 않을 수 있어서 if+while 사용함
				// 근데 이렇게 사용하면 문제나 오류는 없지만
				// ex. 제목레코드가 있다면 제목을 가리킨 2개의 레코드(튜플)이 있다면 아래로 진행
				// 해당 값을 넣어주고 진행해야하는데 걍 확인만하고 다음을 진행하는 상황이 생겨버림
				System.out.println(dptNo + "학과의 학생 정보는 없습니다.");
			}
			
			물론 여기서 아니라 main에서 null 값 뭐 어쩌고 가능함 근데 우린 여기서 일단 해볼거임
			*/
			
			if(rs.next()) {
				do {
					// 이것보다 위에 코드가 가독성이 더 좋음
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
	// 인터페이스 구현 클래스
	
}
