package com.servlet.test;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dataSource.DataSource;
import com.jsp.vo.Member;

/**
 * Servlet implementation class MemberRegistServlet
 */
@WebServlet("/member/regist")
public class MemberRegistServlet extends HttpServlet {
	
	private DataSource dataSource = DataSource.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/WEB-INF/views/member/regist.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Member> memberList = dataSource.getMemberList();
		
		//입력
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String url = request.getContextPath()+"/member/list";
		
		//처리
		Member member = new Member();
		member.setId(id);
		member.setPwd(pwd);
		memberList.put(id, member);
		//memberService.regist(member); <-----DB저장
		
		
		//출력
		
		response.sendRedirect(url);
		/*
		 * request.setAttribute("member", member);
		 * request.getRequestDispatcher(url).forward(request,response);
		 */
	}

}








