package clt.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedCollectionExample1 {
	
	
	
	public static void main(String[] args) {
		
		List<String> list = Collections.synchronizedList(new ArrayList<>());
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i < 2; i++) {
			threadPool.execute(new Runnable() {
				
				@Override
				public void run() {
						/*
						 * 다중 연산을 블럭으로 synchronized블럭으로 동기화를 해주어야한다.
						 * 2개의 쓰레드가 순서를 보장하지 않고 동시에 실행하기 때문에 
						 * 만약 A쓰레드에서 clear()한 순간 B쓰레드에서 remove()를 호출하면 IndexOutOfBoundsException 발생
						 */
						for(int i = 0; i < 100; i++) {	
							synchronized (list) {
								list.clear();
								System.out.println(this.toString() + " clear complete");
								list.add("A");
								System.out.println(this.toString() + " add complete");
								list.remove(0);
								System.out.println(this.toString() + " remove complete");
							}
						}
					}
			});
		}
	
	}
}
