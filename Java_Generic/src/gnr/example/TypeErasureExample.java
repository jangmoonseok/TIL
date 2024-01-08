package gnr.example;

import java.util.ArrayList;
import java.util.List;

// 치환 전
public class TypeErasureExample<T> {

	private List<T> array;

	public TypeErasureExample() {
		array = new ArrayList<T>();
	}
	
	void add(T element) {
		array.add(element);
	}
	
	List<T> getAll() {
		return array;
	}
}

// 치환 후
//public class TypeErasureExample {
//
//	private List array;
//
//	public TypeErasureExample() {
//		array = new ArrayList();
//	}
//	
//	void add(Object element) {
//		array.add(element);
//	}
//	
//	List getAll() {
//		return array;
//	}
//}
