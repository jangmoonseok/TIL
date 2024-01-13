package jsp.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		boolean remember = req.getParameter("remember") != null;
		
		boolean isValidUser = (userName != null && !userName.equals("")) && (password != null && !password.equals(""));
		
		if(isValidUser) {
			Cookie cookie = new Cookie("user", userName);
			if(remember) {
				//쿠키 만료시간 1분 설정
				cookie.setMaxAge(60);
			}else {
				cookie.setMaxAge(-1);
			}
			
			//response에 쿠키를 저장
			res.addCookie(cookie);
			res.sendRedirect("/Java_JSP/welcome.jsp");
		}else {
			res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<h3>Invalid login. Please try again.</h3>");
		}
	}

	
}
