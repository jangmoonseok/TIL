package kr.or.ddit.jdbcboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.jdbcboard.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	private static IBoardDao instance;
	
	private BoardDaoImpl() {}
	
	public static IBoardDao getInstance() {
		if(instance == null) instance = new BoardDaoImpl();
		return instance;
	}

	@Override
	public int writeBoard(Connection conn, BoardVO boardVo) throws SQLException {
		String sql = "insert into jdbc_board(board_no, board_title, board_writer, board_date, board_cnt, board_content)"
				+ "		values(board_seq.nextVal, ?, ?, sysdate, 0, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardVo.getTitle());
		pstmt.setString(2, boardVo.getWriter());
		pstmt.setString(3, boardVo.getContent());
		
		int result = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return result;
	}

	@Override
	public int deleteBoard(Connection conn, int num) throws SQLException {
		String sql = "delete from jdbc_board where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		
		int result = pstmt.executeUpdate();
		
		return result;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO boardVo, int num) throws SQLException {
		String sql = "update jdbc_board"
				+ "		set board_title = ?,"
				+ "			board_content = ?"
				+ "	  where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardVo.getTitle());
		pstmt.setString(2, boardVo.getContent());
		pstmt.setInt(3, num);
		
		int result = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return result;
	}

	@Override
	public BoardVO readBoard(Connection conn, int num) throws SQLException {
		int result = updateCount(conn, num);
		BoardVO boardVo = null;
		
		if(result > 0) {			
			String sql = "select * from jdbc_board where board_no = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {					
				boardVo = new BoardVO();
				
				boardVo.setNo(rs.getInt("board_no"));
				boardVo.setTitle(rs.getString("board_title"));
				boardVo.setWriter(rs.getString("board_writer"));
				boardVo.setDate(rs.getDate("board_date"));
				boardVo.setCnt(rs.getInt("board_cnt"));
				boardVo.setContent(rs.getString("board_content"));
			}
			
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
		}else {
			System.out.println("조회 실패..");
		}
		
		
		return boardVo;
	}

	@Override
	public List<BoardVO> readAll(Connection conn) throws SQLException {
		List<BoardVO> boardList = null;
				
		String sql = "select * from jdbc_board";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			boardList = new ArrayList<BoardVO>();
			
			BoardVO boardVo = new BoardVO();
			
			boardVo.setNo(rs.getInt("board_no"));
			boardVo.setTitle(rs.getString("board_title"));
			boardVo.setWriter(rs.getString("board_writer"));
			boardVo.setDate(rs.getDate("board_date"));
			boardVo.setCnt(rs.getInt("board_cnt"));
			boardVo.setContent(rs.getString("board_content"));
			
			boardList.add(boardVo);
		}
		
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		
		return boardList;
	}

	@Override
	public List<BoardVO> findBoard(Connection conn, String text) throws SQLException {
		List<BoardVO> findList = new ArrayList<BoardVO>();
		
		String sql = "";
		if(text == null || text.equals(" ")) {	
			sql = "select * from jdbc_board";
		}else {
			sql = "select * from jdbc_board where board_title like '%" + text + "%'";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			BoardVO boardVo = new BoardVO();
			
			boardVo.setNo(rs.getInt("board_no"));
			boardVo.setTitle(rs.getString("board_title"));
			boardVo.setWriter(rs.getString("board_writer"));
			boardVo.setDate(rs.getDate("board_date"));
			boardVo.setCnt(rs.getInt("board_cnt"));
			boardVo.setContent(rs.getString("board_content"));
			
			findList.add(boardVo);
		}
		
		return findList;
	}

	@Override
	public int updateCount(Connection conn, int num) throws SQLException {
		String sql = "update jdbc_board"
				+ " set board_cnt = board_cnt + 1"
				+ " where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		
		int result = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return result;
	}

}
