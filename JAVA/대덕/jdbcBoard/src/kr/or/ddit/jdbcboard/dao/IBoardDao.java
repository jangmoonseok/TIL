package kr.or.ddit.jdbcboard.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.jdbcboard.vo.BoardVO;

public interface IBoardDao {

	public int writeBoard(Connection conn, BoardVO boardVo) throws SQLException;
	
	public int deleteBoard(Connection conn, int num) throws SQLException;
	
	public int updateBoard(Connection conn, BoardVO boardVo, int num) throws SQLException;
	
	public BoardVO readBoard(Connection conn, int num) throws SQLException;
	
	public List<BoardVO> readAll(Connection conn) throws SQLException;
	
	public List<BoardVO> findBoard(Connection conn, String text) throws SQLException; 
	
	public int updateCount(Connection conn, int num) throws SQLException;

}
