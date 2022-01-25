```java
package ch3;

public class OperatorEx4 {
	public static void main(String[] args) {
		/*
		 * 반올림 - Math.round()
		 * 실수를 소수점 첫 째 자리에서 반올림한 정수를 반환
		 */
		long result = Math.round(4.52);
		System.out.println(result); // 5 >> 소수점 첫 째 자리5에서 반올림
		
		double pi = 3.141952;
		double shortPi = Math.round(pi * 1000) / 1000.0;
		System.out.println(shortPi); // 3.142
		/*
		 * Math.round(3.141592 * 1000) / 1000.0
		 * >> Math.round(3141.592) / 1000.0
		 * >> 3142 / 1000.0 : 3142 / 1000은 int / int 이므로 결과도 int인 3이 나오기 때문에 1000.0으로 나누어야 한다. 
		 * >> 3.142
		 */
		
		/*
		 * 나머지 연산자 %
		 * - 나누는 피연산자는 0이 아닌 정수만 허용(부호는 무시됨)
		 */
		int x =10, y = 8;
		System.out.println(x % y); // 2
		System.out.println(x % -y); // 2 >> 나머지 연산자는 부호를 무시하므로 위 결과와 같다
		
		/*
		 * 문자열 비교
		 * - 문자열 비교에는 == 대신 equals()를 사용해야 한다.
		 */
		
		String str1 = "abc";
		String str2 = "abc";
		System.out.println(str1 == str2); //true
		System.out.println(str1.equals(str2)); //true
		
		str1 = new String("abc");
		str2 = new String("abc");
		System.out.println(str1 == str2); //false
		System.out.println(str1.equals(str2)); //true
		
		/*
		 * 조건 연산자 ? :
		 * - 조건식의 결과에 따라 연산결과를 달리한다.
		 * - 간단한 if문을 가독성 좋게 쓰기 위해 사용한다.
		 * result = (x > y) ? (참일경우 반환값) : (거짓일경우 반환값)
		 */
	}
}
```
