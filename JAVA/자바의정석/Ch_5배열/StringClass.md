```java
package ch5;

public class ch5_1 {

	public static void main(String[] args) {
		/*
		 * String클래스
		 * - String클래스 = char[] + 메서드(기능)
		 * - String클래스는 내용을 변경할 수 없다(read only)
		 */
		 String a = "a";
		 String b = "b";
		 a = a + b;
		 System.out.println(a); // 출력:ab
		 //이때 a 변수에 담긴 내용이 변경된 것 처럼 보이지만 기존의 값은 기존의 주소에 그대로 있고 새로운 주소에 새로운 결과가 담겨 변수 a가 그 주소를 참조하고있는 것이다.
		 
		 /*
		  * String클래스의 주요 메서드
		  * - char charAt(int index) : 문자열에서 해당 위치(index)에 있는 문자를 반환
		  * - int length() : 문자열의 길이를 반환한다.
		  * - String substring(int from, int to) : 문자열에서 해당 범위(from~to)의 문자열을 반환한다. 단, to는 포함 안됨
		  * - boolean equals(Object obj) : 문자열의 내용이 같은지 확인한다. 같으면 true, 다르면 false
		  * - char[] toCharArray() : 문자열을 문자배열(char[])로 변환해서 반환한다.
		  */
		 
		 String str = "ABCDE";
		 
		 char ch = str.charAt(3);
		 System.out.println(ch); //출력:D
		 
		 int length = str.length();
		 System.out.println(length); //출력:5
		 
		 String substr = str.substring(0, 3);
		 System.out.println(substr); //출력:ABC (index 0, index 1, index 2) 3은 포함안됨
		 
		 boolean equals = str.equals("ABCDE");
		 System.out.println(equals); //출력:true
		 
		 char[] toCharArr = str.toCharArray();
		 System.out.println(toCharArr); //출력:ABCDE
		 
		 
		    
	}

}
```
