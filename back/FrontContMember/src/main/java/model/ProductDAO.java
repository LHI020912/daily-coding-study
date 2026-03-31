package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ProductDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;

    public ProductDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 전체 상품 목록 조회
    public List<ProductVo> listProducts() {
        List<ProductVo> productList = new ArrayList<ProductVo>();
        String sql = "SELECT PRDNO, PRDNAME, PRDPRICE, PRDCOMPANY FROM product";

        try (Connection conn = dataFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String prdNo      = rs.getString("PRDNO");
                String prdName    = rs.getString("PRDNAME");
                int    prdPrice   = rs.getInt("PRDPRICE");
                String prdCompany = rs.getString("PRDCOMPANY");

                ProductVo vo = new ProductVo(prdNo, prdName, prdPrice, prdCompany);
                productList.add(vo);
            }
        } catch (Exception e) {
            System.out.println("상품 정보 조회 실패");
            e.printStackTrace();
        }
        return productList;
    }

    // 상품 추가
    public void addProduct(ProductVo vo) {
        try {
            conn = dataFactory.getConnection();
            String query = "INSERT INTO product (prdno, prdname, prdprice, prdcompany) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, vo.getPrdNo());
            pstmt.setString(2, vo.getPrdName());
            pstmt.setInt(3, vo.getPrdPrice());
            pstmt.setString(4, vo.getPrdCompany());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 상품 단건 조회
    public ProductVo findProduct(String _prdNo) {
        ProductVo prdInfo = null;
        try {
            conn = dataFactory.getConnection();
            String query = "SELECT * FROM product WHERE prdno = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, _prdNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String prdNo      = rs.getString("prdno");
                String prdName    = rs.getString("prdname");
                int    prdPrice   = rs.getInt("prdprice");
                String prdCompany = rs.getString("prdcompany");
                prdInfo = new ProductVo(prdNo, prdName, prdPrice, prdCompany);
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prdInfo;
    }

    // 상품 수정
    public void modProduct(ProductVo vo) {
        try {
            conn = dataFactory.getConnection();
            String query = "UPDATE product SET prdname=?, prdprice=?, prdcompany=? WHERE prdno=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, vo.getPrdName());
            pstmt.setInt(2, vo.getPrdPrice());
            pstmt.setString(3, vo.getPrdCompany());
            pstmt.setString(4, vo.getPrdNo());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 상품 삭제
    public void delProduct(String _prdNo) {
        try {
            conn = dataFactory.getConnection();
            String query = "DELETE FROM product WHERE prdno=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, _prdNo);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
