package clt.example;

import java.util.HashMap;

public class HashMapExample {

	public static void main(String[] args) {
		HashMap<Integer,String> map = new HashMap<Integer, String>();
		
		map.put(1, "A");
		map.put(17, "B");
		
		System.out.println(map.toString());
	}
}
