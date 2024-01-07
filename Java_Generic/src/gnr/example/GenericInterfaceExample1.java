package gnr.example;

public class GenericInterfaceExample1 {
	public static void main(String[] args) {
		GenericInterfaceExampleClass1<String> class3 = new GenericInterfaceExampleClass1<String>();
//		class3.add(1, 0); ���׸� Ÿ���� String���� �����߱� ������ intŸ���� ��Ҹ� ������ �����Ͽ��� �߻�
		class3.add("A", 0);
		class3.add("B", 1);
		class3.add("C", 2);
		class3.add("D", 3);
		
		System.out.println("0��° index��� = " + class3.getElement(0));
		System.out.println("1��° index��� = " + class3.getElement(1));
		System.out.println("2��° index��� = " + class3.getElement(2));
		System.out.println("3��° index��� = " + class3.getElement(3));
		System.out.println("4��° index��� = " + class3.getElement(4));
	}
}

interface GenericExampleInterface1<T>{
	void add(T element, int index);
	T getElement(int index);
}

class GenericInterfaceExampleClass1<T> implements GenericExampleInterface1<T>{

	private Object[] array;
	
	
	public GenericInterfaceExampleClass1() {
		/* 
		* ���׸� Ÿ���� ��ü�� ������ �Ұ��ϴ�.
		* ���� ObjectŸ���� ��ü�� ����� ���׸� Ÿ������ �� ��ȯ�� ���־�� �Ѵ�. 
		*/
		array = new Object[10];
	}

	@Override
	public void add(T element, int index) {
		array[index] = element;
	}

	@Override
	public T getElement(int index) {
		return (T)array[index];
	}
	
}