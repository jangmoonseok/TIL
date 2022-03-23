package kr.or.ddit.jdbcboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.jdbcboard.vo.BoardVO;

public interface IBoardService {
	
	public int writeBoard(BoardVO boardVo);
	
	public int deleteBoard(int num);
	
	public int updateBoard(BoardVO boardVo, int num); 
	
	public BoardVO readBoard(int num); 
	
	public List<BoardVO> readAll(); 
	
	public List<BoardVO> readFindBoard(String text); 
}
