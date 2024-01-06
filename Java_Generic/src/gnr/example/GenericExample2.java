package gnr.example;

public class GenericExample2 {
	public static void main(String[] args) {
		GenericExample2 example2 = new GenericExample2();
		example2.checkGenericType();
	}
	
	void checkGenericType() {
		//���׸� Ŭ���� ���ο��� ����� �������� Ÿ���� String,Integer�� ����
		//������ ������� String�� T�� Integer�� U�� �Ҵ�ȴ�.
		GenericExampleClass2<String,Integer> class1 = new GenericExampleClass2<>();
		class1.setObject("String");
//		class1.setObject2(10);
		
		System.out.println(class1.toString());
	}
}

//���׸��� ����� Ŭ����
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