package kr.or.ddlt.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티쓰레드 프로그램
		
		// 1) Thread클래스를 사용하는 방법
		MyThread1 th1 = new MyThread1();
		th1.start();
		System.out.println();
		
		// 2) Runnable인터페이스를 구현해서 사용하는 방법
		// ==> Runnable인터페이스를 구현한 class를 작성하고 이 class의 인스턴스를 생성한다. 그리고 Thread객체를 생성할 때 Thread의 생성자에 Runnable인터페이스 인스턴스값을 넣어서
		// 	   생성한 후 이 Thread객체의 start()메서드를 호출
		Runnable r1 = new MyThread2();
		Thread th2 = new Thread(r1);
		th2.start();
		
		// 3) Runnable인터페이스를 익명구현체로 구현하여 사용하는 방법
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for(int i = 0 ; i < 200; i++) {
					System.out.print("@");
				}
			}
		};
		
		Thread th3 = new Thread(r2);
		th3.start();
	}

}


class MyThread1 extends Thread{

	@Override
	public void run() {
		// 이 run()메서드에는 이 쓰레드가 해야할 일을 코딩하면 된다.
		for(int i = 0; i < 200; i++) {
			System.out.print("*");
			try {
				//Thread.sleep(시간) ==> 주어진 시간(ms)동안 작업을 잠시 멈춘다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
}

class MyThread2 implements Runnable{

	@Override
	public void run() {
		for(int i = 0; i < 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
}
