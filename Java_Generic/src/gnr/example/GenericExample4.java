package gnr.example;

import java.util.ArrayList;
import java.util.List;

public class GenericExample4 {

	public static void main(String[] args) {
		BoardMapper<Board> boardMapper = new BoardMapper<>();
		
		Recipe recipe = new Recipe("볶음밥 레시피", "내용....");
		Diary diary = new Diary("오늘의 일기", "내용...");
		
		List<Board> list = boardMapper.selectBoard();
		
		list.add(recipe);
		list.add(diary);
		
		System.out.println(list.toString());
	}
}

class Board{
	private String title;
	private String content;
	
	public Board(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

class Recipe extends Board{

	public Recipe(String title, String content) {
		super(title, content);
	}
	
}

class Diary extends Board{

	public Diary(String title, String content) {
		super(title, content);
	}
	
}

class BoardMapper<T extends Board>{
	private List<T> list;
	
	
	public BoardMapper() {
		list = new ArrayList<>();
	}

	<T extends Board> List<T> selectBoard(){
		// ..게시판 조회
		return null;
	};
	
	<T extends Board>void insertBoard(T e) {
		// ..게시판 등록
	};
	
	List<Recipe> selectRecipe(){
		// ..요리 레시피 조회
		return null;
	}
	
	List<Diary> selectDiary(){
		// ..다이어리 조회
		return null;
	}
	
	void insertRecipe(Recipe recipe) {
		// ..요리 레시피 등록
	}
	
	void insertDiary(Diary diary) {
		// ..다이어리 등록
	}
}

