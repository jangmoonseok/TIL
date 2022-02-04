```java
package k_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

public class JDBC_Exercise {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SSK03";
		String password = "java";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
//		try {
//			con = DriverManager.getConnection(url, user, password);
//			
//			String sql = "select *"
//					+ " from member"
//					+ " where mem_add1 like '%' || ? || '%'";
//			ps = con.prepareStatement(sql);
//			ps.setString(1, "대전");
//			rs = ps.executeQuery();
//			
//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			
//			while(rs.next()) {
//				String memId = rs.getString("mem_id");
//				String memName = rs.getString("mem_name");
//				Date memBir = rs.getDate("mem_bir");
//				String memPhone = rs.getString("mem_hp");
//				
//				System.out.println("MEM_ID : " + memId + "\t MEM_NAME : " + memName + "\t MEM_BIR : " + memBir + "\t memPhone : " + memPhone );
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if(rs != null) try { rs.close(); } catch(Exception e){};
//			if(ps != null) try { ps.close(); } catch(Exception e){};
//			if(con != null) try { con.close(); } catch(Exception e){};
//		}
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
			//update
//			String sql = "update member"
//					+ " set mem_mileage = mem_mileage + 100"
//					+ " where mem_add1 like ? ||'%'";
//			
//			ps = con.prepareStatement(sql);
//			ps.setString(1, "대전");
			
			
			//insert
//			String sql = "INSERT INTO GOODS VALUES(?,?,?)";
//			ps = con.prepareStatement(sql);
//			ps.setString(1, "P103");
//			ps.setString(2, "삼양라면");
//			ps.setInt(3, 1200);
//			
			//delete
			String sql = "DELETE FROM GOODS"
					+ " WHERE GOOD_NAME = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, "삼양라면");
			
			int count = ps.executeUpdate();
			
			if(count == 0) {
				System.out.println("데이터 입력 실패");
			}else {
				System.out.println("데이터 입력 성공");
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
