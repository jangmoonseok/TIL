package exp.example;

import java.io.IOException;

public class CheckedExceptionExample1 {
	public static void main(String[] args) {
		byte[] list = {'a','b','c'};
		CheckedExceptionClass class1 = new CheckedExceptionClass();
		class1.write(list);
		
	}
}

class CheckedExceptionClass{
	
	/**
	 * write()�޼ҵ�� IOException�� ������.
	 * IOException�� CheckedException�̱� ������ ����ó���� �����ȴ�.
	 * ����ó���� �� �޼ҵ忡�� �����ʰ� ���� �޼ҵ�� �����ٸ� ���� �޼ҵ忡�� ����ó���� �ؾ��Ѵ�.
	 */
	 void write(byte[] list){
		try {
			System.out.write(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}