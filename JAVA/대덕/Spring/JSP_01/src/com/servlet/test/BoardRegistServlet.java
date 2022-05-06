package com.servlet.test;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dataSource.DataSource;
import com.jsp.vo.Board;

/**
 * Servlet implementation class BoardRegistServlet
 */
@WebServlet("/board/boardRegist")
public class BoardRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private DataSource dataSource = DataSource.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardRegist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user = request.getParameter("user");
		
		Map<String, Board> boardList = dataSource.getBoardList();
		Board board = new Board();
		int bno = boardList.size();
		board.setBno(bno);
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(user);
		board.setViewCnt(0);
		board.setRegDate(new Date());
		
		boardList.put(""+bno, board);
		response.sendRedirect(request.getContextPath() + "/board/list");
		
		
	}

}
