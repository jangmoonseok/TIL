package exp.example;

import exp.exception.NotFoundUserIdException;

public class ExceptionProcessExample2 {
	public static void main(String[] args) {
		ExceptionProcessClass2 class2 = new ExceptionProcessClass2();
		String userId = class2.selectUserInfo("abc", "abc");
		System.out.println(userId);
	}
}

class ExceptionProcessClass2{
	/*
	 * �������� �ʴ� ID�� �Է����� �� ���ܸ� �߻���Ű�� �޼ҵ�
	 * ����ڿ��� ���� �޼����� ���� � ������ �߸��� ������ ������ �ľ��� �� �ֵ��� �Ѵ�.
	 */
	String selectUserInfo(String id, String pwd){
		if(id.equals("abc")) {
			throw new NotFoundUserIdException("����� ������ ã�� �� �����ϴ�. ���̵� �ٽ� �Է����ּ���.");
		}
		
		return id;
	}
}