package exp.example;

public class UncheckedExceptionExample3 {
	public static void main(String[] args) {
		UncheckedExceptionClass3 class3 = new UncheckedExceptionClass3();

		class3.catchThrow(13); //���ܰ� �߻����� �ʰ� �������
		class3.notCatchThrow(13); //���� �߻�
	}
}

class UncheckedExceptionClass3{
	/*
	 * 1���� 12�������̱� ������ 12���� ū ���ڰ� ������ ���ܸ� ������.
	 * try-catch�� �ȿ��� throw�� ���ܸ� �߻���Ű�� �ش� ���ܿ� �����ϰų� ��Ӱ��迡 �ִ� ���ܰ� �ִٸ�
	 * catch��Ͽ��� ���ܸ� ��´�.
	 * ��, try-catch�� �ۿ� ������ ������ ���� �ʴ´�.
	 */
	void catchThrow(int num){
		try {
			if(num > 12) {
				throw new RuntimeException(num + "���� �������� �ʴ� �� �Դϴ�.");
			}
			
			System.out.println(num + "���� �����͸� ó���ϰڽ��ϴ�.");
		} catch (RuntimeException e) {
//			e.printStackTrace();
		}
		
		System.out.println(num);
	}
	
	/*
	 * RuntimeException�� throws Ű���尡 ��������
	 * ���� ���ܰ� �߻��ϸ� �ڵ����� ���� �޼ҵ�� ���ܸ� ������.
	 * ���� ������ ������� �ʴ´�. 
	 */
	void notCatchThrow(int num) throws RuntimeException{
		if(num > 12) {
			throw new RuntimeException(num + "���� �������� �ʴ� �� �Դϴ�.");
		}
		System.out.println(num + "���� �����͸� ó���ϰڽ��ϴ�.");
	}
}