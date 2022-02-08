```java
package ch7;

public class ch7_31_AbstractClass {

	public static void main(String[] args) {
		/*
		 * 추상 클래스(Abstract class)
		 * - 미완성 설계도. 미완성 메서드를 갖고 있는 클래스
		 * - 다른 클래스 작성에 도움을 주기 위한 것. 인스턴스 생성 불가
		 * - 상속을 통해 추상 메서드를 완성해야 인스턴스 생성가능
		 * 
		 * 추상 메서드
		 * - 미완성 메서드. 구현부(몸통,{})이 없는 메서드
		 * - 꼭 필요하지만 자손마다 다르게 구현될 것으로 예상되는 경우에 사용
		 */
//		Player p = new Player(); //에러. 미완성 클래스
		Player ap = new AudioPlayer(); // 완성된 클래스
		ap.play(100);
		ap.stop();
	}
}

abstract class Player{ // 추상클래스. 추상메서드를 갖고 있는 클래스
	//추상메서드. 몸통{}이 없는 미완성 메서드
	abstract void play(int pos);
	abstract void stop();
}

class AudioPlayer extends Player{

	@Override
	void play(int pos) {
		System.out.println(pos + "위치부터 play합니다.");
	}

	@Override
	void stop() {
		System.out.println("재생을 멈춥니다.");
	}
	
}
