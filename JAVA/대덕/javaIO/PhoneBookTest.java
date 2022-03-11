package kr.or.ddlt.basic.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
 * 추가조건)
 * 1) '6. 전화번호 저장' 메뉴를 추가하고 구현한다.
 * 		(저장파일명은 'phoneData.dat')
 * 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와서 Map에 저장한다.
 * 3) 프로그램을 종료할 때 Map의 데이터가 수정되거나 추가 또는 삭제되면 저장한 후 종료되도록 한다.
 * 
 * 메뉴 예시)
 * 	1. 전화번호 등록
 * 	2. 전화번호 수정
 * 	3. 전화번호 삭제
 * 	4. 전화번호 검색
 *  5. 전화번호 전체 출력
 *  6. 전화번호 저장
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
		
		// 파일에 저장된 전화번호부 읽어서 Map에 저장하기
		FileInputStream fin;
		try {
			fin = new FileInputStream("d:/d_other/phoneData.dat");

			if(fin == null) {
				System.out.println("저장돼있는 전화번호부 정보가 없습니다.");
				return;
			}
			BufferedInputStream bin = new BufferedInputStream(fin);
			ObjectInputStream oin = new ObjectInputStream(bin);
			
			Object obj;
			
			System.out.println("전화번호부 불러오는중...");
			while( (obj = oin.readObject()) != null) {
				Phone phone = (Phone)obj;
				
				phoneBook.put(phone.getName(), phone);
			}
		} catch(EOFException e){
			System.out.println("전화번호부 불러오기 끝");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
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
			System.out.println("6. 전화번호부 저장");
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
			case 6: saveData(); break;
			case 0: 
				// 저장을 안하고 그냥 껐을 경우에도 자동으로 데이터 저장하기
				saveData();
				System.out.println("프로그램이 종료됩니다.");
				System.exit(0);
			}
		}
	}

	private void saveData() {
		try {
			// 출력할 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/phoneData.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			// 쓰기
			System.out.println("전화번호부 저장 시작...");
			for(String key : phoneBook.keySet()) {
				oout.writeObject(phoneBook.get(key));
			}
			System.out.println("전화번호부 저장 끝...");
			oout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		System.out.println("[전화번호 검색]");
		System.out.println("검색할 사람을 입력하세요 => ");
		String name = s.next();
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
		System.out.println("[전화번호 삭제]");
		System.out.println("삭제할 사람을 입력하세요 => ");
		String name = s.next();
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
		System.out.println("[전화번호 수정]");
		System.out.println("수정할 사람을 입력하세요 => ");
		String name = s.next();
		if(!phoneBook.containsKey(name)) {
			System.out.println("존재하지 않는 이름입니다.");
			System.out.println();
		}else {			
			System.out.println("수정할 번호 => ");
			String tel = s.next();
			System.out.println("수정할 주소 => ");
			s.nextLine();
			String add = s.nextLine();
			phoneBook.put(name, new Phone(name,add,tel));
			System.out.println(name + "의 정보가 수정되었습니다.");
			System.out.println();
		}
	}

	private void addPhone() {
		System.out.println("[전화번호 등록]");
		System.out.println("등록할 이름 => ");
		String name = s.next();
		System.out.println("등록할 번호 => ");
		String tel = s.next();
		System.out.println("등록할 주소 => ");
		s.nextLine();
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
	/*
	 * Scanner객체의 입력 메서드의 특징
	 *  - next(), nextInt(), nextDouble(),...등 ==> 사이띄기, Tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
	 *  - nextLine() ==> 한 줄 단위로 입력한다. 즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어간다.
	 *  - Scanner는 입력한 값이 입력버퍼에 먼저 저장된 후 차례로 꺼내와서 처리된다.
	 */

}

class Phone implements Serializable{
	private static final long serialVersionUID = 2355992171410712803L;
	
	String name;
	String add;
	String tel;
	
	public Phone(String name, String add, String tel) {
		super();
		this.name = name;
		this.add = add;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
