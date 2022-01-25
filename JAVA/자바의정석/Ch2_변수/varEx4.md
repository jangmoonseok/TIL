```java
package ch02;

public class VarEx5 {

	public static void main(String[] args) {
		/*
		 * 타입간의 변환방법
		 * 1.문자와 숫자간의 변환
		 * 2.문자열로의 변환
		 * 3.문자열을 숫자로 변환
		 */
		
		String str = "3";
		
		System.out.println(str.charAt(0)); // '3'
		System.out.println(str.charAt(0) - '0' + 1); // 3 + 1 = 4
		System.out.println(Integer.parseInt(str) + 1); // 3 + 1 = 4
		System.out.println(str + 1); //"3" + 1 >> "3" + "1" = "31"
		System.out.println(3 + '0'); //51 >>'0'은 숫자로 48
		
	
	}

}
```
