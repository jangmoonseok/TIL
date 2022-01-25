```java
package ch3;

public class OperatorEx2 {
	public static void main(String[] args) {
		/*
		 * 형변환 연산자
		 * - 변수 또는 상수의 타입을 다른 타입으로 변환하는 것
		 */
		
		
//		double d = 85.4;
//		int score = d;  int타입의 변수에 double타입의 변수 d를 저장할 수 없음
//		>> int score = (int)d; 형변환 연산자를 붙혀서 저장
//		>> int score = (int)85.4;
//		>> int score = 85
		
		char ch = 'A';
		int i = (int)ch;
		System.out.println(i); 
		//char타입 변수 ch에는 문자'A'를 유니코드 65로 바꾸어 저장 >> 그러므로 ch를 int로 형변환 하면 65가 출력
		
		float f = 1.6f;
		int i1 = (int)f;
		System.out.println(i1);
		// 정수는 소숫점이하 숫자를 저장할 수 없으므로 반올림하지않고 버린다 >> 그러므로 화면에 1 출력
		
		int i2 = 10;
		float f1 = i2;
		System.out.println(f1);
		// 10.0 >> float타입이 int타입보다 유효범위가 크기 때문에 형변환연산자를 붙이지 않아도 자동 형변환이 된다.
		
//		int i = 3.14f;  이 경우는 유효범위가 큰 값을 작은 값에 저장할 때에는 자동 형변환이 되지않아 수동으로 형변환 연산자를 써줘야한다.
		
		/*
		 * 자동 형변환
		 * 1.byte -> int : 형변환 연산자 생략가능
		 * 2.int -> byte : 생략불가
		 * 기존의 값을 최대한 보존할 수 있는 값 손실이 없는 타입으로 자동 형변환 된다.
		 */
		byte b = 100; // byte타입의 번위(-128~127)의 값 대입, 자동형변환
		int i = 100;
		byte b1 = i; 
		// 에러 : 위와 같이 byte타입에 100을 저장했지만 b변수에는 리터럴을 저장했고 b1변수에는 변수를 저장했기 때문에 형변환하여 대입해야한다
		

	}
}
```
