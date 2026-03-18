// 한개 소스에 여러 클래스가 있을 때는 public 접근권한자는 한개의 클래스만 가능
class A {
	
}

class B {
	
}

public class Test {

	public static void main(String[] args) {
		// 프로그램 시작점(실행을 위해 반드시 필요)
		// static 이므로 객체 없이 바로 사용 가능
		// 실행할 때 런타임(자바)이 main을 호출하는 것부터 시작
		// 출력을 위한 코드 | 문장블럭 마지막엔 ; 세미콜론
		System.out.println("test 입니다.");
		System.out.println("test"
				+"");
		// 한줄 주석
		/*
		 * 여러줄 주석
		 */
		{
			//영역 범위 표시(클래스, 메소드, 조건문, 반복문 등)
		}
	}
}