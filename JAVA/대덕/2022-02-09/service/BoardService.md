```java
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.BoardDao;
import dao.MemberDao;
import util.JDBCUtil;
import util.ScanUtil;
import util.View;

public class BoardService {

	private BoardService() { // 다른 클래스에서 객체생성을 막기 위해 생성자에 private를 붙인다
		
	}
	private static BoardService instance; // 객체를 만들어서 보관 할 변수
	public static BoardService getInstance() { // 다른 클래스에서 객체가 필요할 때 호출 할 메서드
		if(instance == null) {
			// 객체가 없으면 객체 생성
			instance = new BoardService();
		}
		return instance;
	}
	
	private BoardDao boardDao = BoardDao.getInstance();
	
	public int boardList() {
		List<Map<String, Object>> boardList = boardDao.selectBoardList();
		
		System.out.println("===========================================");
		System.out.println("번호\t제목\t작성자\t작성일");
		System.out.println("-------------------------------------------");
		for(Map<String, Object> board : boardList) {
			System.out.println(board.get("BOARD_NO")
					+ "\t" + board.get("TITLE")
					+ "\t" + board.get("MEM_NAME")
					+ "\t" + board.get("REG_DATE"));
		}
		System.out.println("===========================================");
		System.out.println("1.조회  2.등록  0.로그아웃");
		
		int input = ScanUtil.nextInt();
		
		switch(input) {
		case 1: return View.BOARD_READ;
		case 2: return View.BOARD_INSERT;
		case 0:
			MemberService.loginMember = null;
			return View.HOME;
		}
		return View.BOARD_LIST;
	}

	public int board() {
		System.out.println("조회할 게시글 번호를 입력해주세요 >");
		int boardNo = ScanUtil.nextInt();
		
		Map<String, Object> board = boardDao.selectboard(boardNo);
		
		if(board == null) {
			System.out.println("해당 번호의 게시글이 존재하지 않습니다.");
		}else {			
			System.out.println("================================================");
			System.out.println("번호\t:" + board.get("BOARD_NO"));
			System.out.println("------------------------------------------------");
			System.out.println("작성자\t:" + board.get("MEM_NAME"));
			System.out.println("------------------------------------------------");
			System.out.println("작성일\t:" + board.get("REG_DATE"));
			System.out.println("------------------------------------------------");
			System.out.println("제목\t:" + board.get("TITLE"));
			System.out.println("------------------------------------------------");
			System.out.println("내용\t:" + board.get("CONTENT"));
			System.out.println("================================================");
			
			if(MemberService.loginMember.get("MEM_NAME").equals(board.get("MEM_NAME")) ){				
				System.out.println("1.수정  2.삭제  0.목록 > ");
				int input = ScanUtil.nextInt();
				
				switch(input) {
				case 1:
					update(board.get("BOARD_NO")); 
					break;
				case 2:
					delete(board.get("BOARD_NO"));	
					break;
				case 0:
					
					break;
				}
			}else {
				System.out.println("0.목록으로 > ");
				int input = ScanUtil.nextInt();
				
				switch(input) {
				case 0:
					break;
				
				}
			}
		}
		return View.BOARD_LIST;
	}

	public int update(Object boardNo) {
		System.out.println("수정할 제목 >");
		String title = ScanUtil.nextLine();
		System.out.println("수정할 내용 >");
		String content = ScanUtil.nextLine();

		
		int result = boardDao.updateBoard(title, content, boardNo);
		
		if(result > 0) {
			System.out.println("게시글이 수정되었습니다.");
		}else {
			System.out.println("게시글 수정을 실패했습니다.");
		}
		return View.BOARD_LIST;
	}

	public int delete(Object boardNo) {
		System.out.println("정말 삭제하시겠습니까? Y/N");
		String input = ScanUtil.nextLine();
		if(input.equals("Y")) {
			int result = boardDao.deleteBoard(boardNo);
			
			if(result > 0) {
				System.out.println("게시글 삭제 성공");
			}else {
				System.out.println("게시글 삭제 실패");
			}
		}
		return View.BOARD_LIST;
	}

	public int insert() {
		System.out.println("제목 > ");
		String title = ScanUtil.nextLine();
		System.out.println("내용 > ");
		String content = ScanUtil.nextLine();
		Object memId = MemberService.loginMember.get("MEM_ID");
		
		int result = boardDao.insertBoard(title,content,memId);
		
		if(result > 0) {
			System.out.println("게시글 등록 성공했습니다.");
		}else {
			System.out.println("게시글 등록을 실패했습니다.");
		}
		return View.BOARD_LIST;
	}
	
}
