package gnr.example;

public class GenericExample1 {

	public static void main(String[] args) {
		GenericExample1 example1 = new GenericExample1();
		example1.checkGenericType();
	}
	
	void checkGenericType() {
		//제네릭 클래스 내부에서 사용할 데이터의 타입을 String으로 지정
		//String이외의 타입을 set하면 컴파일 오류가 발생한다.
		GenericExampleClass1<String> class1 = new GenericExampleClass1<String>();
		class1.setObject("String");

		//제네릭 클래스 내부에서 사용할 데이터의 타입을 Integer로 지정
		//Integer이외의 타입을 set하면 컴파일 오류가 발생한다.
		GenericExampleClass1<Integer> class2 = new GenericExampleClass1<Integer>();
		class2.setObject(10);
		
		//제네릭 클래스 내부에서 사용할 데이터의 타입을 Boolean으로 지정
		//Boolean이외의 타입을 set하면 컴파일 오류가 발생한다.
		GenericExampleClass1<Boolean> class3 = new GenericExampleClass1<Boolean>();
		class3.setObject(true);
		
		System.out.println(class1.getObject());
		System.out.println(class2.getObject());
		System.out.println(class3.getObject());
	}
}

// 제네릭을 사용한 클래스
class GenericExampleClass1<Hello>{
	private Hello object;

	public Hello getObject() {
		return object;
	}

	public void setObject(Hello object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "GenericExampleClass1 [object=" + object.toString() + "]";
	}
	
}
