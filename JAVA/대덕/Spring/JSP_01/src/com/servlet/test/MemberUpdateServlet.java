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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private DataSource dataSource = DataSource.getInstance(); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Member member = dataSource.getMemberList().get(id);
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/member/update.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldId = request.getParameter("oldId");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		Map<String, Member> memberList = dataSource.getMemberList();
		memberList.remove(oldId);
		Member newMember = new Member();
		newMember.setId(id);
		newMember.setPwd(pwd);
		memberList.put(id, newMember);
		
	
		response.sendRedirect(request.getContextPath() + "/member/list");
	}

	

}
