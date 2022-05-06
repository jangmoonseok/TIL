package com.servlet.test;

import java.io.IOException;
import java.net.CookieStore;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dataSource.DataSource;
import com.jsp.vo.Member;

/**
 * Servlet implementation class BoardLoginServlet
 */
@WebServlet("/board/login")
public class BoardLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataSource dataSource = DataSource.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/WEB-INF/views/board/login.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Member> memberList = dataSource.getMemberList();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		if(memberList.get(id) == null) {
			response.sendRedirect(request.getContextPath() + "/board/loginFalse");
		}else {
			if(!memberList.get(id).getPwd().equals(pwd)) {
				response.sendRedirect(request.getContextPath() + "/board/loginFalse");
			}else {
				Cookie user = new Cookie("user", id);
				response.addCookie(user);
				response.sendRedirect(request.getContextPath() + "/board/list");
			}
		}

		
	}

}
