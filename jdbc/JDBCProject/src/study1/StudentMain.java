package study1;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMain {

	public static void main(String[] args) {
		// 클래스 테스트용 (controller)
		IStudentDAO idao = new StudentDAO();
		StudentDAO dao = new StudentDAO(); // 인터페이스를 가운데 놓고 이용하자는 약속
		Scanner sc = new Scanner(System.in);
		
		ArrayList<StudentDTO> stdList = null;
		
		try {
			/* 
			
			// 1. 학생 정보 입력 - 사용자로부터 입력은 ReadWrite class 활용
			idao.insertStudent(ReadWrite.getStdInfo(sc));
			
			// 2. 전체 학생 정보 조회
			stdList = dao.getAllStudent();
			ReadWrite.writeStdInfo(stdList);
			// ReadWrite.writeStdInfo(dao.getAllStudent());
			// 개발자의 습관에 따라 직접호출, 변수에 넣어 호출 차이가 있다.
			
			// 3. 한명 학생 정보 조회
			System.out.println("학번을 입력하세요");
			String stdNo = sc.nextLine();
			
			StudentDTO dto = dao.detailStudent(stdNo);
			
			if(dto != null)
				ReadWrite.writeStdInfo(dto);
			
			// 4.한명 학생 정보 삭제
			System.out.println("학번을 입력하세요");
			String stdNo = sc.nextLine();
			idao.deleteStudent(stdNo);
			
			// 5.한명 학생 정보 갱신
			// 수정할 학생 학번 입력받아 개별 학생 정보 출력해주고
			System.out.println("학번을 입력하세요");
			String stdNo = sc.nextLine();
			StudentDTO dto = dao.detailStudent(stdNo);
			
			if(dto != null) {
				ReadWrite.writeStdInfo(dto);
				dao.updateStudent(ReadWrite.getStdInfo(sc, stdNo));
				// 학번 수정 방지를 위해 ReadWrite에서 update 만들어 stdNo가져옴
			}
			
			// 6. 학과 번호 검색
			System.out.println("학과 번호를 입력하세요");
			String dptNo = sc.nextLine();
			
			// if(!stdList.isEmpty());
			if(stdList.size() != 0);{
				ReadWrite.writeStdInfo(stdList);
			}
			
			 */

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}