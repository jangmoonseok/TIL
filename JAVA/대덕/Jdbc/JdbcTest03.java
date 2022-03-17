package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) LPROD_ID값을 2개를 입력 받아서 두 값들 중 작은 값부터 큰 값 사이의 자료들을 출력하시오
public class JdbcTest03 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("LPROD_ID값 입력 : ");
		int id1 = sc.nextInt();
		System.out.print("LPROD_ID값 입력 : ");
		int id2 = sc.nextInt();
		
//		if(id1 > id2) {
//			int temp = id1;
//			id1 = id2;
//			id2 = temp;
//		}
		
		int min = Math.min(id1, id2);
		int max = Math.max(id1, id2);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SSK03", "java");
			
//			String sql = "select * from lprod where lprod_id >= " + min + " and lprod_id <= " + max;
			String sql = "select * from lprod where lprod_id between " + min + " and " + max;
			
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
