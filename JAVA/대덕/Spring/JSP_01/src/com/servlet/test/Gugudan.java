package com.servlet.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Gugudan
 */
@WebServlet("/Gugudan")
public class Gugudan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		//입력
		int dan = 2;
		String result = "";
		
		//처리
		for(;dan < 10; dan++) {
			result += "<h1>" + dan + "단 입니다.</h1>";
			for(int i = 1; i < 10; i++) {
				result += dan + " * " + i + " = " + (dan * i) + "<br>";
			}
			result += "<br>";
		}
		
		//출력
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("/gugudan.jsp").forward(request, response);
	}


}
