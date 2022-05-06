package com.servlet.test;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dataSource.DataSource;
import com.jsp.vo.Board;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/board/boardDetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource dataSource = DataSource.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/views/board/detail.jsp";
		
		Map<String, Board> boardList = dataSource.getBoardList();
		String boardNum = request.getParameter("boardNum");
		
		Board board = boardList.get(boardNum);
		int viewCnt = board.getViewCnt();
		board.setViewCnt(viewCnt + 1);
		boardList.put(boardNum, board);
		
		response.setContentType("text/html; charset=utf-8");
		
		request.setAttribute("board", board);
		request.getRequestDispatcher(url).forward(request, response);
	}



}
