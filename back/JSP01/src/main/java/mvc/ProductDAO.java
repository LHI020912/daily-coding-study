package mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
	DBConnect Dbcon = new DBConnect();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<ProductVO> productList() {
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		try {
			con = Dbcon.getCon();
			String sql = "select * from product";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				String prdNo = rs.getString("prdNo");
				String prdName = rs.getString("prdName");
				int prdPrice = rs.getInt("prdPrice");
				String prdCompany = rs.getString("prdCompany");
				
				ProductVO vo = new ProductVO(prdNo, prdName, prdPrice, prdCompany);
				prdList.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("제품정보 조회 실패");
		}
		return prdList;
	}
}
