package com.java.ot;

import java.util.Scanner;

public class Main {
	private static Input inputImpl = new Input();
	private static Process processImpl = new Process();
	private static Output outputImpl = new Output();
	
	public static void main(String[] args) {
		//입력
		String input = inputImpl.input();
		
		//처리
		String result = processImpl.proc(input);
		
		//출력
		outputImpl.out(result);

		
	}
	

}
