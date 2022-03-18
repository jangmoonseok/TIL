package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SSK03", "java");
			
			System.out.println("계좌번호 정보 추가하기");
			System.out.print("계좌번호 : ");
			String bankNo = sc.next();
			System.out.print("은행명 : ");
			String bankName = sc.next();
			System.out.print("예금주명 : ");
			String bankUser = sc.next();
			
			// Statement객체를 이용하여 데이터 추가하기
//			String sql = "insert into bankinfo"
//					+ " (bank_no, bank_name, bank_user_name, bank_date)"
//					+ " values('" + bankNo + "','" + bankName + "','" + bankUser + "', sysdate)";
			
			
//			stmt = conn.createStatement();
			
			// select문을 실행할 때는 executeQuery()메서드를 사용하고
			// insert, update, delete등과 같은 쿼리문을 실행할 때는 executeUpdate()메서드를 사용한다.
			// executeUpdate()의 반환값 ==> 작업에 성공한 레코드 수
//			int cnt = stmt.executeUpdate(sql);
			
			// PrepareStatement객체를 이용하여 추가하기
			
			// 쿼리문을 작성할 때 데이터가 들어갈 자리를 물음표(?)로 표시한다.
			String sql = "insert into bankinfo"
					+ " (bank_no, bank_name, bank_user_name, bank_date)"
					+ " values(?, ?, ?, sysdate)";
			
			// PrepareStatement객체를 생성한다 ==> 실행할 쿼리문을 인수값으로 넘겨준다
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문의 물음표자리에 들어갈 데이터를 셋팅한다
			// 형식) pstmt.set자료형이름(물음표번호, 데이터)
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUser);
			
			// 데이터 셋팅 후 쿼리문 실행
			int cnt = pstmt.executeUpdate();
			
			
			System.out.println("반환값 : " + cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) try { stmt.close(); } catch(Exception e) {};
			if(conn != null) try { conn.close(); } catch(Exception e) {};
		}
		
	}

}








