package com.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Gugudan
 */
@WebServlet("/gugudan")
public class Gugudan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		//입력
		String dan = request.getParameter("dan");
		boolean flag = true;
		String str = "";
		int idan = -1;
		
		try {			
			idan = Integer.parseInt(dan);
			
		} catch (Exception e) {
			flag = false;
		}
		
		//처리
		if(flag) {
			for(int i = 1; i < 10; i++) {
				str += dan + " * " + i + " = " + idan * i;
				str += "<br>";
			}
		}else {
			str += "<script>alert('단수를 알 수 없습니다. \\n 다시 입력해주세요')</script>";
		}
		
		request.setAttribute("dan", idan);
		request.setAttribute("result", str);
		request.setAttribute("flag", flag);
		view(request, response);
	}

	public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = (String)request.getAttribute("result");
		boolean flag = (boolean)request.getAttribute("flag");
		int dan = (int)request.getAttribute("dan");
		
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");	
		out.print("<head>");
		out.print("<title>" + dan + "단</title>");
		out.print("</head>");
		out.print("<body>");
		if(flag) out.print("<h1>" + dan + "단 입니다.</h1>");
		out.print(result);
		out.print("</body>");
		out.print("</html>");
	}

}
