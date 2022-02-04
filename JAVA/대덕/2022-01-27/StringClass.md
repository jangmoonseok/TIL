```java
package i_api;

import java.util.Arrays;

public class StringClass {

	public static void main(String[] args) {
		/*
		 * String
		 * - 여러개의 문자를 사용하기 쉽게 만들어 놓은 클래스
		 * 
		 * String의 주요 메서드
		 * - equals() : 문자열의 내용이 같은지 반환한다.
		 * - length() : 문자열의 길이를 반환한다.
		 * - charAt() : 특정 인덱스에 위치한 문자를 반환한다.
		 * - substring() : 문자열의 특정 부분을 잘라서 반환한다.
		 * - indexOf() : 문자열 내의 특정 문자의 인덱스를 반환한다.
		 * - contains() : 문자열이 특정 문자열을 포함하고 있는지 여부를 반환한다.
		 * - split() : 문자열을 특정 문자를 기준으로 나누어 배열형태로 반환한다.
		 * - replace() : 문자열 내의 특정 문자를 다른 문자로 바꿔서 반환한다.
		 * - trim() : 문자열 앞뒤의 공백을 제거해 반환한다.
		 * - valueOf() : 다른 타입의 데이터를 문자열로 변환해 반환한다.
		 */
		
		String str = "12345";
		int length = str.length();
		for(int i = 0; i < length; i++) {
			char charAt = str.charAt(i);
			System.out.println(charAt);
		}
		//결과 : 1 2 3 4 5
		
		//문자열 뒤집기
		String rev = "";
		for(int i = length - 1; i >= 0; i--) {
			rev += str.charAt(i);
		}
		System.out.println(rev); //결과 : 54321
		
		str = "0123456789";
		String sub1 = str.substring(3);
		System.out.println(sub1);
		String sub2 = str.substring(3, 9);
		System.out.println(sub2);
		
		str = "수박 오렌지 귤 블루베리";
		int index = str.indexOf("오렌지");
		System.out.println(index);
		
		//substring과 indexOf를 조합해서 문자열 자르기
		String[] menu = {
				"수박 20000원",
				"오렌지 100000원",
				"귤 500원",
				"블루베리 3000원"
		};
		
		for(int i = 0 ; i< menu.length; i++) {
			String name = menu[i].substring(0, menu[i].indexOf(" "));
			System.out.println(name);
			
			int price = Integer.parseInt(menu[i].substring(menu[i].indexOf(" ") + 1,menu[i].indexOf("원")));
			System.out.println(price);
		}
		
		str = "a/b/c";
		String[] split = str.split("/");
		System.out.println(Arrays.toString(split)); // 결과 : [a,b,c]
		
		//메뉴명과 가격 나누기
		for(int i = 0 ; i < menu.length; i++) {
			String[] split2 = menu[i].split(" ");
			System.out.println(split2[0] + " : " + split2[1]);
		}
//		수박 : 20000원
//		오렌지 : 100000원
//		귤 : 500원
//		블루베리 : 3000원
		
		str = "123123123";
		str = str.replace("3", "짝");
		System.out.println(str);
		
		str = " 문자열   ";
		String trim = str.trim();
		System.out.println("[" + str + "] -> [" + trim + "]" );
		
		//valueOf
		int num = 10;
		str = num + ""; // int + str = str
		str = String.valueOf(num);
		System.out.println(str);
	}

}
```
