package ch02.sec01;

public class Literal {

	public static void main(String[] args) {
		// 리터럴 - > 실제 값 총칭(저장되는 값의 유형)
		// 정수/실수/문자(char)/문자열(String)/논리
		int scroe = 95;
		double average = 88.5;
		char fanilyName = '김';  //문자 리터럴은 ''내부에 표현
		String name = "양정원";  //문자열 리터럴은 "" 내부에 표현
		boolean result = true; //논리 리터럴 boolean

		System.out.println(scroe);
		System.out.println(average);
		System.out.println(fanilyName);
		System.out.println(name);
		System.out.println(result);
		// 변수 사용 범위 : {} 선언된 가장 가까운 블럭내에서만 사용가능
	}
}
