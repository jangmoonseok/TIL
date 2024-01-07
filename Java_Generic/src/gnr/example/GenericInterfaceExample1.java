package gnr.example;

public class GenericInterfaceExample1 {
	public static void main(String[] args) {
		GenericInterfaceExampleClass1<String> class3 = new GenericInterfaceExampleClass1<String>();
//		class3.add(1, 0); 제네릭 타입을 String으로 선언했기 때문에 int타입의 요소를 넣으면 컴파일오류 발생
		class3.add("A", 0);
		class3.add("B", 1);
		class3.add("C", 2);
		class3.add("D", 3);
		
		System.out.println("0번째 index요소 = " + class3.getElement(0));
		System.out.println("1번째 index요소 = " + class3.getElement(1));
		System.out.println("2번째 index요소 = " + class3.getElement(2));
		System.out.println("3번째 index요소 = " + class3.getElement(3));
		System.out.println("4번째 index요소 = " + class3.getElement(4));
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
		* 제네릭 타입의 객체는 생성이 불가하다.
		* 따라서 Object타입의 객체를 만들고 제네릭 타입으로 형 변환을 해주어야 한다. 
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