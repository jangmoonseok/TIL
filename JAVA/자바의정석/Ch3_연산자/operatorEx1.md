```java
package ch3;

public class OperatorEx1 {
	public static void main(String[] args) {
		/*
		 * 증감 연산자
		 * - 증가연산자(++)
		 * - 감소연산자(--)
		 * 
		 * - 전위형 : 값이 참조되기 전에 증가시킨다 >> j = ++i;
		 * - 후위형 : 값이 참조된 후에 증가시킨다. >> j = i++;
		 * - 증감연산자가 독립적으로 사용된 경우 전위형, 후위형의 차이가 없다. >> i++;, ++i;
		 */
		int i = 5, j = 0;
		
		j = i++; // 후위형
		System.out.println("i=" + i + "j=" + j); // j = i 이후에 i++
		i = 5;
		j = 0;
		
		j = ++i; // 전위형
		System.out.println("i=" + i + "j=" + j); // ++i 이후에 j = i

	}
}
```
