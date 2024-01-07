package gnr.example;

import java.util.ArrayList;
import java.util.List;

public class GenericExample3 {
	
	public static void main(String[] args) {
		GenericExampleClass3<String> class3 = new GenericExampleClass3<>();
		class3.add("A");
		List<String> list1 = class3.getAll();
		// 제네릭 메소드를 활용한 String타입의 List
		List<String> list2 = class3.add(list1,"B");
		
		ArrayList<Integer> list3 = new ArrayList<>();
		// 제네릭 메소드를 활용한 Integer타입의 List
		List<Integer> list4 = class3.add(list3, 1);
		
		System.out.println(list2.toString());
		System.out.println(list4.toString());
	}
}

class GenericExampleClass3<T>{
	private List<T> array;

	public GenericExampleClass3() {
		array = new ArrayList<T>();
	}
	
	void add(T element) {
		array.add(element);
	}
	
	List<T> getAll() {
		return array;
	}
	
	/*
	 * 제네릭 메소드
	 * 해당 메소드에 선언한 T는 제네릭 클래스에 선언한 T와는 다른 타입이다.
	 * 즉, 제네릭 클래스의 타입 파라미터와는 상관없는 독립적으로 타입 파라미터를 사용하는 메서드이다. 
	 */
	<T> List<T> add(List<T> list, T element) {
		list.add(element);
		return list;
	}
}