package com.java.ot;

public class Process {
	public static String proc(String input) {
		String str = null;
		if(input == null) return null;
		
		try {				
			Integer.parseInt(input);
			str = "???? :";
		} catch (Exception e) {
			str = "???? :";
		}
		
		
		str += input;
		
		return str;
	}
}
