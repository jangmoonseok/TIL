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
						 * ���� ������ ������ synchronized������ ����ȭ�� ���־���Ѵ�.
						 * 2���� �����尡 ������ �������� �ʰ� ���ÿ� �����ϱ� ������ 
						 * ���� A�����忡�� clear()�� ���� B�����忡�� remove()�� ȣ���ϸ� IndexOutOfBoundsException �߻�
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
