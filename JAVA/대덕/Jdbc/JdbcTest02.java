package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) 사용자로부터 LPROD_ID값을 입력 받아서 입력한 값보다 LPROD_ID가 큰 자료들을 출력하시오.
public class JdbcTest02 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("LPROD_ID값 입력 : ");
		int id = sc.nextInt();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SSK03", "java");
			
			String sql = "select * from lprod where lprod_id > " + id;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("----------------------------------------");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(Exception e) {};
			if(stmt != null) try {stmt.close();}catch(Exception e) {};
			if(conn != null) try {conn.close();}catch(Exception e) {};
		}
	}

}
