package exp.unchecked;

public class UncheckedExceptionExample2 {
	public static void main(String[] args) {
		UncheckedExceptionClass2 class1 = new UncheckedExceptionClass2();
		int num = class1.run();
		System.out.println(num);
		
	}
}

class UncheckedExceptionClass2{
	int run() {
		int num = 0;
		/**
		 * 예외가 발생될 여지가 있는 지점에서 예외 처리
		 * 예외 처리를 직접 함으로써 상위 메소드의 로직 실행에 영향을 주지 않음 
		 */
		try {
			num = parseStringToInteger("hello");
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			num = 1;
		}
		
		return num;
		/**
		 * 예외 처리를 하지 않음
		 * 상위 메소드에 예외 처리 책임을 위임함으로써 상위 메소드가 예외 처리를 하지 않으면 다음 로직이 실행되지 않는다.
		 */
//		parseStringToInteger("hello");
	}
	
	// 문자열을 정수로 변환
	private int parseStringToInteger(String str) {
		int num = Integer.parseInt(str);
		return num;
	}
}