```java
package service;

import java.util.HashMap;
import java.util.Map;

import dao.MemberDao;
import util.ScanUtil;
import util.View;

public class MemberService {

	//싱글톤 패턴 :  하나의 객체를 돌려쓰게 만들어주는 디자인 패턴
	private MemberService() { // 다른 클래스에서 객체생성을 막기 위해 생성자에 private를 붙인다
		
	}
	private static MemberService instance; // MemberService객체를 만들어서 보관 할 변수
	public static MemberService getInstance() { // 다른 클래스에서 MemberService객체가 필요할 때 호출 할 메서드
		if(instance == null) {
			// 객체가 없으면 객체 생성
			instance = new MemberService();
		}
		return instance;
	}
	
	public static Map<String, Object> loginMember;
	private MemberDao memberDao = MemberDao.getInstance();
	
	
	public int join() {
		System.out.println("========== 회원가입 ==========");
		System.out.print("아이디 >");
		String memId = ScanUtil.nextLine();
		System.out.print("비밀번호 >");
		String password = ScanUtil.nextLine();
		System.out.print("이름 >");
		String memName = ScanUtil.nextLine();
		
		//아이디 중복 확인
		//비밀번호 확인
		//유효성 검사(정규표현식)
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("MEM_ID", memId);
		param.put("PASSWORD", password);
		param.put("MEM_NAME", memName);
		
		int result = memberDao.insertMember(param);
		
		if(result > 0) {
			System.out.println("회원가입 성공");
		}else {
			System.out.println("회원가입 실패");
		}
		
		return View.HOME;
	}

	public int login() {
		System.out.println("========== 로그인 ==========");
		System.out.print("아이디 >");
		String memId = ScanUtil.nextLine();
		System.out.print("비밀번호 >");
		String password = ScanUtil.nextLine();
		
		Map<String, Object> member = memberDao.selectMember(memId, password);
		
		if(member == null) {
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하였습니다.");
		}else {
			System.out.println("로그인 성공");
			loginMember = member;
			return View.BOARD_LIST;
		}
		return View.LOGIN;
	}
}
