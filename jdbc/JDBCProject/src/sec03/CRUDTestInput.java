package sec03;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import sec02.DBConnect;

public class CRUDTestInput {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		DBConnect dbCon = new DBConnect();
        PreparedStatement pstmt = null;
		String sql = null;
		String bookNo,bookTitle, bookAuthor, bookDate, pubNo;
		int bookPrice, bookStock;
		
		try {
			con = dbCon.getConnection();
			stmt = con.createStatement();
			
			bookNo="98";
			bookTitle="자바";
			bookAuthor = "김바로";
			bookPrice=10000;
			bookDate = "2021-03-01";
			bookStock = 5;
			pubNo = "2";
			// 쿼리구성이 복잡함 + 사용할때마다 전처리
			/* sql ="insert into book values('"+bookNo+"','"+bookTitle+"','"+bookAuthor+"',"
			+bookPrice+",'"+bookDate+"',"+bookStock+",'"+pubNo+"')";
			
			reparedStatement 객체
		 		Statement 인터페이스의 하위 인터페이스
				변수 바인딩 진행 (? : 바인딩 문자->placeholder): 인수가 많은 복잡한 sql문에 편리
				처음 한 번 전처리 후 다음 사용시에는 전처리 하지 않음 = 처리속도가 빠름*/
			sql = "insert into book values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);// prepareStatement 객체반환
			//데이터 바인딩
			pstmt.setString(1,bookNo);
			pstmt.setString(2,bookTitle);
			pstmt.setString(3,bookAuthor);
			pstmt.setInt(4,bookPrice);
			pstmt.setString(5,bookDate);
			pstmt.setInt(6,bookStock);
			pstmt.setString(7,pubNo);
			int tmpResult = pstmt.executeUpdate(sql);
			if(tmpResult ==1)
				System.out.print("인서트 성공");

			sql = "select * from book";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				bookNo = rs.getString(1);
				bookTitle = rs.getString(2);
				bookAuthor = rs.getString(3);
				bookPrice = rs.getInt(4);
				Date bkbookDate = rs.getDate(5);
				bookStock = rs.getInt(6);
				pubNo = rs.getString(7);
				System.out.format("%-10s\t %-20s\t %-10s %6d %13s \t%3d %10s\n",
						bookNo,bookTitle, bookAuthor, bookPrice,bkbookDate, bookStock, pubNo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
