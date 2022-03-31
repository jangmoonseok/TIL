package kr.or.ddit.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.ZipVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;


/**
 * Servlet implementation class ZipSearch
 */
@WebServlet("/ZipSearch.do")
public class ZipSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dong = request.getParameter("dong");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		List<ZipVO> zipList = service.zipList(dong);
		
		Gson gson = new Gson();
		String result = gson.toJson(zipList);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		writer.print(result);
		writer.flush();
		
	}

}
