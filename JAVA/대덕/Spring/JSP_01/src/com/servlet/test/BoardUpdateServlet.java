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
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource = DataSource.getInstance();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/views/board/update.jsp";
		
		Map<String, Board> boardList = dataSource.getBoardList();
		String bno = request.getParameter("boardNum");
		Board board = boardList.get(bno);
		
		request.setAttribute("board", board);
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, Board> boardList = dataSource.getBoardList();
		String bno = request.getParameter("boardNum");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Board board = boardList.get(bno);
		board.setTitle(title);
		board.setContent(content);
		boardList.put(bno, board);
		
		response.sendRedirect(request.getContextPath() + "/board/boardDetail?boardNum=" + bno);
		
	}

}
