package gnr.example;

import java.util.ArrayList;
import java.util.List;

public class GenericExample4 {

	public static void main(String[] args) {
		BoardMapper<Board> boardMapper = new BoardMapper<>();
		
		Recipe recipe = new Recipe("������ ������", "����....");
		Diary diary = new Diary("������ �ϱ�", "����...");
		
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
		// ..�Խ��� ��ȸ
		return null;
	};
	
	<T extends Board>void insertBoard(T e) {
		// ..�Խ��� ���
	};
	
	List<Recipe> selectRecipe(){
		// ..�丮 ������ ��ȸ
		return null;
	}
	
	List<Diary> selectDiary(){
		// ..���̾ ��ȸ
		return null;
	}
	
	void insertRecipe(Recipe recipe) {
		// ..�丮 ������ ���
	}
	
	void insertDiary(Diary diary) {
		// ..���̾ ���
	}
}

