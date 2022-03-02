package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Lotto {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.println("================================");
			System.out.println("\tLotto");
			System.out.println("================================");
			System.out.print("1.Lotto구입 2.프로그램종료 > ");
			int input = s.nextInt();
			switch(input) {
			case 1 : buyLotto(); break;
			case 2 : 
				System.out.println("프로그램이 종료되었습니다.");
				System.exit(0);
			}

		}
	}

	private static void buyLotto() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Lotto 구입");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int money = s.nextInt();
		if(money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!");
		}else if(money > 100000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!");
		}else {			
			List<Set<Integer>> LottoList = new ArrayList<>();
			for(int i = 0; i < money/1000; i++) {
				Set<Integer> Lotto = new HashSet<Integer>();
				while(Lotto.size() < 6) {
					int rnd = (int)(Math.random() * 45 + 1);
					Lotto.add(rnd);
				}
				LottoList.add(Lotto);
			}
			
			System.out.println("행운의 로또번호는 아래와 같습니다.");
			int index = 1;
			for(int i = 0; i < LottoList.size(); i++) {
				System.out.print("로또번호" + index++ + " : ");
				for(int num : LottoList.get(i)) {
					System.out.print(num + " ");
				}
				System.out.println();
			}
			
			System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + money%1000 + "원입니다.");
		}
	}

}
