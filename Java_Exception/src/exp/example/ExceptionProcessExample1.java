package exp.example;

public class ExceptionProcessExample1 {

	public static void main(String[] args) {
		ExceptionProcessClass1 class1 = new ExceptionProcessClass1();
		try {
			String userId = class1.selectUserInfo("abc", "abc");
			System.out.println(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ExceptionProcessClass1{
	/*
	 * 존재하지 않는 ID를 입력했을 때 예외를 발생시키는 메소드
	 * 사용자에게 오류 메세지를 통해 어떤 이유로 잘못된 것인지 빠르게 파악할 수 있도록 한다.
	 */
	String selectUserInfo(String id, String pwd) throws Exception {
		if(id.equals("abc")) {
			throw new Exception("사용자 정보를 찾을 수 없습니다. 아이디를 다시 입력해주세요.");
//			throw new Exception();
		}
		
		return id;
	}
}