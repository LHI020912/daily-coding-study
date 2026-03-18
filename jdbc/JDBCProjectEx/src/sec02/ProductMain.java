package sec02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductMain {

	public static void main(String[] args) {
		Connection con = null;
		DBConnect dbCon = new DBConnect();
		con = dbCon.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = dbCon.getConnection();
			stmt = con.createStatement();
			String sql = "select * from product order by prdNo";
			// 질의문자열
			rs = stmt.executeQuery(sql);

			System.out.println("--------- 전체 제품 정보 조회 ------------");
			System.out.println("prdNo \t prdName \t\t\t\t prdPrice  \t\t  prdMaker \t\t prdColo  \tctgNo");
			
			// 순회하며 변수에 담기
			while(rs.next()) {
				String prdNo = rs.getString(1);
				String prdName = rs.getString(2);
				int prdPrice = rs.getInt(3);
				String prdMaker = rs.getString(4);
				String prdColo = rs.getString(5);
				int ctgNo = rs.getInt(6);
				System.out.format("%-8s \t %-15s \t %-10d \t %-10s \t %-10s \t %d%n",
						 prdNo,prdName,prdPrice,prdMaker,prdColo,ctgNo);
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
