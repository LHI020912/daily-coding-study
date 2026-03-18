package sec01;

import java.sql.Connection;
import java.sql.DriverManager;

public class ProJDBConn {

	public static void main(String[] args) {
		Connection con = null;
		try {
			// url ="jdbc:종류:@ip주소:포트:sid:
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##SQL_USER";
			String pwd = "1234";
			
			con =DriverManager.getConnection(url,user,pwd);
			
			if(con != null)
				System.out.println("db연셜성공");
			else
				System.out.println("db연결실패");
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
}