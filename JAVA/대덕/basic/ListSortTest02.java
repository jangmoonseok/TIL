package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//과제 : 회원번호의 내림차순으로 정렬될 수 있는 외부 정렬 기준 작성하기
public class ListSortTest02 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "강감찬", "010-4444-4444"));
		memList.add(new Member(6, "일지매", "010-5555-5555"));
		memList.add(new Member(2, "변학도", "010-6666-6666"));
		
		System.out.println("정렬 전...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		Collections.sort(memList);
		System.out.println("오름차순 정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.sort(memList, new MemberDesc());
		System.out.println("내림차순 정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
	}

}

class MemberDesc implements Comparator<Member>{

	@Override
	public int compare(Member o1, Member o2) {
//		방법(1)
//		if(o1.getNum() > o2.getNum()) {
//			return -1;
//		}else if(o1.getNum() < o2.getNum()) {
//			return 1;
//		}else {
//			return 0;
//		}
		
//		방법(2)
//		return new Integer(o1.getNum()).compareTo(o2.getNum()) * -1;
		
//		방법(3)
		return Integer.compare(o1.getNum(), o2.getNum()) * -1;
	}
	
}

// 회원 이름을 기준으로 오름차순 정렬이 되도록 내부 정렬 기준을 추가한 클래스
class Member implements Comparable<Member>{
	private int num; // 회원번호
	private String name; // 회원이름
	private String tel; // 전화번호
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	@Override
	public int compareTo(Member mem) {
		return name.compareTo(mem.getName());
	}
}