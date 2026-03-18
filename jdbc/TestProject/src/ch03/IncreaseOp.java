package ch03;

public class IncreaseOp {

	public static void main(String[] args) {
		// 증가감 연산자
		int x = 10;
		int y = 10;
		int z;
		
		System.out.println("------------------");
		x++;
		++x;
		System.out.println(x); // = 12
		
		System.out.println("------------------");
		y--;
		--y;
		System.out.println(y); // = 8

		//대입연산자와 같이 사용
		System.out.println("------------------");
		z= x++; //z에 대입 x 증가 
		System.out.println("z= "+z); //12
		System.out.println("x= "+x); //13
		
		System.out.println("------------------");
		z= ++x; //x먼저 증가 z에 대입
		System.out.println("z= "+z); //14
		System.out.println("x= "+x); //14

		//다른 연산자와 같이 사용(+ 연산과 같이 사용)
		System.out.println("------------------");
		z = ++x + y++;
		System.out.println("y= "+y); //9
		System.out.println("x= "+x); //15
		System.out.println("z= "+z); //23
	}

}
