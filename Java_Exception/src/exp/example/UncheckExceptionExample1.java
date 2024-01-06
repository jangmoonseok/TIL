package exp.example;

public class UncheckExceptionExample1 {
	public static void main(String[] args) {
		UncheckedExceptionClass class1 = new UncheckedExceptionClass();
		class1.runDivideIntegerBy0();
		System.out.println("runDivideIntegerBy0 메소드에서 오류가 발생했습니다.");
	}
}

class UncheckedExceptionClass{
	
	/**
	 * divideIntegerBy0 메서드를 호출하기 때문에 ArithmeticException이 발생한다.
	 * 이 메소드도 역시 아무런 처리를 하지 않고 상위 메소드로 예외를 던진다.
	 */
	void runDivideIntegerBy0() {
		divideIntegerBy0(2);
	}
	
	/**
	 * 정수를 0으로 나누게 되면 ArithmeticException이 발생한다.
	 * 예외를 처리하지도 않고 throws키워드를 사용하지도 않았지만 자동으로 상위 메소드로 예외를 던진다.
	 */
	private int divideIntegerBy0(int num){
		return num / 0;
	}
}