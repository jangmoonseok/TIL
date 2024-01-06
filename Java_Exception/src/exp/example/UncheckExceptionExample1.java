package exp.example;

public class UncheckExceptionExample1 {
	public static void main(String[] args) {
		UncheckedExceptionClass class1 = new UncheckedExceptionClass();
		class1.runDivideIntegerBy0();
		System.out.println("runDivideIntegerBy0 �޼ҵ忡�� ������ �߻��߽��ϴ�.");
	}
}

class UncheckedExceptionClass{
	
	/**
	 * divideIntegerBy0 �޼��带 ȣ���ϱ� ������ ArithmeticException�� �߻��Ѵ�.
	 * �� �޼ҵ嵵 ���� �ƹ��� ó���� ���� �ʰ� ���� �޼ҵ�� ���ܸ� ������.
	 */
	void runDivideIntegerBy0() {
		divideIntegerBy0(2);
	}
	
	/**
	 * ������ 0���� ������ �Ǹ� ArithmeticException�� �߻��Ѵ�.
	 * ���ܸ� ó�������� �ʰ� throwsŰ���带 ��������� �ʾ����� �ڵ����� ���� �޼ҵ�� ���ܸ� ������.
	 */
	private int divideIntegerBy0(int num){
		return num / 0;
	}
}