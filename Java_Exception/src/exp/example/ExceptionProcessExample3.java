package exp.example;

import exp.exception.NotFoundUserIdException;

public class ExceptionProcessExample3 {

}

class ExceptionProcessClass3{
	/*
	 * 예외를 잡아 알맞는 Exception으로 변환하는 메소드 
	 */
	void login(String id, String pwd) {
		//id조회
		try {
			String userId = selectUserInfo(id, pwd);
		} catch (Exception e) {
			//good
			throw new NotFoundUserIdException("사용자 정보를 찾을 수 없습니다. 아이디를 다시 입력해주세요.");
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
