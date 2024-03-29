package gnr.example;

import java.util.ArrayList;
import java.util.List;

// 치환 전
public class TypeErasureExample<T extends Number> {

	private List<T> list;

	public TypeErasureExample() {
		list = new ArrayList<T>();
	}
	
	void add(T element) {
		list.add(element);
	}
	
	T getValue(int i) {
		return list.get(i);
	}
	
	public static void main(String[] args) {
		TypeErasureExample<Integer> example = new TypeErasureExample<>();
		example.add(1);
		example.add(2);
		example.add(3);
		
		Integer value1 = example.getValue(1);
		
		TypeErasureExample1 example1 = new TypeErasureExample1();
//		example1.add("String"); 컴파일 오류
		example1.add(1);
		example1.add(2);
		example1.add(3);
		
		Integer value2 = example.getValue(1);
		
		System.out.println(value1);
		System.out.println(value2);
		
		
	}
}

// 치환 후
class TypeErasureExample1{

	/*
	 * List<T>가 에서 T는 Object로 치환
	 */
	private List list;

	public TypeErasureExample1() {
		list = new ArrayList(); // 제네릭 타입<T> 제거
	}
	
	/*
	 * 매개변수 T는 <T extends Number>의 하위이므로 Number로 치환
	 */
	void add(Number element) {
		list.add(element);
	}
	
	/*
	 * 반환타입 T는 <T extends Number>의 하위이므로 Number로 치환
	 * list.get()은 Object타입으로 반환하므로 Number으로 형 변환
	 */
	Number getValue(int i) {
		return (Number)list.get(i);
	}
}

