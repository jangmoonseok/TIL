package exp.unchecked;

public class UncheckedExceptionExample2 {
	public static void main(String[] args) {
		UncheckedExceptionClass2 class1 = new UncheckedExceptionClass2();
		int num = class1.run();
		System.out.println(num);
		
	}
}

class UncheckedExceptionClass2{
	int run() {
		int num = 0;
		/**
		 * ���ܰ� �߻��� ������ �ִ� �������� ���� ó��
		 * ���� ó���� ���� �����ν� ���� �޼ҵ��� ���� ���࿡ ������ ���� ���� 
		 */
		try {
			num = parseStringToInteger("hello");
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			num = 1;
		}
		
		return num;
		/**
		 * ���� ó���� ���� ����
		 * ���� �޼ҵ忡 ���� ó�� å���� ���������ν� ���� �޼ҵ尡 ���� ó���� ���� ������ ���� ������ ������� �ʴ´�.
		 */
//		parseStringToInteger("hello");
	}
	
	// ���ڿ��� ������ ��ȯ
	private int parseStringToInteger(String str) {
		int num = Integer.parseInt(str);
		return num;
	}
}