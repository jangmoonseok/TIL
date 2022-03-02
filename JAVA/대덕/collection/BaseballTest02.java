package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseballTest02 {
	private ArrayList<Integer> numList; // 난수가 저장 될 List
	private ArrayList<Integer> userList; // 사용자가 입력한 값이 저장 될 List
	
	private int strike;
	private int ball;
	private Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		new BaseballTest02().gameStart();
	}
	
	// 게임이 시작되는 메서드
	public void gameStart() {
		int count = 0;

		getNum();
		do {			
			count++;
			inputNum();
			ballCount();
		} while (strike != 3);
		
		System.out.println();
		System.out.println("축하합니다!!" + count + "번째만에 맞췄습니다.");
	}
	
	// 1~9사이의 서로 다른 난수 3개를 만들어서 List에 저장하는 메서드
	private void getNum() {
		Set<Integer> numSet = new HashSet<Integer>();
		
		// 1~9사이의 난수 3개 만들기
		while(numSet.size() < 3) {
			numSet.add((int)(Math.random() * 9 + 1));
		}
		
		// 만들어진 난수를 List에 저장
		numList = new ArrayList<Integer>(numSet);
		
		// List에 저장된 수를 섞기
		Collections.shuffle(numList);
	}
	
	// 사용자로부터 3개의 정수를 입력받아 List에 저장하는 메서드
	private void inputNum() {
		int n1, n2, n3; // 입력한 정수가 저장 될 변수 선언
		
		
		do {
			System.out.print("숫자입력 : ");
			n1 = s.nextInt();
			n2 = s.nextInt();
			n3 = s.nextInt();
			
			if(n1 == n2 || n1 == n3 || n2==n3) {
				System.out.println("중복되는 숫자는 입력할 수 없습니다.");
			}
		} while (n1 == n2 || n1 == n3 || n2==n3);
		
		userList = new ArrayList<Integer>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
	// 스트라이크, 볼을 판정하고 결과를 출력하는 메서드
	private void ballCount() {
		strike = 0;
		ball = 0;
		
		for(int i = 0; i < userList.size(); i++) {
			for(int j = 0; j < numList.size(); j++) {
				if(userList.get(i) == numList.get(j)) {
					//값이 같은지 검사
					if(i == j) {
						//위치가 같은지 검사
						strike++;
					}else {
						ball++;
					}
						
				}
			}
		}
		// 결과 출력
		System.out.printf("%d %d %d => %dS %dB\n",
				userList.get(0), userList.get(1), userList.get(2), strike, ball);
	}
}



















