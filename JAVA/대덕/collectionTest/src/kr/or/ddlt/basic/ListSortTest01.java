package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest01 {
	
	/*
	 * 정렬과 관련된 interface => Comparable, Comparator
	 * 
	 * Comparable은 Collection에 추가되는 데이터 자체에 정렬기준을 넣고 싶을 때 즉, 내부 정렬 기준을 구현하는 인터페이스
	 * Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때즉, 외부 정렬 기준을 구현할 때 사용하는 인터페이스
	 * 
	 * Comparable에서는 compareTo()메서드를 재정의 해야 하고,
	 * Comparator에서는 compare()메서드를 재정의 해야 한다.
	 * 
	 * String, Wrapper클래스, Date클래스, File클래스 등에는 내부 정렬 기준이 이미 구현되어 있다.(오름차순)
	 */
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬전 : " + list);
		
		//정렬은 Collections.sort()메서드를 이용하여 정렬한다. ==> 정렬할 데이터의 '내부 정렬 기준'을 바탕으로 정렬한다.
		// ==> 보통의 '내부 정렬 기준'은 오름차순으로 정렬되도록 구현되어 있다.
		Collections.shuffle(list);
		System.out.println("자료 섞기 : " + list);
		Collections.sort(list); // 오름차순
		System.out.println("오름차순 : " + list);
		Collections.sort(list, new Desc()); // 내림차순
		System.out.println("내림차순 : " + list);
	}

}

// 외부 정렬 기준을 정해주는 class 작성하기
class Desc implements Comparator<String>{

	// compare()
	// 반환 값이 0 일 때 => 두 값이 서로 같다.
	// 반환 값이 양수 일 때 => 두 값의 순서가 바뀐다.
	// 반환 값이 음수 일 때 => 두 값의 순서가 바뀌지 않는다.
	
	//오름차순 정렬일 경우 ==> 앞의 값이 크면 양수 반환
	//                   같으면 0 반환
    //                   앞의 값이 작으면 음수 반환
	@Override
	public int compare(String str1, String str2) {
		//내림차순으로 정렬하는 기준 만들기
		return str1.compareTo(str2) * -1;
	}
	
}