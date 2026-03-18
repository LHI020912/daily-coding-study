package sec05;

import java.util.Scanner;

public class StudentMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int m;
		try {
			
			while(true) {
				System.out.println("------- 메뉴 ---------");
				System.out.println("1. 학생 정보 입력");
				System.out.println("2. 전체 학생 조회");
				System.out.println("3. 한명 학생 조회");
				System.out.println("4. 한명 학생 삭제");
				System.out.println("5. 한명 학생 갱신");
				System.out.println("6. 학과명 검색");
				m = Integer.parseInt(sc.nextLine());
				
				if(m == 7) break;
				
				switch(m) {
				case 1: Menu.Info(); break;
				case 2: Menu.AllInfo(); break;
				case 3: Menu.DetailInfo(); break;
				case 4: Menu.DeleteStd(); break;
				case 5: Menu.UpdateStd(); break;
				case 6: Menu.DptNameStd(); break;
				default:System.out.println("숫자를 잘 못 입력하였습니다.");
				}
			}System.out.println("종료합니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}