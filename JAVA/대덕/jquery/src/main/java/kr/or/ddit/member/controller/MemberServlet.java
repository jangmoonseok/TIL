package kr.or.ddit.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

import java.io.IOException;
import java.util.List;


/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 요청시 데이터 받기
		
		// service 객체 얻기
		IMemberService service = MemberServiceImpl.getInstance();
		
		// service 메소드 호출 - 결과를 얻는다
		List<MemberVO> list = service.selectAll();
		
		// 결과를 가지고 출력 또는 응답데이터를 생성
		// view페이지로 이동
		// view페이지와 결과값을 공유하기 위해서 request에 저장
		req.setAttribute("selectAll", list);
		
		// view페이지로 forward
		req.getRequestDispatcher("0329/memberList.jsp").forward(req, res);
	}

}
