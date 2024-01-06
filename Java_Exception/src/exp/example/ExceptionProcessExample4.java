package exp.example;

import exp.exception.NotFoundUserIdException;
import exp.exception.NotMatchPasswordException;

public class ExceptionProcessExample4 {
	/*
	 * ������ ���ܸ� �ʰ� ó������
	 */
	public static void main(String[] args) {
		ExceptionProcessClass4 class4 = new ExceptionProcessClass4();
		try {
			class4.login("abc", "abc");
		} catch (Exception e) {
			if(e instanceof NotFoundUserIdException) {
				// Id�� �߸� �Է��� ����� ����ó�� ����
			}else if(e instanceof NotMatchPasswordException) {
				// ��й�ȣ�� �߸� �Է��� ����� ����ó�� ����
			}
		}
	}
}

class ExceptionProcessClass4{
	void login(String id, String pwd) throws Exception{
		String userId = selectUserInfo(id, pwd);
		String password = selectUserInfoByPwd(pwd);
	}
	
	String selectUserInfo(String id, String pwd) throws Exception {
		if(id.equals("abc")) {
			throw new NotFoundUserIdException("����� ������ ã�� �� �����ϴ�. ���̵� �ٽ� �Է����ּ���.");
		}
		
		return id;
	}
	
	String selectUserInfoByPwd(String pwd) throws Exception{
		if(pwd.equals("abc")) {
			throw new NotMatchPasswordException("��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.");
		}
		return pwd;
	}
}
