package kr.or.ddlt.basic;

public class ThreadTest15 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("test1", sObj);
		TestThread th2 = new TestThread("test2", sObj);
		th1.start();
		th2.start();
	}

}

// 공통으로 사용할 클래스
class ShareObject{
	private int sum = 0;
	
	/*
	 - 동기화처리 ==> synchronized를 이용해서 처리한다.
	 - 방법1) 메서드 자체에 동기화처리
	 	public synchronized void add(){
	 		처리할 내용
	 	}
	 - 방법2) 동기화 블럭으로 처리하는 방법
	 	public void add(){
	 		synchronized(동기화 할 객체){
	 		처리할 내용
	 		}
	 	}
	*/
//	public synchronized void add() {
	public void add() {
		synchronized(this) {			
			int n = sum;
			
			n += 10;
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}

class TestThread extends Thread{
	private ShareObject sObj;
	
	public TestThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			sObj.add();
		}
	}
}