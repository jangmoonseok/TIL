package kr.or.ddlt.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		DataInput input = new DataInput();
		CountDown cnt = new CountDown();
		input.start();
		cnt.start();
	}

}

//데이터를 입력하는 쓰레드
class DataInput extends Thread{
	public static boolean inputCheck = false; // 입력 여부를 확인하기 위한 변수 선언, 쓰레드에서 공통으로 사용할 변수
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		inputCheck = true; // 입력이 완료되면 inputCheck값을 true로 변경
		System.out.println("입력한 값 : " + str);
	}
	
}

//카운트 다운을 진행하는 쓰레드
class CountDown extends Thread{
	@Override
	public void run() {
		for(int i = 10; i >= 0; i--) {
			// 입력이 완료되었는지 여부를 검사해서 완료되었으면 쓰레드를 종료
			if(DataInput.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}