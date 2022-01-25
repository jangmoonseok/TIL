```java
package ch02;

public class VarEx3 {

	public static void main(String[] args) {
		/*
		 * 접미사
		 * - 정수형 : L(long)
		 * - 실수형 : f(float)
		 * - 접미사는 대소문자 구별하지 않는다
		 */
		byte b = 123; //byte와 short의 리터럴은 없기때문에 int타입이지만 -128 ~ 127 사이값만 저장가능
		int i = 100; // 10진수
		int oct = 0100; //접두사 0 > 8진수
		int hex = 0x100; //접두사 0x > 16진수,
		int i1 = 0b100; //접두사 0b > 2진수
		long l = 10_000_000_000L; // 100억은 int의 최대값인 20억을 넘으므로 long타입에 저장하고 접미사인 L을 꼭 붙힌다
		float f = 3.14f;// f는 생략불가
		double d = 3.14d; // d는 생략가능
		double d1 = 10.; // 10.0
		double d2 = .10; //0.10
		float f1 = 10f; //10.0
		double d3 = 1e3; // 1000.0
		System.out.println(i); //100
		System.out.println(oct); //64
		System.out.println(hex); //256
		System.out.println(l); //10000000000
		System.out.println(f); //3.14
		
		/*
		 * 변수와 리터럴의 타입 불일치
		 * - 범위가 변수 > 리터럴 인 경우 OK
		 * - 범위가 변수 < 리터럴 인 경우 ERROR
		 * - byte, short 변수에 int 리터럴 저장가능
		 */
		
		//변수 > 리터럴
		int i2 = 'A'; // int > char 
		long l1 = 123; // long > int
		double d4 = 3.14f; // double > float
		
		/*
		 * 변수 < 리터럴
		 * int i = 30_0000_0000; : int의 범위 벗어남 ERROR
		 * long l = 3.14f : long < float long은 8byte, float은 4byte이지만 정부형보다 실수형이 범위가 훨씬 넓음 ERROR
		 * float f = 3.14 : float < double ERROR
		 */
		
		//byte,short변수에 int 리터럴
		byte b1 = 100; //byte의 범위 -128~127에 속함
		
		char ch = 'a'; // char타입은 문자형으로 하나의 문자만 저장가능
		//char ch1 = 'ab'; ERROR
		
		String s1 = "AB";
		String s2 = new String("AB"); // String은 클래스이어서 객체를 생성해야 하지만 자주쓰이므로 생략해서 사용한다.
		
		String s3 = "" + 7; // "7" : 문자열 + 숫자는 문자열로 결합되므로 숫자를 문자열로 바꿀때 빈 문자열을 더하면된다
		String s4 = "" + 7 + 7; //"77" >> "" + 7 = "7" >> "7" + 7 = "77"
		String s5 = 7 + 7 + ""; // "14" >> 7 + 7 = 14 >> 14 + "" = "14"
		System.out.println(ch); //a
		System.out.println(s1); //AB
		System.out.println(s2); //AB
		System.out.println(s3); //7
		System.out.println(s4); //77
		System.out.println(s5); //14
	}

}
```
