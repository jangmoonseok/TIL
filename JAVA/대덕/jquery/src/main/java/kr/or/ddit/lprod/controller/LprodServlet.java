package kr.or.ddit.lprod.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;
import kr.or.ddit.lprod.vo.LprodVO;

import java.io.IOException;
import java.util.List;


/**
 * Servlet implementation class LprodServlet
 */
@WebServlet("/LprodServlet.do")
public class LprodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LprodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILprodService service = LprodServiceImpl.getInstance();
		
		List<LprodVO> list = service.selectAll();
		
		request.setAttribute("selectAll", list);
		
		request.getRequestDispatcher("0329/lprodList.jsp").forward(request, response);
		
	}

}
