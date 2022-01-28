```java
package j_collection;

import java.util.*;

import e_oop.ScanUtil;

public class Board {

	public static void main(String[] args) {
		/*
		 * ArrayList와 HashMap을 사용해 게시판 테이블을 만들고,
		 * 조회, 등록, 수정, 삭제가 가능한 게시판을 만들어주세요.
		 * 
		 * - 번호, 제목, 내용, 작성자, 작성일
		 * 목록 - 조회(상세내용), 등록
		 * 조회 - 상세내용,수정,삭제
		 */
		ArrayList<HashMap<String,Object>> board = new ArrayList<HashMap<String,Object>>();
		
		int number = 1;
		while(true) {			
			System.out.println("1.상세보기\t2.글쓰기\t3.글목록\t4.종료");
			int input = ScanUtil.nextInt();
			switch (input) {
			case 1: 
				viewPost(board);
				break;
			case 2:
				board.add(write(number));
				number++;
				break;
			case 3:
				System.out.println("================================= 게시판 =================================");
				System.out.println("번호\t제목\t\t\t작성자\t작성일");
				for(int i = 0; i < board.size(); i++) {
					HashMap<String, Object> post = board.get(i);
					System.out.print(post.get("번호") + "\t" + post.get("제목") + "\t\t\t" + post.get("작성자") + "\t" + post.get("작성일"));
					System.out.println();
				}
				System.out.println("========================================================================");
				break;
			case 4:
				System.exit(0);
				
			};
		}

		
	}

	static void viewPost(ArrayList<HashMap<String, Object>> board) {
		System.out.println("조회할 글 번호를 입력해주세요 >");
		int number = ScanUtil.nextInt();
		System.out.println("========================================================================");
		System.out.println("제목 : " + board.get(number - 1).get("제목"));;
		System.out.println("내용 : " + board.get(number - 1).get("내용"));;
		System.out.println("========================================================================");
		System.out.println("1.수정\t2.삭제");
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			System.out.println("수정할 제목 > ");
			String title = ScanUtil.nextLine();
			System.out.println("수정할 내용");
			String content = ScanUtil.nextLine();
			board.get(number - 1).put("제목", title);
			board.get(number - 1).put("내용", content);		
			break;
		case 2:
			board.remove(number - 1); 
			break;
		default:
			break;
		}
	}

	static HashMap<String, Object> write(int number) {
		HashMap<String,Object> post = new HashMap<String, Object>();
		post.put("번호", number);
		System.out.println("제목을 입력하세요 >");
		String title = ScanUtil.nextLine();
		post.put("제목", title);
		System.out.println("내용을 작성하세요 >");
		String content = ScanUtil.nextLine();
		post.put("내용", content);
		System.out.println("작성자를 입력하세요 >");
		String writer = ScanUtil.nextLine();
		post.put("작성자", writer);
		post.put("작성일", new Date());
		return post;
	}
	
}
```
