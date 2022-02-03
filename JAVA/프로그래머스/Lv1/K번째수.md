```java
package programmers;

import java.util.Arrays;

public class K번째수 {

	public static void main(String[] args) {
		int[] array = {1,5,2,6,3,7,4};
		int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
		
		Solution solution = new Solution();
		int[] answer = solution.solution(array, commands);
		System.out.println(Arrays.toString(answer));
	}
}

class Solution {
	int[] array = {1,5,2,6,3,7,4};
	int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
	
    public int[] solution(int[] array, int[][] commands) {
    	
    	int[] answer = new int[commands.length];
    	int answerId = 0;
        for(int i = 0; i < commands.length; i++){
        	int start = commands[i][0];
        	int end = commands[i][1];
        	int get = commands[i][2];
        	
        	int[] arr = new int[end - start + 1];
        	
        	int index = 0;
        	
        	for(int j = start - 1; j < end; j++) {
        		arr[index] = array[j];
        		index++;
        	}
        	
        	Arrays.sort(arr);
        	answer[answerId] = arr[get - 1];
        	answerId++;
        }
        return answer;
    }
}
```
