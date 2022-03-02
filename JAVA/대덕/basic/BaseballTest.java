package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제) Set을 활용하여 숫자 야구 게임 프로그램을 작성하시오.
 * 		컴퓨터의 숫자는 난수를 이용해서 구한다. (스트라이크는 S, 볼은 B로 나타낸다.)
 */
public class BaseballTest {
	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
		
		Set<Integer> comSet = new HashSet<Integer>();
		while(comSet.size() < 3) {
			int rnd = (int)(Math.random() * 9 + 1);
			comSet.add(rnd);
		}
		List<Integer> answer = new ArrayList<>(comSet);

		Collections.shuffle(answer);

		
		int strike;
		int ball;
		int out;
		int count = 0;
		
		
		do {
			strike = 0;
			ball = 0;
			out = 0;
			
			List<Integer> userList = new ArrayList<Integer>();
			System.out.print("숫자를 입력해주세요 > ");
			while(userList.size() < 3) {
				int num = s.nextInt();
				if(userList.contains(num)) {
					System.out.println("중복된 숫자입니다.");
				}else {				
					userList.add(num);
				}
			}	
			for(int num : userList) {
				System.out.print(num + " ");
			}
			for(int i = 0; i < answer.size(); i++) {
				if(answer.contains(userList.get(i))) {
					if(answer.indexOf(userList.get(i)) == i) {
						strike++;
					}else {
						ball++;
					}
				}else {
					out++;
				}
			}
			count++;
			System.out.println(" : " + strike + " 스트라이크 " + ball + " 볼 " + out + "아웃");
		} while (strike != 3);
		
		System.out.println("축하합니다 " + count + "번째에 정답입니다.");
		
	}
}



















