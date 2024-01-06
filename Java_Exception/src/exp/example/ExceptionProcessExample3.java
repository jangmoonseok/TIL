package exp.example;

import exp.exception.NotFoundUserIdException;

public class ExceptionProcessExample3 {

}

class ExceptionProcessClass3{
	/*
	 * ���ܸ� ��� �˸´� Exception���� ��ȯ�ϴ� �޼ҵ� 
	 */
	void login(String id, String pwd) {
		//id��ȸ
		try {
			String userId = selectUserInfo(id, pwd);
		} catch (Exception e) {
			//good
			throw new NotFoundUserIdException("����� ������ ã�� �� �����ϴ�. ���̵� �ٽ� �Է����ּ���.");
			//bad
//			throw e;
		}
		
	}
	
	String selectUserInfo(String id, String pwd) throws Exception {
		if(id.equals("abc")) {
			throw new Exception();
		}
		
		return id;
	}
}
