package kr.or.ddlt.basic;

import javax.swing.JOptionPane;

/*
 * 	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고, 사용자의 가위 바위 보는 showInputDialog()를 이용해서 입력받는다.
 * 
 * 	입력 시간은 5초로 제한하고 카운트 다운을 진행한다. 5초안에 입력이 없으면 게임에 진 것으로 처리한다.
 *  5초안에 입력이 있으면 승,패를 구해서 출력한다.
 *  
 *  결과 예시)
 *  1) 5초안에 입력이 없을때
 *   - 결과 - 
 *   시간 초과로 당신이 졌습니다.
 *  2) 입력이 있을때
 *   - 결과 -
 *   컴퓨터 : 가위
 *   당신 : 바위
 *   결과 : 당신이 이겼습니다.
 */
public class ThreadTest07 {
	public static boolean check = false;
	
	public static void main(String[] args) {
		Count cnt = new Count();
		cnt.start();
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3);
		String com = data[index];
		String input = JOptionPane.showInputDialog("가위 바위 보 중 하나를 입력하세요.");
		check = true;
		String result = "";
		switch(input) {
			case "가위":
				result = index == 0 ? "비겼습니다." : index == 1 ? "당신이 졌습니다." : "당신이 이겼습니다.";
				break;
			case "바위":
				result = index == 0 ? "당신이 이겼습니다." : index == 1 ? "비겼습니다." : "당신이 졌습니다.";
				break;
			case "보":
				result = index == 0 ? "당신이 졌습니다" : index == 1 ? "당신이 이겼습니다." : "비겼습니다.";
				break;
			default:
				result = "제대로 입력하지 않았습니다.";
		}
		System.out.println("컴퓨터 : " + com);
		System.out.println("당 신 : " + input);
		System.out.println("결과 : " + result);
	}

}


class Count extends Thread{
	@Override
	public void run() {
		for(int i = 5; i >= 0; i--) {
			if(ThreadTest07.check) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
}












