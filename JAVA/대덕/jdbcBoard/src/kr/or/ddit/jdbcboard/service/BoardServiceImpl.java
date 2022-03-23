package kr.or.ddit.jdbcboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.jdbcboard.dao.BoardDaoImpl;
import kr.or.ddit.jdbcboard.dao.IBoardDao;
import kr.or.ddit.jdbcboard.util.DBUtil3;
import kr.or.ddit.jdbcboard.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private static IBoardService instance;
	private IBoardDao dao;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(instance == null) instance = new BoardServiceImpl();
		return instance;
	}
	
	@Override
	public int writeBoard(BoardVO boardVo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			result = dao.writeBoard(conn, boardVo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}

	@Override
	public int deleteBoard(int num) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBUtil3.getConnection();
			result = dao.deleteBoard(conn, num);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null)try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}

	@Override
	public int updateBoard(BoardVO boardVo, int num) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBUtil3.getConnection();
			result = dao.updateBoard(conn, boardVo, num);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}

	@Override
	public BoardVO readBoard(int num) {
		Connection conn = null;
		BoardVO boardVo = null;
		
		try {
			conn = DBUtil3.getConnection();
			boardVo = dao.readBoard(conn, num);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return boardVo;
	}

	@Override
	public List<BoardVO> readAll() {
		Connection conn = null;
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil3.getConnection();
			boardList = dao.readAll(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
			
		return boardList;
	}

	@Override
	public List<BoardVO> readFindBoard(String text) {
		Connection conn = null;
		List<BoardVO> findList = null;
		
		try {
			conn = DBUtil3.getConnection();
			findList = dao.findBoard(conn, text);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
			
		return findList;
	}

}
