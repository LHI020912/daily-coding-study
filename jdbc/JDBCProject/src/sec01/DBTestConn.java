package sec01;

import java.sql.Connection; // dbms와 session을 연결하고 관리하는 객체
import java.sql.DriverManager; //dbms와 통신담당

public class DBTestConn {

	public static void main(String[] args) {
		// 접속(Connection)객체선언
		Connection con = null;
		// DB연결은 외부 자원을 사용 - 
		// 예외처리 필요
		try {
			// 1. JDBC DRIVER로드 : 런타임시 로드(자동로그: 생략가능)
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 객체 인스턴스 생성 - db위치(주소:url), 계정명, 비밀번호
			// DriverManager를 통해 접속 시도 DriverManager.getConnection(url,user,pwd)
			// URL: jdbc종류: @ip주소:포트:sid:
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##SQL_USER";
			String pwd = "1234";
			
			con = DriverManager.getConnection(url,user,pwd);// db연결이 성공되면 객체가 반환 실패하면 null값이 반환
			
			if(con !=null)
				System.out.println("db연결성공");
			else
				System.out.println("db연결실패");
			
		} catch(Exception e) {
			e.printStackTrace(); // 오류발생할때까지 과정을 추적해서 오류내용 출력
		}
	}

}
