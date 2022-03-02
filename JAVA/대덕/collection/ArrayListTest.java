package kr.or.ddlt.basic;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		//ArrayList는 기본적인 사용법이 Vector와 같다.
		
		ArrayList list = new ArrayList();
		
		//데이터 추가
		list.add("aaaa");
		list.add("bbbb");
		list.add(123);
		list.add('a');
		list.add(false);
		list.add(123.45);
		System.out.println("list => " + list);
		
		ArrayList<String> list2 = new ArrayList();
		list2.add("AAAA");
		list2.add("BBBB");
		list.addAll(list2);
		list.add("AAAA");
		System.out.println("list => " + list);
		System.out.println("1번째 자료 : " + list.get(1));
		
		//contains(비교객체) ==> List에 '비교객체'가 있으면 true, 없으면 false를 반환
		System.out.println("DDDD값이 있나? " + list2.contains("DDDD"));
		System.out.println("AAAA값이 있나? " + list2.contains("AAAA"));
		
		// - indexOf(비교객체) ==> 앞에서 뒤로가면서 검서
		// - lastIndexOf(비교객체) ==> 뒤에서 앞쪽으로 가면서 검사
		// ==> List에 '비교객체'가 있으면 '비교객체'가 위치한 index값을 반환하고 없으면 -1을 반환
		System.out.println("DDDD의 위치 " + list.indexOf("DDDD"));
		System.out.println("A의 위치 " + list.indexOf("AAAA"));
		System.out.println("AAAA의 위치 " + list.lastIndexOf("AAAA"));
		// toArray() ==> List안의 데이터를 배열로 변환하여 반환한다. ==> 기본적으로 Object형 배열로 변환한다.
		Object[] arr = list.toArray();
		for(int i = 0; i < arr.length; i++) {
			System.out.println(i + " : " + arr[i]);
		}
		
		// toArray(new 제네릭타입[0]) ==> 제네릭 타입의 배열로 변환한다.
		String[] arr2 = list2.toArray(new String[0]);
		for(String str : arr2) {
			System.out.println(str);
		}
	}

}
