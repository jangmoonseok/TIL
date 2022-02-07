```java
package k_jdbc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import e_oop.ScanUtil;


public class Board_JDBC {

	public static void main(String[] args) {
		new Board_JDBC().start();
	}

	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); 
	String selectAll = "select * from tb_jdbc_board order by board_no";
	private void start() {
		while(true) {
			System.out.println("=====================================================");
			System.out.println("번호\t제목\t작성자\t작성일");
			List<Map<String, Object>>boardTable = JDBCUtil.selectList(selectAll);
			for(int i = boardTable.size() - 1; i >= 0; i--) {
				System.out.println("-----------------------------------------------------");
				Map<String, Object> board = boardTable.get(i);
				System.out.println(board.get("BOARD_NO") + 
						"\t" + board.get("TITLE") +
						"\t" + board.get("MEM_ID") + 
						"\t" + board.get("REG_DATE"));
			}
			System.out.println("=====================================================");
			System.out.println("1.조회  2.등록  0.종료 >");
			int input = ScanUtil.nextInt();
			switch(input) {
			case 1:
				read();
				break;
			case 2:
				insert();
				break;
			case 0:
				System.out.println("프로그램이 종료되었습니다.");
				System.exit(0);
			}
		}
	}
	private void read() {
		try {
			System.out.println("조회할 게시물 번호를 입력해주세요.");
			int boardNo = ScanUtil.nextInt();
			
			Map<String, Object> board = null;
			
			String sql = "select * from tb_jdbc_board"
					+ " where board_no = ?";
			List<Object> param = new ArrayList<Object>();
			param.add(boardNo);
			board = JDBCUtil.selectOne(sql, param);
			
			for(int i = 0; i < board.size(); i++) {
			System.out.println("================================================");
			System.out.println("번호\t:" + board.get("BOARD_NO"));
			System.out.println("------------------------------------------------");
			System.out.println("작성자\t:" + board.get("MEM_ID"));
			System.out.println("------------------------------------------------");
			System.out.println("작성일\t:" + board.get("REG_DATE"));
			System.out.println("------------------------------------------------");
			System.out.println("제목\t:" + board.get("TITLE"));
			System.out.println("------------------------------------------------");
			System.out.println("내용\t:" + board.get("CONTENT"));
			System.out.println("================================================");
			
			System.out.println("1.수정  2.삭제  0.목록 > ");
			int input = ScanUtil.nextInt();
			
			switch(input) {
			case 1:
				update(board);
				break;
			case 2:
				delete(board);
				break;
			case 0:
				
				break;
			}
		}
		}catch(Exception e) {
			System.out.println("게시물 번호에 해당하는 글이 존재하지 않습니다.");
		}
		
	}
	private void delete(Map<String, Object> board) {
		System.out.println("정말 삭제하시겠습니까? > (Y/N)");
		String input = ScanUtil.nextLine();
		if(input.equals("Y")) {			
			String sql = "delete from tb_jdbc_board"
					+ " where board_no = ?";
			List<Object> param = new ArrayList<Object>();
			param.add(board.get("BOARD_NO"));
			int success = JDBCUtil.update(sql, param);
			
			if(success != 0) {
				System.out.println("삭제성공");
			}else {
				System.out.println("삭제실패");
			}
		}
	}
	private void update(Map<String, Object> board) {
		String sql = "update tb_jdbc_board"
				+ " set title = ?, content = ?"
				+ " where board_no = ?";
		List<Object> param = new ArrayList<Object>();
		System.out.println("수정할 제목 >");
		param.add(ScanUtil.nextLine());
		System.out.println("수정할 내용 >");
		param.add(ScanUtil.nextLine());
		param.add(board.get("BOARD_NO"));
		
		int success = JDBCUtil.update(sql, param);
		
		if(success != 0) {
			System.out.println("수정성공");
		}else {
			System.out.println("수정실패");
		}
	}
	private void insert() {
		
		String sql = "insert into tb_jdbc_board"
				+ " values("
				+ "(select nvl(max(board_no),0) + 1 from tb_jdbc_board),"
				+ "?,?,?,"
				+ "sysdate)";
		List<Object> param = new ArrayList<Object>();
		

		System.out.println("제목 >");
		param.add(ScanUtil.nextLine());
		System.out.println("내용 >");
		param.add(ScanUtil.nextLine());
		System.out.println("작성자 >");
		param.add(ScanUtil.nextLine());
		
		int success = JDBCUtil.update(sql, param);
		
		if(success != 0) {
			System.out.println("등록성공");
		}else {
			System.out.println("등록실패");
		}
		
	}

}
```
