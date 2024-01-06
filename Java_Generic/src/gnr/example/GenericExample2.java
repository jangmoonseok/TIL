package gnr.example;

public class GenericExample2 {
	public static void main(String[] args) {
		GenericExample2 example2 = new GenericExample2();
		example2.checkGenericType();
	}
	
	void checkGenericType() {
		//제네릭 클래스 내부에서 사용할 데이터의 타입을 String,Integer로 지정
		//선언한 순서대로 String은 T에 Integer는 U에 할당된다.
		GenericExampleClass2<String,Integer> class1 = new GenericExampleClass2<>();
		class1.setObject("String");
//		class1.setObject2(10);
		
		System.out.println(class1.toString());
	}
}

//제네릭을 사용한 클래스
class GenericExampleClass2<T,U>{
	private T object1;
	private U object2;

	public T getObject() {
		return object1;
	}

	public void setObject(T object) {
		this.object1 = object;
	}

	public U getObject2() {
		return object2;
	}

	public void setObject2(U object2) {
		this.object2 = object2;
	}

	@Override
	public String toString() {
//		return "GenericExampleClass1 [object1=" + object1.toString() + ", object2=" + object2.toString() + "]";
		return "GenericExampleClass1 [object=" + object1.toString() + "]";
	}
	
}