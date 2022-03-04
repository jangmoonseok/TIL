package kr.or.ddlt.basic;

public class ThreadTest04 {
	/*
	 * 	1~20억까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 때와 여러개의 쓰레드가 협력해서 처리할 때의 경과시간을 비교해 보자.
	 */
	public static void main(String[] args) {
		//단독으로 처리하기
		SumThread smth = new SumThread(1L,2_000_000_000L);
		smth.start();
		long startTime = System.currentTimeMillis();
		try {
			smth.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독경과시간 : " + (endTime - startTime));
		System.out.println("---------------------------------------");
		
		// 여러개의 쓰레드가 협력해서 처리하기
		SumThread[] smths = new SumThread[] {
				new SumThread(            1L,   500_000_000L),
				new SumThread(  500_000_001L, 1_000_000_000L),
				new SumThread(1_000_000_001L, 1_500_000_000L),
				new SumThread(1_500_000_001L, 2_000_000_000L)
		};
		startTime = System.currentTimeMillis();
		for(SumThread th : smths) {
			th.start();
		}
		for(SumThread th : smths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		endTime = System.currentTimeMillis();
		System.out.println("여러개 경과시간 : " + (endTime - startTime));
	}

}

// 합계를 구하는 쓰레드 클래스 작성
class SumThread extends Thread{
	private long min, max;

	public SumThread(long min, long max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = min; i <= max; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	

}