package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hotel {
	
	private static Map<Integer, Room> roomMap = new HashMap<Integer, Room>();
	
	public static void main(String[] args) {
		int single = 201;
		int dob = 301;
		int sweet = 401;

		
		for(int i = 0; i < 9; i++) {
			roomMap.put(single, new Room(single, "싱글룸"));
			roomMap.put(dob, new Room(dob, "더블룸"));
			roomMap.put(sweet, new Room(sweet, "스위트룸"));
			single++;
			dob++;
			sweet++;
		}

		Set<Integer> roomSet = roomMap.keySet();
		List<Integer> roomList = new ArrayList<>(roomSet);
		Collections.sort(roomList);
		System.out.println(roomList);
		System.out.println("방 번호\t방 종류\t투숙객");
		for(Integer roomNum : roomList) {
			Room room = roomMap.get(roomNum);
			System.out.println(room.num + "\t" + room.name + "\t" + room.user);
			System.out.println("-------------------------------");
		}
	}

	private void start() {
		
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
}
