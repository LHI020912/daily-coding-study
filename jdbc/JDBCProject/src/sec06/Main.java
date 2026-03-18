package sec06;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int m;
		System.out.println("******* 도서 관리 프로그램 *******");
		
		while(true) {
			System.out.println("******* 다음 메뉴에서 선택 *******");
			System.out.println("1. 도서 등록");
			System.out.println("2. 도서 정보 조회");
			System.out.println("3. 도서 정보 수정");
			System.out.println("4. 도서 정보 삭제");
			System.out.println("5. 종료");
			System.out.println("-----------------------------");
			System.out.print("메뉴 번호 입력: ");
			m = Integer.parseInt(sc.nextLine());
			
			if(m == 5) break;
			
			switch(m) {
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: break;
			default: System.out.println("숫자를 잘 못 입력하셨습니다.");break;
			}
		}
		System.out.println("종료합니다.");
	}

}
