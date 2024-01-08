package gnr.example;

import java.util.ArrayList;
import java.util.List;

// ġȯ ��
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

// ġȯ ��
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
