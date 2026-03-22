package sec01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	public static Connection getConnection() {
		Connection con =null;

		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver"); 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##SQL_USER";
			String pwd = "1234";
			
			con = DriverManager.getConnection(url,user,pwd);
			
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
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if(con != null) {
				con.close();
				con = null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
