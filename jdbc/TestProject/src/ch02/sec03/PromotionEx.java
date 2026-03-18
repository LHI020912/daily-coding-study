package ch02.sec03;

public class PromotionEx {

	public static void main(String[] args) {
		// *자동형변환 Promotion* 작은타입의 data가 큰타입 변수로 저장되는 경우
		// java가 실행중에 자동 진행
		byte byteValue = 10;
		int intValue = byteValue; //서로 다른 타입이지만 byte가 int보다 작은타입 = 자동형변환
		System.out.println(intValue);
		
		char charValue='가';
		intValue = charValue; //char 2byte, int 4byte 자동형변환
		System.out.println("'가'의 유니코드: "+intValue);
		
		intValue = 500;
		long longValue = intValue;
		System.out.println(longValue);

		intValue = 500;
		double doubleValue = intValue;
		System.out.println(doubleValue);
		
		// intValue = doubleValue; | Type mismatch: cannot convert from double to int
	}

}
