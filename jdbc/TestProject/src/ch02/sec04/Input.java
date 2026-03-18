package ch02.sec04;

import java.util.Scanner; //외부 클래스 - 인스턴스 생성해서 사용해야함

public class Input {
	public static void main(String[] args) {
	// 참조형 변수 sc : 참조형 변수(인스턴스) 선언시 활용 객체 타입 선언
	Scanner sc = new Scanner(System.in); //인스턴스 new 생성자
	// 키보드를 통해 입력된 바이트코드를 메소드에 따라 형변환
	int num1, num2;
	System.out.print("첫 번째 숫자 입력: ");
	num1 = sc.nextInt();
	System.out.print("두 번째 숫자 입력: ");
	num2 = sc.nextInt();

	System.out.println("두 수의 합은: "+(num1+num2));
	System.out.println("두 수의 곱은: "+(num1*num2));
	
	///////////////문자열 입력 : sc.next();///////////////
	String grade;
	System.out.print("문자열을 입력하세요: ");
	grade = sc.next();
	System.out.println(grade);

	char grd;
	System.out.print("문자를 입력하세요: ");
	//grd = sc.next();//한 문자든 여러 문자든 문자열 타입으로 반환->char에 저장 불가능
	//문자열로 입력반은 후에 한 문자만 추출 : String.charAt(0);
	grd= sc.next().charAt(1);
	System.out.println(grd);
	}
}