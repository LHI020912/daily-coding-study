package sec02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectMain {
	public static void main(String[] args) {
		// DB연결 TEST
		Connection con = null; // 외부자원 db사용
		DBConnect dbCon = new DBConnect(); // db 연결담당 클래스 참조변수
		con = dbCon.getConnection(); // db 연결 요청
		Statement stmt = null; // 외부자원 db사용
		ResultSet rs = null; // 외부자원 db사용
		
		// DB 테이블 사용 - SELECT구문 실행
		// 쿼리문을 DBMS에 전송하기 위해 필요한 객체 생성: Statement 객체 생성
		// select 문 결과 객체를 받기위한 객체생성: ResultSet 객체생성
		try {
			con = dbCon.getConnection();
			stmt = con.createStatement(); // statement객체 인스턴스반환
			String sql = "select * from book order by bookno";
			
			// 질의어가 select 이므로 Statement.executrQuery(질의문자열): 반환값으로 view객체가 반환
			rs = stmt.executeQuery(sql);
			
			// 오류 없이 반환된 값이 있으면
			// 반환값 접근하고 필요한 경우 가져다가 사용(프린트진행예정)
			System.out.println("--------- 전체 도서 정보 조회 ------------");
			System.out.println("도서번호 \t 도서명 \t\t\t\t 저자 \t\t  가격 \t\t 발행일  \t재고 \t출판사번호");
			
			// ResultSet 객체 활용 전달된 질의 결과를 순회하면서 변수에 담기
			// ResultSet.next(): 포인터 이동 후 참조해야하는 레코드가 있으면 true, 레코드가 없으면 false
			while(rs.next()) {
				// 현재 rs 참조하는 레코드의 각 컬럼의 값을 반환받아서 변수에 저장
				String bookNo = rs.getString(1);
				String bookName = rs.getString(2);
				String bookAuthor = rs.getString(3);
				int bookPrice = rs.getInt(4);
				Date bookDate = rs.getDate(5);
				int bookStock = rs.getInt(6);
				String pubNo = rs.getString(7);
				System.out.format("%-10s\t %-20s\t %-10s %6d %13s \t%3d %10s\n",
						bookNo,bookName, bookAuthor, bookPrice,bookDate, bookStock, pubNo);
			}
			// 생선된 객체를 닫을때는 생성순서의 반대로 닫음
			rs.close();
			stmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
