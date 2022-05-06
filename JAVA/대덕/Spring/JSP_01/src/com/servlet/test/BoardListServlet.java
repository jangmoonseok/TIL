package com.servlet.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dataSource.DataSource;
import com.jsp.vo.Board;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	private DataSource dataSource = DataSource.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/views/board/list.jsp";
		
		Map<String, Board> boardMap = dataSource.getBoardList();
		List<Board> boardList = new ArrayList<Board>(boardMap.values());
		

		
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher(url).forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
