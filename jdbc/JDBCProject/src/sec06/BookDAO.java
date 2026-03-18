package sec06;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sec05.DBConnect;

public class BookDAO implements IBookDAO{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnect dbCon = null;
	BookDTO btd = null;
	ArrayList<BookDTO> btdList = null;
	
	public BookDAO() {
		con = DBConnect.getConnection();
	}

	@Override
	public void insertBook(BookDTO dto) {
		// 도서 등록
		try {
			String sql = "insert into book values(?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			// 변수 설정
			pstmt.setString(1, dto.getBookNo());
			pstmt.setString(2, dto.getBookName());
			pstmt.setString(3, dto.getBookAuthor());
			pstmt.setInt(4, dto.getBookPrice());
			pstmt.setString(5, dto.getBookDate());
			pstmt.setInt(6, dto.getBookStock());
			pstmt.setString(7, dto.getPubNo());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0)
				System.out.println("도서 등록 성공 !");
			
		}catch(SQLException e) {
			System.out.println("도서 등록 실패");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt);
		}
	}

	@Override
	public BookDTO detailBook (String bookNo) {
		// 도서 정보 조회
		btdList = new ArrayList<BookDTO>();
		
		try {
			String sql = "select * from book where bookNo=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bookNo = rs.getString(1);
				String bookName = rs.getString(2);
				String bookAuthor = rs.getString(3);
				int bookPrice = rs.getInt(4);
				String bookDate = rs.getString(5);
				int bookStock = rs.getInt(6);
				String pubNo = rs.getString(7);
				
				btd = new BookDTO(bookNo, bookName, bookAuthor, 
						bookPrice, bookDate, bookStock, pubNo);
			}else {
				System.out.println("도서 번호에 해당하는 도서가 없습니다.");
			}
			
		}catch(SQLException e) {
			System.out.println("오류 발생");
			e.printStackTrace();
		}finally {
			DBConnect.close(con);
		}
		return btd;
	}

	@Override
	public void updateBook(BookDTO dto) {
		// 도서 정보 수정
		String sql = "update book set bookNo =?, bookName=?, bookAuthor=?,"
				+ "bookPrice=?, bookDate=?, bookStrock=?, pubNo=?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getBookNo());
			pstmt.setString(2, dto.getBookName());
			pstmt.setString(3, dto.getBookAuthor());
			pstmt.setInt(4, dto.getBookPrice());
			pstmt.setString(5, dto.getBookDate());
			pstmt.setInt(6, dto.getBookStock());
			pstmt.setString(7, dto.getPubNo());
			
			int result = pstmt.executeUpdate();
			if(result > 0)
				System.out.println("도서의 정보가 수정되었습니다.");
			else
				System.out.println("해당 도서가 없습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt);
		}
		
	}

	@Override
	public void deleteBook(String bookNo) {
		// 도서 정보 삭제
		String sql = "delete from book where bookNo=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0)
				System.out.println("도서가 삭제되었습니다.");
			else
				System.out.println("찾으시는 도서가 없습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt);
		}
	}

	@Override
	public boolean searchBookNo(String bookNo) {
		// 중복 확인
		String sql = "select * from book where bookNo=?";
		boolean res = true;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				res = false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt,rs);
		}
		return true;
	}
	

}
