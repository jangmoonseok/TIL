package exp.example;

import exp.exception.NotFoundUserIdException;
import exp.exception.NotMatchPasswordException;

public class ExceptionProcessExample4 {
	/*
	 * 가능한 예외를 늦게 처리하자
	 */
	public static void main(String[] args) {
		ExceptionProcessClass4 class4 = new ExceptionProcessClass4();
		try {
			class4.login("abc", "abc");
		} catch (Exception e) {
			if(e instanceof NotFoundUserIdException) {
				// Id를 잘못 입력한 경우의 예외처리 로직
			}else if(e instanceof NotMatchPasswordException) {
				// 비밀번호를 잘못 입력한 경우의 예외처리 로직
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
			throw new NotFoundUserIdException("사용자 정보를 찾을 수 없습니다. 아이디를 다시 입력해주세요.");
		}
		
		return id;
	}
	
	String selectUserInfoByPwd(String pwd) throws Exception{
		if(pwd.equals("abc")) {
			throw new NotMatchPasswordException("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
		}
		return pwd;
	}
}
