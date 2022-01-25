```java
public class VarEx1 {

	public static void main(String[] args) {
		/*
		 * 변수란?
		 * - 하나의 값을 저장할 수 있는 메모리 공간(RAM)
		 * - 전체 메모리 공간을 1byte로 나누어서 0,1,2,3,..같이 연속적인 번호를 붙여 메모리 주소를 부여한다.
		 * - 1bit : 2진수 1자리, 1byte : 8bit
		 * - 메모리 주소는 사람이 기억하기 힘드므로 주소에 이름을 붙여 사용한다.
		 * - 지역변수는 꼭!! 읽기 전에 초기화해야 함
		 */
		
		//변수 선언 : 값을 저장할 저장소를 만드는것
		int i;
		
		//변수 초기화 : 저장소에 저장할 값을 넣는것
		i = 10;
		
		//두 변수의 값 바꾸기
		int x = 4, y = 2;
		
		x = y;
		y = x;
		System.out.println(x); // 2
		System.out.println(y); // 2
		
		/*
		 * 변수의 타입
		 * - 기본형 : (정수형)byte,short,int,long
		 * 		     (실수형)float, double
		 *           (문자형)char
		 *           (논리형)boolean
		 * - 기본형은 실제 값을 저장
		 * - 참조형 : 기본형을 제외한 나머지(String, System등)
		 * - 참조형은 메모리 주소를 저장
		 */
		
		//기본형 : 실제 값을 저장
		int i1 = 100;
		float f1 = 3.14f;
		char ch = 'A';
		System.out.println(i1); //100
		System.out.println(f1); //3.14
		System.out.println(ch); //A

		//참조형 : 메모리 주소를 저장
		Date today = new Date(); //객체 생성
		System.out.println(today); // 객체의 주소를 today에 저장
		
	}

}
```
