package kr.or.ddit.prod.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;


/**
 * Servlet implementation class ProdController
 */
@WebServlet("/ProdController.do")
public class ProdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProdService service = ProdServiceImpl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("lgu");
		
		List<ProdVO> list = service.selectByLgu(parameter);
		
		Gson gson = new Gson();
		String result = gson.toJson(list);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
		
//		request.setAttribute("selectByLgu", list);
//		
//		request.getRequestDispatcher("0330/prodList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("id");
		
		ProdVO vo = service.selectById(parameter);
		
		Gson gson = new Gson();
		String result = gson.toJson(vo);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(result);
		writer.flush();
//		request.setAttribute("selectById", vo);
//		
//		request.getRequestDispatcher("0330/prodDetail.jsp").forward(request, response);
	}

}
