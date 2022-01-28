```java
package ch5;

import java.util.Arrays;

public class ch5_2 {

	public static void main(String[] args) {
		/*
		 * Arrays클래스
		 * - equals() : 배열비교후 boolean반환
		 * - toString() : 배열출력
		 * - copyOf(), copyOfRange() : 배열의 복사
		 * - sort() : 배열의 정렬
		 */
		
		int[] arr = {1,2,3,4,5};
		int[][] arr2D = {{1,2,3},{4,5,6}};
		
		System.out.println(Arrays.toString(arr)); //출력:[1, 2, 3, 4, 5] 
		System.out.println(Arrays.deepToString(arr2D)); //출력:[[1, 2, 3], [4, 5, 6]] 다차원배열은 deepToString사용
		
		String[][] str2D = {{"aaa","bbb"},{"AAA","BBB"}};
		String[][] str2D2 = {{"aaa","bbb"},{"AAA","BBB"}};
		
		System.out.println(str2D == str2D2); //출력:false ==연산자는 참조변수를 비교하기 때문에 str2D와 str2D2는 서로 다른 메모리에 저장돼있으므로 false이다
		System.out.println(Arrays.equals(str2D, str2D2)); //출력:false
		System.out.println(Arrays.deepEquals(str2D, str2D2)); //출력:true 다차원배열비교는 deepEquals사용
		
		arr = new int[]{0,1,2,3,4};
		int[] arr2 = Arrays.copyOf(arr, arr.length); //arr2=[0,1,2,3,4] 
		int[] arr3 = Arrays.copyOf(arr, 3); //arr3=[0,1,2]
		int[] arr4 = Arrays.copyOf(arr, 7); //arr4=[0,1,2,3,4,0,0]
		int[] arr5 = Arrays.copyOfRange(arr, 2, 4); //arr5=[2,3]
		int[] arr6 = Arrays.copyOfRange(arr, 0, 7); //arr6=[0,1,2,3,4,0,0]

		arr = new int[] {1,4,0,2,3};
		Arrays.sort(arr); //arr=[0,1,2,3,4]
		System.out.println(Arrays.toString(arr));
		
    }

}
```
