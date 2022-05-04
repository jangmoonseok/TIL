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
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet("/member/detail")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataSource dataSource = DataSource.getInstance(); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println(id);
		Map<String, Member> memberList = dataSource.getMemberList();
		Member member = memberList.get(id);
		System.out.println("member = " + member.getId());
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/member/detail.jsp").forward(request, response);
	}


}
