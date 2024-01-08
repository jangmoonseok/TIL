package gnr.example;

import java.util.List;

public class GenericExample4 {

	public static void main(String[] args) {
		
	}
}

class Board{
	private String title;
	private String content;
	
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

	public Recipe() {
		super();
	}
	
}

class Diary extends Board{

	public Diary() {
		super();
	}
	
}

class BoardMapper{
	
//	<T extends Board> List<T> select(){
//		
//	};
//	
//	<T extends Board> void insert(List<T> list, T e);
}

