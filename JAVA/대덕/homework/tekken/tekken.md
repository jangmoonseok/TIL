```java
package e_oop.homework;

import e_oop.ScanUtil;

public class tekken {
	Character my;
	Character enemy;
	int round = 1;
	
	public static void main(String[] args) {
		new tekken();
	}

	tekken(){
		do {
			System.out.println("=============== " + round + "라운드 ===============");
			System.out.println("캐릭터를 선택해주세요.");
			System.out.println("1.Jin 2.Paul 3.Heihachi 4.Hwoarang >");
			int cPick = ScanUtil.nextInt();
			switch(cPick) {
			case 1:
				my = new Jin();
				System.out.println("캐릭터를 "+ my.name + "로 선택하셨습니다.");
				break;
			case 2:
				my = new Paul();
				System.out.println("캐릭터를 "+ my.name + "로 선택하셨습니다.");
				break;
			case 3:
				my = new Heihachi();
				System.out.println("캐릭터를 "+ my.name + "로 선택하셨습니다.");
				break;
			case 4:
				my = new Hwoarang();
				System.out.println("캐릭터를 "+ my.name + "로 선택하셨습니다.");
				break;
			}
			System.out.println("==============================");
			int enemyNum = (int)(Math.random() * 4) + 1;
			switch(enemyNum) {
			case 1:
				enemy = round == 1 ? new Jin() : round == 2 ? new Jin(5) : new Jin(5,5);
				System.out.println("대결상대는 "+ enemy.name + "입니다.");
				battle(my,enemy);
				break;
			case 2:
				enemy = round == 1 ? new Paul() : round == 2 ? new Paul(5) : new Paul(5,5);
				System.out.println("대결상대는 "+ enemy.name + "입니다.");
				battle(my,enemy);
				break;
			case 3:
				enemy = round == 1 ? new Heihachi() : round == 2 ? new Heihachi(5) : new Heihachi(5,5);
				System.out.println("대결상대는 "+ enemy.name + "입니다.");
				battle(my,enemy);
				break;
			case 4:
				enemy = round == 1 ? new Hwoarang() : round == 2 ? new Hwoarang(5) : new Hwoarang(5,5);
				System.out.println("대결상대는 "+ enemy.name + "입니다.");
				battle(my,enemy);
				break;
			}
			if(round > 3) {
				System.out.println("모든 라운드 클리어!!!!!!");
				break;
			}
		}while(round >= 2);
}

	
	
	void battle(Character my,Character enemy) {
		System.out.println("=========== 전투 시작 ===========");
		int turn = 10;
		battle : while(true) {
					if(turn > 0) {
						System.out.println("1.공격\t2.방어\t3.캐릭터정보");
						int input = ScanUtil.nextInt();
						switch(input) {
						case 1:
							my.attack(enemy);
							if(enemy.hp <= 0) {
								System.out.println("========== 전투 종료 ==========");
								System.out.println("\t  승리!");
								System.out.println("============================");
								System.out.println("다음 라운드를 진행하시겠습니까? 1.Yes 2.No");
								int next = ScanUtil.nextInt();
								if(next == 1) {
									round++;
								}else {
									System.out.println("게임 종료");
								}
								break battle;
							}
							if(my.hp <= 0) {
								System.out.println("========== 전투 종료 ==========");
								System.out.println("\t패배...!");
								System.out.println("============================");
								System.out.println("게임 종료");
								round = 1;
								break battle;
							}
							turn--;
							System.out.println("남은 턴 : " + turn);
							System.out.println("==============================");
							break;
						case 2:							
							my.defence(enemy);
							turn--;
							System.out.println("남은 턴 : " + turn);
							System.out.println("==============================");
							break;
						case 3:
							my.status();
							break;
						}
					}else {
						System.out.println("========== 전투 종료 ==========");
						if(my.hp > enemy.hp) {
							System.out.println("\t  승리!");
							System.out.println("다음 라운드를 진행하시겠습니까? 1.Yes 2.No");
							int next = ScanUtil.nextInt();
							if(next == 1) {
								round++;
							}else {
								System.out.println("게임 종료");
							}
						}else if(my.hp < enemy.hp) {
							System.out.println("\t패배...!");
							System.out.println("게임 종료");
							round = 1;
						}else {
							System.out.println("\t  Draw");
							System.out.println("게임 종료");
							round = 1;
						}
						System.out.println("==============================");
						break;
					}
				}
	}

}
```
