package ch02.sec02;

public class DataType {

	public static void main(String[] args) {
		// 기본타입 8개(정수/실수/문자/논리)
		// 정수타입 : byte/short/int/long
		// 실수타입 : float/double
		// 문자타입 : char
		// 논리타입 : boolean
		// 데이터 타입에 따라 사용하는 메모리 크기
		// 메모리 크기 최소단위 : bit(0,1 저장)
		// bit가 8개가 모여 byte단위 : 8개의 0또는 1을 저장
		// byte:데이터를 처리하는 최소단위 - 256개의 데이터 표현
		
		///////////////////문자형(char)///////////////////
		char a = 'A';
		char b = '\u0041';
		char c = 65;

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);

		///////////////////int(4byte)///////////////////
		int num = 10; // 리터럴에 정수 표현 기호가 필요없음
		/// long (8byte)
		long num2 = 10; //int타입의 리터럴을 long형 변수에 저장(작은타입이 큰타입으로 저장)
		//long longValue = 100010001000100; 리터럴 int형이라 표현범위 벗어남 오류
		long longValue = 100010001000100l;// L,l을 표현해서 long형 리터럴 명시
		System.out.println("==========================");
		byte byteValue = 10;		//1byte정수 = 110 = 10
		short shotValue = 1000; 	//2byte정수
		int intValue = 2100100100; 	//4byte정수
		long longVal = 100010001000100l;
		
		System.out.println(byteValue);

		//////////////////실수(double)//////////////////
		float average = 88.5f;
		double doubleValue = 0.1234568790123456789;//더 정밀함
		float floatValue = 0.1234568790123456789f;

		System.out.println("==========================");
		System.out.println(doubleValue);
		System.out.println(floatValue);

		///////////////////지수 표현(e)///////////////////
		int var1 = 300000;
		double var2 = 3e6;  //정수부분을 표현하고 e 6을 표현 : 0을 6개 추가 300000
		float var3 = 3e6f;  //f는 float의 f
		double var4 = 3e-6; //소수점이하 6자리 의미
		double var5 = 3e-2; //소수점이하 2자리 의미

		System.out.println("==========================");
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var3);
		System.out.println(var4); //println문의 포맷변경x = 소수점이하가 길면 지수표현
		System.out.println(var5);
		
		// 참조형(기본타입만으로 표현의 한계 추가한 타입(객체) : String
		String name = "양정원"; //기본타입처럼 사용되지만 참조타입
		//name은 문자열형 변수 | "양정원"은 문자열형 리터럴
		
		System.out.println("==========================");
		System.out.println(name);
	}
}
