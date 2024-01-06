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
	 * write()메소드는 IOException을 던진다.
	 * IOException은 CheckedException이기 때문에 예외처리가 강제된다.
	 * 예외처리를 이 메소드에서 하지않고 상위 메소드로 던진다면 상위 메소드에서 예외처리를 해야한다.
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