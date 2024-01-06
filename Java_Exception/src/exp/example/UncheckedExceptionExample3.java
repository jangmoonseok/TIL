package exp.example;

public class UncheckedExceptionExample3 {
	public static void main(String[] args) {
		UncheckedExceptionClass3 class3 = new UncheckedExceptionClass3();

		class3.catchThrow(13); //예외가 발생하지 않고 정상실행
		class3.notCatchThrow(13); //예외 발생
	}
}

class UncheckedExceptionClass3{
	/*
	 * 1년을 12월까지이기 때문에 12보다 큰 숫자가 들어오면 예외를 던진다.
	 * try-catch문 안에서 throw로 예외를 발생시키면 해당 예외와 동일하거나 상속관계에 있는 예외가 있다면
	 * catch블록에서 예외를 잡는다.
	 * 즉, try-catch문 밖에 로직에 영향을 주지 않는다.
	 */
	void catchThrow(int num){
		try {
			if(num > 12) {
				throw new RuntimeException(num + "월은 존재하지 않는 달 입니다.");
			}
			
			System.out.println(num + "월의 데이터를 처리하겠습니다.");
		} catch (RuntimeException e) {
//			e.printStackTrace();
		}
		
		System.out.println(num);
	}
	
	/*
	 * RuntimeException은 throws 키워드가 생략가능
	 * 따라서 예외가 발생하면 자동으로 상위 메소드로 예외를 던진다.
	 * 이후 로직은 실행되지 않는다. 
	 */
	void notCatchThrow(int num) throws RuntimeException{
		if(num > 12) {
			throw new RuntimeException(num + "월은 존재하지 않는 달 입니다.");
		}
		System.out.println(num + "월의 데이터를 처리하겠습니다.");
	}
}