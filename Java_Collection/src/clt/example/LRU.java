package clt.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class LRU {

	static LinkedList<Page> cache = new LinkedList<>();
	static HashSet<Page> hashSet = new HashSet<>();
	static int cacheSize = 5;

	static class Page{
		private int id;
		private String content;
		
		public Page() {}
		
		public Page(int id, String content) {
			super();
			this.id = id;
			this.content = content;
		}

		public int getId() {
			return id;
		}
		
		
		
		
	}
	
	static void put(Page page) {
		if(!hashSet.contains(page)) {
			//처음 방문하는 페이지인 경우
			if(cacheSize == cache.size()) {
				//캐시가 꽉 찼으면 방문한지 가장 오래된 페이지를 삭제
				Page last = cache.removeLast();
				//해당 페이지를 방문기록에서 제거
				hashSet.remove(last);
			}
		}else {
			//방문했던 페이지라면 캐시에서 해당 페이지를 삭제
			cache.remove(page);
		}
		//방문기록에 페이지 추가
		hashSet.add(page);
		//캐시에 페이지 추가
		cache.push(page);
	}
	
	static void print() {
		Iterator<Page> iterator = cache.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getId());
		}
	}
	
	public static void main(String[] args) {
		
		Page page1 = new Page(1,"1");
		Page page2 = new Page(2,"1");
		Page page3 = new Page(3,"1");
		Page page4 = new Page(4,"1");
		Page page5 = new Page(5,"1");
		Page page6 = new Page(6,"1");
		
		put(page1); //1
		put(page2); //2 1
		put(page3); //3 2 1
		put(page5); //5 3 2 1
		put(page4); //4 5 3 2 1
		put(page2); //2 4 5 3 1
		put(page3); //3 2 4 5 1
		put(page1); //1 3 2 4 5
		put(page2); //2 1 3 4 5
		put(page6); //6 2 1 3 4
		print();
		
		
	}
}
