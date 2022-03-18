package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * 	회원을 관리하는 프로그램을 작성하시오.(MYMEMBER테이블 이용)
 * 
 * 	아래 메뉴의 기능을 모두 구현하시오(CRUD구현)
 * 
 * 	메뉴예시)
 * 	---------------------------
 * 	===== 작업 선택 =====
 * 	1. 자료 추가
 * 	2. 자료 수정
 * 	3. 자료 삭제
 * 	4. 전체 자료 출력
 * 	0. 작업 끝
 * 
 * 	조건)
 * 	1. 자료 추가에서 '회원ID'는 중복되면 안된다.
 * 	2. 자료 삭제는 '회원ID'만 입력받아서 처리
 * 	3. 자료 수정에서 '회원ID'는 변경 불가
 */

public class JdbcTest06 {
	Scanner sc = new Scanner(System.in);
	Connection conn = DBUtil.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	public static void main(String[] args) {
		new JdbcTest06().start();
	}
	
	private void start() {
		while(true) {
			System.out.println("======================= 작업 선택 =======================");
			System.out.println("1.자료 추가 2.자료 수정 3.자료 삭제 4.전체 자료 출력 0.프로그램 종료");
			System.out.print("작업을 선택하세요 : ");
			int input = sc.nextInt();
			
			switch(input) {
			case 1: addMember(); break;
			case 2: updateMember(); break;
			case 3: deleteMember(); break;
			case 4: readAllMember(); break;
			case 0: 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}

	private void readAllMember() {
		System.out.println("----------------------------- 회원 정보 -----------------------------");
		System.out.printf("%-10s%-10s%-15s%-10s", "회원ID", "회원이름", "전화번호", "주소");
		System.out.println();
		try {
			String sql = "select * from mymember";
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.printf("%-10s%-10s%-15s%-10s", rs.getString("mem_id"), rs.getString("mem_name"), rs.getString("mem_tel"), rs.getString("mem_addr"));
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	private void deleteMember() {
		try {
			System.out.print("삭제할 회원의 ID를 입력하세요 : ");
			String id = sc.next();
			
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) System.out.println("회원 삭제 완료");
			else System.out.println("존재하지 않는 회원입니다.");
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	private void updateMember() {
		try {
			String id;
			while(true) {			
				System.out.print("수정할 회원의 ID를 입력하세요 : ");
				id = sc.next();
				
				String idCheckSql = "select * from mymember where mem_id = ?";
				
				pstmt = conn.prepareStatement(idCheckSql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(!rs.next()) System.out.println(id + "회원은 없는 회원입니다. 다시 입력하세요");
				else break;
			}
			System.out.print("이름 : ");
			String name = sc.next();
			System.out.print("비밀번호 : ");
			String pwd = sc.next();
			System.out.print("전화번호 : ");
			String tel = sc.next();
			sc.nextLine();
			System.out.print("주소 : ");
			String addr = sc.nextLine();
			
			String sql = "update mymember"
					+ "		 set mem_name = ?,"
					+ "			 mem_pass = ?,"
					+ "			 mem_tel = ?,"
					+ "			 mem_addr = ?"
					+ "	   where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) System.out.println("회원정보 수정 성공");
			else System.out.println("회원정보 수정 실패");
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	private void addMember() {
			String id;
		try {			
			while(true) {			
				System.out.print("아이디 : ");
				id = sc.next();
				
				String idCheckSql = "select * from mymember where mem_id = ?";
				
				pstmt = conn.prepareStatement(idCheckSql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) System.out.println(id + "는 중복된 아이디입니다. 다시 입력하세요");
				else break;
			}
			
			System.out.print("이름 : ");
			String name = sc.next();
			System.out.print("비밀번호 : ");
			String pwd = sc.next();
			System.out.print("전화번호 : ");
			String tel = sc.next();
			sc.nextLine();
			System.out.print("주소 : ");
			String addr = sc.nextLine();
			
			String sql = "insert into mymember"
					+ " (mem_id, mem_name, mem_pass, mem_tel, mem_addr)"
					+ " values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) System.out.println("회원 추가 성공");
			else System.out.println("회원 추가 실패");
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
