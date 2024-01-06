package gnr.example;

public class GenericExample1 {

	public static void main(String[] args) {
		GenericExample1 example1 = new GenericExample1();
		example1.checkGenericType();
	}
	
	void checkGenericType() {
		//���׸� Ŭ���� ���ο��� ����� �������� Ÿ���� String���� ����
		//String�̿��� Ÿ���� set�ϸ� ������ ������ �߻��Ѵ�.
		GenericExampleClass1<String> class1 = new GenericExampleClass1<String>();
		class1.setObject("String");

		//���׸� Ŭ���� ���ο��� ����� �������� Ÿ���� Integer�� ����
		//Integer�̿��� Ÿ���� set�ϸ� ������ ������ �߻��Ѵ�.
		GenericExampleClass1<Integer> class2 = new GenericExampleClass1<Integer>();
		class2.setObject(10);
		
		//���׸� Ŭ���� ���ο��� ����� �������� Ÿ���� Boolean���� ����
		//Boolean�̿��� Ÿ���� set�ϸ� ������ ������ �߻��Ѵ�.
		GenericExampleClass1<Boolean> class3 = new GenericExampleClass1<Boolean>();
		class3.setObject(true);
		
		System.out.println(class1.getObject());
		System.out.println(class2.getObject());
		System.out.println(class3.getObject());
	}
}

// ���׸��� ����� Ŭ����
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
