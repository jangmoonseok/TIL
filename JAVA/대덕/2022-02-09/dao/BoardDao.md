```java
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.BoardService;
import util.JDBCUtil;
import util.ScanUtil;

public class BoardDao {
	
	private BoardDao() { // 다른 클래스에서 객체생성을 막기 위해 생성자에 private를 붙인다
		
	}
	private static BoardDao instance; // 객체를 만들어서 보관 할 변수
	public static BoardDao getInstance() { // 다른 클래스에서 객체가 필요할 때 호출 할 메서드
		if(instance == null) {
			// 객체가 없으면 객체 생성
			instance = new BoardDao();
		}
		return instance;
	}
	
	public List<Map<String, Object>> selectBoardList(){
		String sql = "SELECT A.BOARD_NO,"
				+ "          A.TITLE,"
				+ "          B.MEM_NAME,"
				+ "          TO_CHAR(A.REG_DATE, 'MM/DD') AS REG_DATE"
				+ "     FROM TB_JDBC_BOARD A"
				+ "     LEFT OUTER JOIN TB_JDBC_MEMBER B ON A.MEM_ID = B.MEM_ID"
				+ "    ORDER BY 1 DESC";
		
		return JDBCUtil.selectList(sql);
	}
	
	public Map<String,Object> selectboard(int boardNo){
		String sql = "SELECT A.BOARD_NO,"
				+ "          A.TITLE,"
				+ "          A.CONTENT,"
				+ "          B.MEM_NAME,"
				+ "          TO_CHAR(A.REG_DATE, 'MM/DD') AS REG_DATE"
				+ "     FROM TB_JDBC_BOARD A"
				+ "     LEFT OUTER JOIN TB_JDBC_MEMBER B ON A.MEM_ID = B.MEM_ID"
				+ "    WHERE BOARD_NO = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(boardNo);
		
		return JDBCUtil.selectOne(sql, param);
	}

	public int updateBoard(String title, String content, Object boardNo) {
		String sql = "UPDATE TB_JDBC_BOARD"
				+ "      SET TITLE = ?, CONTENT = ?"
				+ "    WHERE BOARD_NO = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(title);
		param.add(content);
		param.add(boardNo);
		
		return JDBCUtil.update(sql, param);
	}

	public int deleteBoard(Object boardNo) {
		String sql = "DELETE FROM TB_JDBC_BOARD"
				+ "    WHERE BOARD_NO = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(boardNo);
		
		return JDBCUtil.update(sql, param);
	}

	public int insertBoard(String title, String content, Object memId) {
		String sql = "INSERT INTO TB_JDBC_BOARD"
				+ "   VALUES("
				+ "   (SELECT NVL(MAX(BOARD_NO),0) + 1 FROM TB_JDBC_BOARD),"
				+ "   ?, ?, ?,"
				+ "   SYSDATE"
				+ ")";
		
		List<Object> param = new ArrayList<Object>();
		param.add(title);
		param.add(content);
		param.add(memId);
		
		return JDBCUtil.update(sql, param);
	}
}
