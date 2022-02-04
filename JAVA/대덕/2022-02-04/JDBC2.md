```java
package k_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JDBC2 {

	public static void main(String[] args) {
		//데이터베이스 접속 정보
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //@앞부분은 jdbc버전 @뒷부분은 oracle호스트이름,포트,SID 
		String user = "SSK03";
		String password = "java";
		
		
		Connection con = null;
		PreparedStatement ps = null; //PreparedStatement : 동적으로 할당해야 하는 값이 있을때 사용, 없으면 Statement객체 사용
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "select *"
					+ " from cart"
					+ " where cart_member = ?"
					+ " and cart_qty > ?"; //sql문 줄바꿈시에 맨 앞에 반드시 공백 필요, ?에는 값만 들어갈수 있다(컬럼명,키워드, 등.. 사용 X)
			ps = con.prepareStatement(sql);
			ps.setString(1, "a001"); // sql문 첫번째 물음표에 넣을 값과 타입지정
			ps.setInt(2, 5); // sql문 두번째 물음표에 넣을 값과 타입지정
//			ps.setObject(0, sql) : 타입 상관없이 넣을 값 지정
			
			rs = ps.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData(); //메타데이터 : 데이터에 대한 데이터
			int columnCount = metaData.getColumnCount(); //컬럼수
			
			while(rs.next()) {
				for(int i = 1; i <= columnCount; i++) {
					System.out.print(rs.getObject(i) + "\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 결과추출 후 ResultSet, Statement, Connection 닫기
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
			if(con != null) try { con.close(); } catch (Exception e) {}
		}
	}

}
```
