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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/**
 * Servlet implementation class ProdControllerSW
 */
@WebServlet("/ProdControllerSW.do")
public class ProdControllerSW extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProdService service = ProdServiceImpl.getInstance();
   
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ProdControllerSW() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("lgu");
		
		List<ProdVO> list = service.selectByLgu(parameter);
		
		JsonObject obj = new JsonObject();
		if(list != null && list.size() > 0) {
			obj.addProperty("sw", "ok");
			
			Gson gson = new Gson();
			JsonElement jele = gson.toJsonTree(list);
			obj.add("datas", jele);
		}else {
			obj.addProperty("sw", "no");
		}
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(obj);
		out.flush();
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
	}

}
