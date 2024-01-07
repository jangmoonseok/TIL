package gnr.example;

import java.util.ArrayList;
import java.util.List;

public class GenericExample3 {
	
	public static void main(String[] args) {
		GenericExampleClass3<String> class3 = new GenericExampleClass3<>();
		class3.add("A");
		List<String> list1 = class3.getAll();
		// ���׸� �޼ҵ带 Ȱ���� StringŸ���� List
		List<String> list2 = class3.add(list1,"B");
		
		ArrayList<Integer> list3 = new ArrayList<>();
		// ���׸� �޼ҵ带 Ȱ���� IntegerŸ���� List
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
	 * ���׸� �޼ҵ�
	 * �ش� �޼ҵ忡 ������ T�� ���׸� Ŭ������ ������ T�ʹ� �ٸ� Ÿ���̴�.
	 * ��, ���׸� Ŭ������ Ÿ�� �Ķ���Ϳʹ� ������� ���������� Ÿ�� �Ķ���͸� ����ϴ� �޼����̴�. 
	 */
	<T> List<T> add(List<T> list, T element) {
		list.add(element);
		return list;
	}
}