```java
package i_api;

import e_oop.ScanUtil;

public class SetComma {

	public static void main(String[] args) {
		//숫자를 입력받아 입력받은 숫자에 3자리 마다 콤마를 붙여 출력 >> 1234567 -> 1,234,567
		System.out.println("숫자를 입력해주세요 >");
		String num = ScanUtil.nextLine();
		String result = "";
		int count = 0;
		for(int i = num.length() - 1; i >= 0; i--) {
			result = num.charAt(i) + result;
			count++;
			if(count % 3 == 0 && count != num.length()) {
				result = "," + result;
			}
		}
		System.out.println(result);
	}

}
```
