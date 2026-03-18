package sec05;

import java.util.ArrayList;

public interface IStudentDAO {
// 학생관리 crud 인터페이스: 기능 표준 제시
	// 학생등록
	public void insertStudent(StudentDTO dto);
	
	// 전체 학생 정보 조회
	public ArrayList<StudentDTO> getAllStudent();
	
	// 상세 학생 정보 조회
	public StudentDTO detailStudent(String stdNo);
	
	// 학생 정보 삭제
	public void deleteStudent(String stdNo);
	
	// 학생 정보 수정
	public void updateStudent(StudentDTO dto);
	
	// 과별 학생 검색: 여러 학생 반환
	public ArrayList<StudentDTO> searchStudent(String dptNo);
	
	// 과별 학생 검색: 여러 학생 반환
	public ArrayList<StudentDTO> searchStudentName(String dptName);
	
	// 학생 번호(id) 존재여부 반환
	public boolean searchStudentNo(String stdNo);
}
