package sec05;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static IStudentDAO idao = new StudentDAO();
	private static StudentDAO dao = new StudentDAO();
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<StudentDTO> stdList = null;
	
	public static void Info() {
		// 1. 학생 정보 입력 - 사용자로부터 입력은 ReadWrite class 활용
		idao.insertStudent(ReadWrite.getStdInfo(sc));
		StudentDTO dto = null;
		
		while(true) {
			dto = ReadWrite.getStdInfo(sc);
			
			if(idao.searchStudentNo(dto.getStdNo())) {
				System.out.println("이미 존재하는 학번 입니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}
		idao.insertStudent(dto);
	};
	
	public static void AllInfo() {
		// 2. 전체 학생 정보 조회
		stdList = dao.getAllStudent();
		ReadWrite.writeStdInfo(stdList);
	};
	
	public static void DetailInfo() {
		// 3. 한명 학생 정보 조회
		System.out.println("학번을 입력하세요");
		String stdNo = sc.nextLine();
		
		StudentDTO dto = dao.detailStudent(stdNo);
		
		if(dto != null)
			ReadWrite.writeStdInfo(dto);
	};
	
	public static void DeleteStd() {
		// 4.한명 학생 정보 삭제
		System.out.println("학번을 입력하세요");
		String stdNo = sc.nextLine();
		idao.deleteStudent(stdNo);
	};
	
	public static void UpdateStd() {
		// 5.한명 학생 정보 갱신
		System.out.println("학번을 입력하세요");
		String stdNo = sc.nextLine();
		StudentDTO dto = dao.detailStudent(stdNo);
		
		if(dto != null) {
			ReadWrite.writeStdInfo(dto);
			dao.updateStudent(ReadWrite.updateStdInfo(sc, stdNo));
		}
	};
	
	public static void DptNameStd() {
		// 6. 학과명 검색
		System.out.println("학과명을 입력하세요");
		String dptName = sc.nextLine();
		
		stdList = idao.searchStudentName(dptName);
		
		if(stdList.size() != 0);
			ReadWrite.writeStdInfo(stdList);
	};

}
