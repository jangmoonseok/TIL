package kr.or.ddlt.basic;

/*
 * 	쓰레드에서 객체를 공통으로 사용하는 예제
 * 
 * 	원주율을 계산하는 쓰레드와 계산된 원주율을 출력하는 쓰레드가 있다.
 * 
 * 	원주율을 저장하는 객체가 필요하다. 이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다.
 */
public class ThreadTest14 {

	public static void main(String[] args) {
		// 공통으로 사용할 객체 생성
		ShareData sd = new ShareData();
		
		// 쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 넣는다.
		CalcPIThread ct = new CalcPIThread();
		ct.setSd(sd);
		
		PrintPIThread pt = new PrintPIThread(sd);
		
		ct.start();
		pt.start();
		
	}

}

// 원주율을 관리하는 클래스(공통으로 사용 할 클래스)
class ShareData{
	private double result; // 계산된 원주율이 저장 될 변수
	
	private volatile boolean isOK; // 계산이 완료되었는지 여부를 나타내는 변수
	// volatile ==> CPU의 각 코어에 캐시가 있는데 이 캐시를 사용하지 않고 직접 메모리에서 데이터 값을 입출력한다.

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}
}

// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;
	
	public void setSd(ShareData sd) {
		this.sd = sd;	
	}
	
	@Override
	public void run() {
		/*
		 * 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + ...) * 4
		 * 분모를 2로 나눈 몫을 2로 나누었을때 짝수이면 + 홀수이면 -
		 */
		double sum = 0.0;
		for(int i = 1; i <= 1_000_000_000; i += 2) {
			if((i / 2) % 2 == 0) {
				sum += (1.0 / i);
			}else {
				sum -= (1.0 / i);
			}
		}
		
		// 계산이 완료된 값을 공통으로 사용할 객체에 저장한다.
		sd.setResult(sum * 4);
		sd.setOK(true);
	}
}

// 계산이 완료되면 계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;

	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOK()) {
				break;
			}
		}
		System.out.println();
		System.out.println("결과 : " + sd.getResult());
		System.out.println("PI : " + Math.PI);
	}
	
}









