package kr.or.ddlt.basic;

public class ThreadTest08 {
	/*
	 * 데몬 쓰레드 연습 ==> 자동 저장하기
	 */
	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		autoSave.setDaemon(true); // 데몬 쓰레드로 설정하기 ==> 반드시 start()를 호출하기 전에 설정
		autoSave.start();
		
		try {
			for(int i = 1; i <= 20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
			System.out.println("main 쓰레드 종료..");
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}

class AutoSaveThread extends Thread{
	// 작업 내용을 저장하는 메서드
	public void save() {
		System.out.println("작업 내용을 저장합니다..");
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			save();
		}
	}
	
	
}