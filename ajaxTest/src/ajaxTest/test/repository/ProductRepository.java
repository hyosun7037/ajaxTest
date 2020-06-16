package ajaxTest.test.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import ajaxTest.test.db.DBConn;
import ajaxTest.test.model.Product;
import ajaxTest.test.model.Type;

public class ProductRepository {
	private static final String TAG = "ProductRepository : "; // TAG 생성 (오류 발견시 용이)
	private static ProductRepository instance = new ProductRepository();

	private ProductRepository() {}
	
	public static ProductRepository getInstnce() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<Product> findAll() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, name, type, price,count FROM product";
		List<Product> products = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product product = Product.builder()
								.id(rs.getInt(1))
								.name(rs.getString(2))
								.type(Type.valueOf(rs.getString(3)))
								.price(rs.getInt(4))
								.count(rs.getInt(5))
								.build();
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
	
	public List<Product> priceDESC() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, name, type, price,count FROM product ORDER BY price DESC ";
		List<Product> products = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product product = Product.builder()
								.id(rs.getInt(1))
								.name(rs.getString(2))
								.type(Type.valueOf(rs.getString(3)))
								.price(rs.getInt(4))
								.count(rs.getInt(5))
								.build();
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "priceDESC : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
	
	
	
	
	
	public List<Product> countDESC() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, name, type, price,count FROM product ORDER BY count DESC ";
		List<Product> products = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product product = Product.builder()
								.id(rs.getInt(1))
								.name(rs.getString(2))
								.type(Type.valueOf(rs.getString(3)))
								.price(rs.getInt(4))
								.count(rs.getInt(5))
								.build();
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "countDESC : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
	
	
	
	
	public int delete(int id) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "DELETE FROM product WHERE id = ? ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "countDESC : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	
	
}
