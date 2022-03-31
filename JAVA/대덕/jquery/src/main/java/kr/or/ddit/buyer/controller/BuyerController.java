package kr.or.ddit.buyer.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;

import java.io.IOException;
import java.util.List;


/**
 * Servlet implementation class BuyerController
 */
@WebServlet("/BuyerController.do")
public class BuyerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IBuyerService service = BuyerServiceImpl.getInstance();
		
		List<BuyerVO> list = service.selectByName();
		
		request.setAttribute("selectByName", list);
		
		request.getRequestDispatcher("0330/buyerList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bId = request.getParameter("id");	
			
		IBuyerService service = BuyerServiceImpl.getInstance();
		
		BuyerVO vo = service.idByDetail(bId);
		
		request.setAttribute("idByDetail", vo);
		
		request.getRequestDispatcher("0330/buyerDetail.jsp").forward(request, response);
	}

}
