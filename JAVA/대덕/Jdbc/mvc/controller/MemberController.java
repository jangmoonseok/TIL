package kr.or.ddit.basic.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.basic.mvc.service.IMemberService;
import kr.or.ddit.basic.mvc.service.MemberServiceImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;

public class MemberController {
	private Scanner sc = new Scanner(System.in);
	private IMemberService service;
	
	public MemberController() {
		service = new MemberServiceImpl();
	}
	
	public static void main(String[] args) {
		new MemberController().start();
	}

	private void start() {
		while(true) {
			System.out.println("======================= 작업 선택 =======================");
			System.out.println("1.자료 추가 2.자료 수정 3.자료 삭제 4.전체 자료 출력 0.프로그램 종료");
			System.out.print("작업을 선택하세요 : ");
			int input = sc.nextInt();
			
			switch(input) {
			case 1: 
				insertMember();
				break;
			case 2:
				updateMember();
				break;
			case 3: 
				deleteMember();
				break;
			case 4: 
				readAllMember();
				break;
			case 0: 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}
	
	private void readAllMember() {
		System.out.println("----------------------------- 회원 정보 -----------------------------");
		System.out.printf("%-10s%-10s%-15s%-10s", "회원ID", "회원이름", "전화번호", "주소");
		System.out.println();
		
		List<MemberVO> list = service.getAllMember();
		
		for(MemberVO member : list) {
			System.out.printf("%-10s%-10s%-15s%-10s", member.getMem_id(), member.getMem_name(), member.getMem_tel(), member.getMem_addr());
			System.out.println();
		}
	}

	private void updateMember() {
		int result = 0;
		int count = 0;
		String memId;
		do {
			System.out.println("수정할 회원의 ID를 입력하세요 : ");
			memId = sc.next();
			count = service.getMemberCount(memId);
			if(count == 0) System.out.println("존재하지 않는 회원입니다. 다시 입력해주세요");
		} while (count == 0);
		
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("비밀번호 : ");
		String pwd = sc.next();
		System.out.print("전화번호 : ");
		String tel = sc.next();
		sc.nextLine();
		System.out.print("주소 : ");
		String addr = sc.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_name(name);
		memVo.setMem_id(memId);
		memVo.setMem_addr(addr);
		memVo.setMem_pass(pwd);
		memVo.setMem_tel(tel);
		
		result = service.updateMember(memVo);
		
		if(result > 0) {
			System.out.println("회원정보 수정 성공!");
		}else {
			System.out.println("회원정보 수정 실패...");
		}
	}

	private void deleteMember() {
		int result = 0;
		System.out.print("삭제할 회원의 ID를 입력하세요 : ");
		String memid = sc.next();
		
		result = service.deleteMember(memid);
		
		if(result > 0) {
			System.out.println("삭제 성공!");
		}else {
			System.out.println("존재하지 않는 회원입니다.");
		}
	}

	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요 : ");
		
		int count = 0;
		String memId;
		do {
			System.out.print("회원ID : ");
			memId = sc.next();
			count = service.getMemberCount(memId);
			if(count > 0) {
				System.out.println("이미 등록된 회원ID입니다.");
				System.out.println("다른 ID를 입력하세요.");
			}
		}while(count > 0);
		
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("비밀번호 : ");
		String pwd = sc.next();
		System.out.print("전화번호 : ");
		String tel = sc.next();
		sc.nextLine();
		System.out.print("주소 : ");
		String addr = sc.nextLine();
		
		// 입력한 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(pwd);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
		
		int result = service.insertMember(memVo);
		
		if(result > 0) {
			System.out.println("회원정보 추가 성공!");
		}else {
			System.out.println("회원정보 추가 실패...");
		}
	}
}
