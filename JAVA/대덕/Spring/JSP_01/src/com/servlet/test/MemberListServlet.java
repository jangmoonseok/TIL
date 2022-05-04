package com.servlet.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dataSource.DataSource;
import com.jsp.vo.Member;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource dataSource = DataSource.getInstance();
		Map<String, Member> memberMap = dataSource.getMemberList();
		
		ArrayList<Member> memberList = new ArrayList<Member>(memberMap.values());
		
//		String result = "";
//		for(String member : memberList.keySet()) {
//			result += "<h1>아이디 : " + member + "<br>비밀번호 : " + memberList.get(member).getPwd() + "<h1>";
//			result += "--------------------------------------------------";
//		}
//		
//		request.setAttribute("result", result);
		request.setAttribute("memberList", memberList);
	
		
		request.getRequestDispatcher("/WEB-INF/views/member/list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
