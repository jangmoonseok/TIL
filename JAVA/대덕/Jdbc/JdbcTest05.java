package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 *  문제) Lprod테이블에 새로운 데이터를 추가하기
 *  
 *  lprod_gu와 lprod_nm은 입력받아서 처리하고, lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1 크게 한다.
 *  
 *  그리고 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
 */
public class JdbcTest05 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		
		String lprodGU;
		String lprodNM;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SSK03", "java");
			
			conn = DBUtil.getConnection();
			
			while(true) {				
				System.out.print("제품코드 입력 : ");
				lprodGU = sc.next();
				String checkSql = "select * from lprod where lprod_gu = ?";
				pstmt = conn.prepareStatement(checkSql);
				pstmt.setString(1, lprodGU);
				rs = pstmt.executeQuery();

				if(rs.next()) System.out.println(lprodGU + "는 중복된 제품코드입니다. 다시 입력하세요");
				else break;
			}
			System.out.print("제품카테고리 입력 : ");
			lprodNM = sc.next();
			

			
			
			String sql = "insert into lprod"
					+ " (lprod_id, lprod_gu, lprod_nm)"
					+ " values("
					+ "		(select max(lprod_id) from lprod) + 1,"
					+ "		 ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lprodGU);
			pstmt.setString(2, lprodNM);
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) System.out.println("추가 성공");
			else System.out.println("추가 실패");
			
	
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {};
			if(rs != null) try {rs.close();}catch(Exception e) {};
			if(conn != null) try {conn.close();}catch(Exception e) {};
		}
	}

}
