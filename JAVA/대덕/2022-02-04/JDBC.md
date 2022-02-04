```java
package k_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {

	public static void main(String[] args) {
		/*
		 * JDBC(Java Database Connectivity)
		 * - 자바와 데이터베이스를 연결해주는 라이브러리
		 * - ojdbc : 오라클 JDBC
		 * 
		 * JDBC 작성 단계
		 * 1. Connection객체 생성(DB 연결)
		 * 2. Statement객체 생성(쿼리 작성)
		 * 3. Query 실행
		 * 4. ResultSet객체에서 결과 추출(select인 경우)
		 * 5. ResultSet, Statement, Connection 닫기
		 */
		
		//데이터베이스 접속 정보
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //@앞부분은 jdbc버전 @뒷부분은 oracle호스트이름,포트,SID 
		String user = "SSK03";
		String password = "java";
		
		
		Connection con = null;
		PreparedStatement ps = null; //PreparedStatement : 동적으로 할당해야 하는 값이 있을때 사용, 없으면 Statement객체 사용
		ResultSet rs = null;
		
		//DriverManager : 데이터베이스에 접속하기 위한 드라이버를 관리해주는 클래스 (Java와 데이터베이스가 상호작용하기 위한 중간다리 역할)
		try {
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "select * from member";
			ps = con.prepareStatement(sql); // sql을 데이터베이스로 보내기위해 적절한 형태의 객체로 만들어 저장
			
			
			//select
			rs = ps.executeQuery(); // select문 쿼리 실행후 결과값 반환(쿼리 실행 결과)
			//insert, update, delete
			//int result = ps.executeUpdate(); // select문이 아닌 쿼리 실행후 int타입의 결과반환(영향을 받은 행의 수)
			
			while(rs.next()) { // ResultSet.next() : 첫번째 행부터 순차적으로 접근하며 다음 행이 존재하지않으면 false
				String memId = rs.getString(1); //컬럼인덱스
				String memPass = rs.getString("MEM_PASS"); //컬럼명
				
				System.out.println("MEM_ID : " + memId + "/ MEM_PASS : " + memPass);
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
