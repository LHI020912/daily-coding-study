package sec04;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import sec03.DBConnect;

public class DeleteTest {

	public static void main(String[] args) {
		// 필요 참조변수
		DBConnect dbCon = new DBConnect();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		String sql = null;
		String bookNo,bookTitle, bookAuthor, bookDate, pubNo;
		int bookPrice, bookStock;
		
		try {
			con = dbCon.getConnection();
			sql = "delete book where bookNo=?";
			pstmt = con.prepareStatement(sql);
			
			System.out.print("삭제할 도서번호 입력");
			bookNo=sc.nextLine();
			
			pstmt.setString(1, bookNo);
			
			pstmt.executeUpdate();
			System.out.println("도서정보 삭제 성공");
			System.out.println("--------- 전체 도서 정보 조회 ------------");
			System.out.println("도서번호 \t 도서명 \t\t\t\t 저자 \t\t  가격 \t\t 발행일  \t재고 \t출판사번호");
			
			sql = "select * from book where bookNo=?";
			PreparedStatement pstmtSel = con.prepareStatement(sql);
			pstmtSel.setString(1, bookNo);
			rs = pstmtSel.executeQuery(); // 바인딩문제 조시매~
			
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
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			System.out.println("도서정보 삭제 실패");
			e.printStackTrace();
		}
	}

}
