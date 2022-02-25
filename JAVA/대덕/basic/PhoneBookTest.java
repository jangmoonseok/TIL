package kr.or.ddlt.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고,
 * 		Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 * 
 * 		전화번호 정보는 Map에 저장하여 관리한다.(key값은 입력받은 사람의 '이름'으로 하고, value값은 'Phone클래스의 인스턴스'로 한다.
 * 
 * 메뉴 예시)
 * 	1. 전화번호 등록
 * 	2. 전화번호 수정
 * 	3. 전화번호 삭제
 * 	4. 전화번호 검색
 *  5. 전화번호 전체 출력
 *  0. 프로그램 종료
 *  삭제, 검색 기능은 '이름'을 입력 받아 처리한다.
 *  --------------------------
 *  실행예시)
 *  1번 입력
 *  새롭게 등록할 전화번호 정보를 입력하세요.
 *  이름 >> 
 *  전화번호 >>
 *  주소 >>
 *  
 *  중복된 정보를 입력할 시 '이미 등록된 사람입니다.' 메세지 뜨게하기 
 * 		
 */
public class PhoneBookTest {
	private Scanner s = new Scanner(System.in);
	Map<String, Phone> phoneBook = new HashMap<String, Phone>();
	public static void main(String[] args) {
		new PhoneBookTest().start();
	}

	private void start() {
		while(true) {			
			System.out.println("================================");
			System.out.println("\t전화번호부 입니다.");
			System.out.println("================================");
			System.out.println("[메뉴]");
			System.out.println("1. 전화번호 등록");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 전화번호 삭제");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호부 출력");
			System.out.println("0. 프로그램 종료");
			System.out.println("--------------------------------");
			System.out.print("메뉴 번호를 입력해주세요 > ");
			int input = s.nextInt();
			
			switch(input) {
			case 1: addPhone(); break;
			case 2: updatePhone(); break;
			case 3: deletePhone(); break;
			case 4: searchPhone(); break;
			case 5: phoneList(); break;
			case 0: 
				System.out.println("프로그램이 종료됩니다.");
				System.exit(0);
			}
		}
	}

	private void phoneList() {
		System.out.println("[전화번호부 출력]");
		System.out.println("--------------------------------------------------");
		System.out.println("번호\t이름\t전화번호\t\t주소");
		System.out.println("--------------------------------------------------");
		Set<String> phoneSet = phoneBook.keySet();
		int num = 1;
		for(String key : phoneSet) {
			Phone value = phoneBook.get(key);
			System.out.println(num++ + "\t" + value.name + "\t" + value.tel + "\t" + value.add);
			System.out.println();
		}
		System.out.println();
		
	}

	private void searchPhone() {
		s.nextLine();
		System.out.println("[전화번호 검색]");
		System.out.println("검색할 사람을 입력하세요 => ");
		String name = s.nextLine();
		if(!phoneBook.containsKey(name)) {
			System.out.println("검색할 대상이 존재하지 않습니다.");
			System.out.println();
		}else {
			Phone value = phoneBook.get(name);
			System.out.println("--------------------------------------------------");
			System.out.println("이름\t전화번호\t\t주소");
			System.out.println("--------------------------------------------------");
			System.out.println(value.name + "\t" + value.tel + "\t" + value.add);
			System.out.println();
		}
	}

	private void deletePhone() {
		s.nextLine();
		System.out.println("[전화번호 삭제]");
		System.out.println("삭제할 사람을 입력하세요 => ");
		String name = s.nextLine();
		if(!phoneBook.containsKey(name)) {
			System.out.println("삭제할 대상이 존재하지 않습니다.");
			System.out.println();
		}else {			
			phoneBook.remove(name);
			System.out.println(name + "의 정보가 삭제되었습니다.");
			System.out.println();
		}
	}

	private void updatePhone() {
		s.nextLine();
		System.out.println("[전화번호 수정]");
		System.out.println("수정할 사람을 입력하세요 => ");
		String name = s.nextLine();
		if(!phoneBook.containsKey(name)) {
			System.out.println("존재하지 않는 이름입니다.");
			System.out.println();
		}else {			
			System.out.println("수정할 번호 => ");
			String tel = s.nextLine();
			System.out.println("수정할 주소 => ");
			String add = s.nextLine();
			phoneBook.put(name, new Phone(name,add,tel));
			System.out.println(name + "의 정보가 수정되었습니다.");
			System.out.println();
		}
	}

	private void addPhone() {
		s.nextLine();
		System.out.println("[전화번호 등록]");
		System.out.println("등록할 이름 => ");
		String name = s.nextLine();
		System.out.println("등록할 번호 => ");
		String tel = s.nextLine();
		System.out.println("등록할 주소 => ");
		String add = s.nextLine();
		if(phoneBook.containsKey(name)) {
			System.out.println(name + "은 이미 등록된 정보입니다.");
			System.out.println();
		}else {
			phoneBook.put(name, new Phone(name,add,tel));
			System.out.println(name + "의 정보가 등록되었습니다.");
			System.out.println();
		}
	}

}

class Phone{
	String name;
	String add;
	String tel;
	
	public Phone(String name, String add, String tel) {
		super();
		this.name = name;
		this.add = add;
		this.tel = tel;
	}
}
