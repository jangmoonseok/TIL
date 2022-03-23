package kr.or.ddit.jdbcboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.jdbcboard.service.BoardServiceImpl;
import kr.or.ddit.jdbcboard.service.IBoardService;
import kr.or.ddit.jdbcboard.vo.BoardVO;

public class BoardController {
	private Scanner sc = new Scanner(System.in);
	private IBoardService service;
	
	public BoardController() {
		service = BoardServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new BoardController().start();
	}

	private void start() {
		while(true) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.printf("%-5s %-15s %-10s %-5s", "No", "제목", "작성자", "조회수");
			System.out.println();
			System.out.println("-----------------------------------------------------------");
			
			List<BoardVO> readAll = service.readAll();
			
			if(readAll == null) {
				System.out.println("등록된 게시글이 없습니다.");
			}else {
				for(BoardVO board : readAll) {
					System.out.printf("%-5s %-15s%-10s %-5s", board.getNo(), board.getTitle(), 
							board.getWriter(), board.getCnt());
					System.out.println();
				}
			}
			
			displayMenu();
		}
	}

	private void displayMenu() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("메뉴 : 1.새글작성  2.게시글보기  3.검색  0.작업끝");
		int input = sc.nextInt();
		
		switch (input) {
		case 1:
			writeBoard();
			break;
		case 2:
			readBoard();
			break;
		case 3:
			findBoard();
			break;
		case 0:
			System.out.println("프로그램을 종료합니다..");
			System.exit(0);
		default:
			System.out.println("메뉴를 선택하세요.");
		}
	}

	private void readBoard() {
		System.out.print("읽을 게시물 번호 입력 : ");
		int num = sc.nextInt();
		
		BoardVO boardVo = service.readBoard(num);
		if(boardVo == null) {
			System.out.println("해당하는 번호의 게시물이 없습니다.");
		}else {
			System.out.println(num + "번 게시글");
			System.out.println("-----------------------------------------------------------");
			System.out.println("- 제목 : " + boardVo.getTitle());
			System.out.println("- 작성자 : " + boardVo.getWriter());
			System.out.println("- 내용 : " + boardVo.getContent());
			System.out.println("- 작성일 : " + boardVo.getDate());
			System.out.println("- 조회수 : " + boardVo.getCnt());
			System.out.println("-----------------------------------------------------------");
			System.out.println("메뉴 : 1.수정  2.삭제 3.목록으로");
			
			int input = sc.nextInt();
			
			switch (input) {
			case 1:
				updateBoard(boardVo, num);
				break;
			case 2:
				deleteBoard(num);
				break;
			case 3:
				break;
			default:
				System.out.println("메뉴를 선택하세요");
			}
		}
		
		
	}

	private void deleteBoard(int num) {
		int result = service.deleteBoard(num);
		
		if(result > 0) {
			System.out.println(num + "번 글이 삭제되었습니다.");
		}else {
			System.out.println("삭제 실패..");
		}
	}

	private void updateBoard(BoardVO boardVo, int num) {
		sc.nextLine();
		System.out.println("수정 작업");
		System.out.println("---------------------------");
		System.out.print("- 수정할 제목 : ");
		String title = sc.nextLine();
		System.out.print("- 수정할 내용 : ");
		String content = sc.nextLine();
		
		boardVo.setTitle(title);
		boardVo.setContent(content);
		
		int result = service.updateBoard(boardVo, num);
		
		if(result > 0) {
			System.out.println(num + "번 게시글 수정완료");
		}else {
			System.out.println("게시글 수정 실패...");
		}
		
	}

	private void findBoard() {
		System.out.println("검색 작업");
		System.out.println("---------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		sc.nextLine();
		String text = sc.nextLine();
		
		List<BoardVO> findList = service.readFindBoard(text);
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("%-5s %-15s %-10s %-5s", "No", "제목", "작성자", "조회수");
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		
		if(findList == null || findList.size() == 0) {
			System.out.println("해당 게시글이 없습니다.");
		}else {
			for(BoardVO board : findList) {
				System.out.printf("%-5s %-15s%-10s %-5s", board.getNo(), board.getTitle(), 
						board.getWriter(), board.getCnt());
				System.out.println();
			}
		}
		
		displayMenu();
		
		
	}

	private void writeBoard() {
		System.out.println("새 글 작성하기");
		System.out.println("---------------------------");
		System.out.print("- 제목 : ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.print("- 작성자 : ");
		String writer = sc.next();
		System.out.print("- 내용 : ");
		sc.nextLine();
		String content = sc.nextLine();
		
		BoardVO boardVo = new BoardVO();
		boardVo.setTitle(title);
		boardVo.setWriter(writer);
		boardVo.setContent(content);
		
		int result = service.writeBoard(boardVo);
		
		if(result > 0) {
			System.out.println("새 글이 추가되었습니다.");
		}else {
			System.out.println("게시글 등록 실패...");
		}
	}

}
