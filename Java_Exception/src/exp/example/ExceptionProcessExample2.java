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
	 * 존재하지 않는 ID를 입력했을 때 예외를 발생시키는 메소드
	 * 사용자에게 오류 메세지를 통해 어떤 이유로 잘못된 것인지 빠르게 파악할 수 있도록 한다.
	 */
	String selectUserInfo(String id, String pwd){
		if(id.equals("abc")) {
			throw new NotFoundUserIdException("사용자 정보를 찾을 수 없습니다. 아이디를 다시 입력해주세요.");
		}
		
		return id;
	}
}