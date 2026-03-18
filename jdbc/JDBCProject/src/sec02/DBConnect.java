package sec02;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public Connection getConnection() {//메소드가 호출되면 db연결 진행하고 연결된 객체 반환
		Connection con = null;
		// 예외처리 필요
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##SQL_USER";
			String pwd = "1234";
			
			con = DriverManager.getConnection(url,user,pwd);// db연결이 성공되면 객체가 반환 실패하면 null값이 반환
			
			if(con !=null)
				System.out.println("db연결성공");
			else
				System.out.println("db연결실패");
			
			return con;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
