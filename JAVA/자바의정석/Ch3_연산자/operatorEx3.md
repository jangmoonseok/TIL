```java
package ch3;

public class OperatorEx3 {
	public static void main(String[] args) {
		//사칙 연산에서의 형 변환
		System.out.println(10 / 4); // 2.5가 나와야하지만 int끼리 연산한 결과는 int가 나와야하므로 소숫점은 버려져 2가 나온다
		System.out.println(10 / 4.0f); // int타입인 10이 실수인 4.0f와 연산하기 위해 값 손실이 없도록 10.0으로 형 변환되어 계산된다.
		
		/*
		 * 산술 변환 : 연산 전에 피연산자의 타입을 일치시키는 것
		 * - 두 피연산자의 타입을 같게 일치시킨다(보다 큰 타입으로 일치) > ex) long(8byte) + int(4byte) -> long + long = long
		 * - 피연산자의 타입이 int보다 작은 타입이면 int로 변환된다. > ex) byte(1byte) + short(2byte) -> int(4byte) + int(4byte) = int
		 */
		
		System.out.println('2' - '0'); // 2 >> ch - ch => int - int 이므로 '2'을 int로 형변환한 50과 '0'을 형변환한 28을 뺀 2가 나온다
		System.out.println('2' - 0); // 50 >> ch - int = int - int 이므로 '2'를 형변환한 50과 숫자0을 뺀 50이 나온다
		
		int a = 1_000_000;
		int b = 2_000_000;
		long c = a * b;
		System.out.println(c); // -1454759936 >> a * b과정에서 결과값이 int타입의 범위인 20억을 넘어서서 오버플로우가 발생
		c = (long)a * b;
		System.out.println(c); // 2000000000000 >> a와 b 둘중 하나를 long으로 형변환 한 상태에서 계산해야 올바른 값이 나온다
		

	}
}
```
