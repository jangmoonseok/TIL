```java
package k_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

public class JDBC_Exercise2 {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SSK03";
		String password = "java";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
			//장바구니 테이블에서 2005년 5월 판매정보 조회(일자, 상품명,수량,금액)
			String sql = "select *"
					+ " from cart a, prod b"
					+ " where a.cart_prod = b.prod_id"
					+ " and a.cart_no like ? || '%'"
					+ " order by cart_no";
			ps = con.prepareStatement(sql);
			ps.setString(1,"200505");
			rs = ps.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while(rs.next()) {
				String cartNo = rs.getString("cart_no").substring(0, 8);
				String prodName = rs.getString("prod_name");
				int prodQTY = rs.getInt("cart_qty");
				int price = rs.getInt("cart_qty") * rs.getInt("prod_price");
				
				System.out.println("판매일: " + cartNo + "\t 상품명: " + prodName + "\t 수량: " + prodQTY + "\t 금액: " + price);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(Exception e){};
			if(ps != null) try { ps.close(); } catch(Exception e){};
			if(con != null) try { con.close(); } catch(Exception e){};
		}
	}

}
```
