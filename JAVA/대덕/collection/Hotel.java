package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	
	private static Map<Integer, Room> roomMap = new HashMap<Integer, Room>();
	private Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		int single = 201;
		int dob = 301;
		int sweet = 401;

		//Map에 객실 만들기
		for(int i = 0; i < 9; i++) {
			roomMap.put(single, new Room(single, "싱글룸"));
			roomMap.put(dob, new Room(dob, "더블룸"));
			roomMap.put(sweet, new Room(sweet, "스위트룸"));
			single++;
			dob++;
			sweet++;
		}
		
		new Hotel().start();
	}

	private void start() {
		while(true) {			
			System.out.println("======================================");
			System.out.println("\t호텔문을 열었습니다. 어서오세요.");
			System.out.println("======================================");
			System.out.println();
			System.out.println();
			System.out.println("----------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃  3.객실상태  4.업무종료");
			System.out.println("----------------------------------");
			System.out.print("하실 업무를 입력해주세요 > ");
			int input = s.nextInt();
			
			switch(input) {
			case 1: checkIn(); break;
			case 2: checkOut(); break;
			case 3: viewRoom(); break;
			case 4: 
				System.out.println("==================================");
				System.out.println("\t호텔문을 닫았습니다.");
				System.out.println("==================================");
				System.exit(0);
			}
		}
		
	}
	
	private void checkOut() {
		System.out.println("------------------------------");
		System.out.println("체크아웃 작업");
		System.out.println("------------------------------");
		System.out.println("201~209 : 싱글룸");
		System.out.println("301~309 : 더블룸");
		System.out.println("401~409 : 스위트룸");
		System.out.println("------------------------------");
		System.out.print("방 번호 입력 => ");
		int roomNum = s.nextInt();
		//Scanner.nextInt()를 사용하고나면 입력버퍼에 enter키값이 남아있다. nextLine()은 enter키값까지 받아가기때문에 입력하기 전에 nextLine()을 사용하여 초기화
		s.nextLine();
		if(!roomMap.containsKey(roomNum)) {
			//입력한 방 번호가 존재하지 않을때
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
		}else {			
			if(roomMap.get(roomNum).getUser() == "-") {
				//입력한 방에 손님이 없을때
				System.out.println(roomNum + "호 객실은 체크인 한 사람이 없습니다. ");
			}else {
				System.out.println(roomNum + "호 객실의 " + roomMap.get(roomNum).getUser() + "님 체크아웃을 완료하였습니다.");
				roomMap.get(roomNum).setUser("-");
			}
		}
	}

	private void checkIn() {
		System.out.println("------------------------------");
		System.out.println("체크인 작업");
		System.out.println("------------------------------");
		System.out.println("201~209 : 싱글룸");
		System.out.println("301~309 : 더블룸");
		System.out.println("401~409 : 스위트룸");
		System.out.println("------------------------------");
		System.out.print("방 번호 입력 => ");
		int roomNum = s.nextInt();
		//Scanner.nextInt()를 사용하고나면 입력버퍼에 enter키값이 남아있다. nextLine()은 enter키값까지 받아가기때문에 입력하기 전에 nextLine()을 사용하여 초기화
		s.nextLine();
		if(!roomMap.containsKey(roomNum)) {
			//입력한 방 번호가 존재하지 않을때
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
		}else {			
			if(roomMap.get(roomNum).getUser() != "-") {
				//입력한 방에 손님이 있을때
				System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
			}else {
				System.out.print("체크인 하실 고객명을 입력해주세요 > ");
				String user = s.nextLine();
				roomMap.get(roomNum).setUser(user);
				System.out.println(user + "님 체크인이 완료되었습니다.");
			}
		}
	}

	private void viewRoom() {
		//Map과 Set은 index가 없으므로 순서가 정해져있지 않다 ==> 
		//keySet을 생성해서 List에 넣어 방 번호를 오름차순으로 정렬 후 출력

		Set<Integer> roomSet = roomMap.keySet();
		List<Integer> roomList = new ArrayList<>(roomSet);
		Collections.sort(roomList);
		
		System.out.println("============================");
		System.out.println("방 번호\t방 종류\t투숙객");
		System.out.println("============================");
		for(Integer roomNum : roomList) {
			Room room = roomMap.get(roomNum);
			System.out.println(room.num + "\t" + room.name + "\t" + room.user);
			System.out.println("----------------------------");
		}
	}

}

class Room{
	int num;
	String name;
	String user = "-";
	
	public Room(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
