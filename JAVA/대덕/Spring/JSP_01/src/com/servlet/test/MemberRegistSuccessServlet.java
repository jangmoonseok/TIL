package com.servlet.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.vo.Member;



@WebServlet("/member/regist/success")
public class MemberRegistSuccessServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/el_02.jsp";

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		Member member = new Member();
		member.setId(id);
		member.setPwd(pwd);

		request.setAttribute("member", member);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
