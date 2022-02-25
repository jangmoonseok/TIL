package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest02 {

	public static void main(String[] args) {
		/*
		 * 문제) 5명의 사람 이름을 입력받아 ArrayList에 저장한 후 이들 중 '김'씨 성의 이름을 모두 출력하시오.
		 * (입력은 Scanner객체를 이용한다.)
		 */
		Scanner s = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			System.out.print("학생 이름을 입력하세요 > ");
			list.add(s.nextLine());
		}
		System.out.println(list);
		System.out.print("'김'씨 성의 이름 => ");
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).indexOf("김") == 0) {
				System.out.print(list.get(i) + " ");
			}
		}
//		for(int i = 0; i < list.size(); i++) {
//			if(list.get(i).charAt(0) == '김') {
//				System.out.println(list.get(i));
//			}
//		}
//		System.out.println();
//		for(int i = 0; i < list.size(); i++) {
//			if(list.get(i).substring(0, 1).equals("김")) {
//				System.out.println(list.get(i));
//			}
//		}
//		System.out.println();
//	    for(int i = 0; i < list.size(); i++) {
//	    	if(list.get(i).startsWith("김") == true) { //==true를 생략해도 된다.
//	    		System.out.println(list.get(i));
//	    	}
//	    }
		
		
	}

}
