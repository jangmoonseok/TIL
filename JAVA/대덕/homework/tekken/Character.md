```java
package e_oop.homework;

import e_oop.ScanUtil;

public class Character {
	int hp = 100; // 체력
	int defChance = 3; // 방어기회
	String name; // 캐릭터 이름
	Skill[] skills = new Skill[3]; // 스킬창

	

	
	void attack(Character enemy) {
		System.out.println("==============================");
		System.out.println("공격할 기술을 선택하세요.");
		System.out.println("==============================");
		System.out.println("1."+ skills[0].name + " 2." + skills[1].name + " 3." + skills[2].name + " >");
		int input2 = ScanUtil.nextInt();
		switch(input2) {
		case 1 :
			if(skills[0].numOfskil <= 0) {
				//기술을 다 썼을때
				attackMiss(skills[0], enemy);
				break;
			}
			skills[0].numOfskil--;
			if(enemyTurn(enemy)) {
				//enemyTurn에서 true를 리턴했을때 서로 공격을 주고받는다.
				attackSucc(skills[0], enemy);
				if(enemy.hp <= 0) {
					break;
				}
				System.out.println("--------------------------------------------->>");
				enemyAttack(enemy);
				break;
			}			
			//enemyTurn에서 false를 반환했을 때 나의 공격이 무효화 된다.			
			System.out.println("적 " + enemy.name + "이 " + skills[0].name + "을 방어했습니다. > 남은 사용횟수 : " + skills[0].numOfskil);
			break;
		case 2 :
			if(skills[1].numOfskil <= 0) {
				//기술을 다 썼을때
				attackMiss(skills[1], enemy);
				break;
			}
			skills[1].numOfskil--;
			if(enemyTurn(enemy)) {
				//enemyTurn에서 true를 리턴했을때 서로 공격을 주고받는다.
				attackSucc(skills[1], enemy);
				if(enemy.hp <= 0) {
					break;
				}
				System.out.println("--------------------------------------------->>");
				enemyAttack(enemy);
				break;
			}			
			//enemyTurn에서 false를 반환했을 때 나의 공격이 무효화 된다.				
			System.out.println("적 " + enemy.name + "이 " + skills[1].name + "을 방어했습니다. > 남은 사용횟수 : " + skills[1].numOfskil);
			break;
		case 3 :
			if(skills[2].numOfskil <= 0) {
				//기술을 다 썼을때
				attackMiss(skills[2], enemy);
				break;
			}
			skills[2].numOfskil--;
			if(enemyTurn(enemy)) {
				//enemyTurn에서 true를 리턴했을때 서로 공격을 주고받는다.
				attackSucc(skills[2], enemy);
				if(enemy.hp <= 0) {
					break;
				}
				System.out.println("--------------------------------------------->>");
				enemyAttack(enemy);
				break;
			}			
			//enemyTurn에서 false를 반환했을 때 나의 공격이 무효화 된다.				
			System.out.println("적 " + enemy.name + "이 " + skills[2].name + "을 방어했습니다. > 남은 사용횟수 : " + skills[2].numOfskil);
			break;
		}
	}

	void defence(Character enemy) {
		if(enemyTurn(enemy)) {
			//적이 공격일때 적의 공격을 방어
			if(defChance > 0) {
				//적이 공격일때 defChance가 있으면 공격방어
				defChance--;
				int enemySkillNum = (int)(Math.random() * skills.length);
				Skill enemySkill = enemy.skills[enemySkillNum];
				if(enemySkillNum == 2) {
					if(enemySkill.numOfskil == 0) {
						//적의 공격이 필살기이고 사용횟수가 0일경우에 공격을 남은 2개 기술중에서 재설정
						enemySkillNum = (int)(Math.random() * skills.length - 1);
						enemySkill = skills[enemySkillNum];
					}
				}
				enemySkill.numOfskil--;
				System.out.println("적 " + enemy.name + "의 " + enemySkill.name + "을 방어했습니다.");
				System.out.println("defChance : " + defChance);
			}else {				
				//적이 공격일때 defChance가 없으면 방어 실패
				System.out.println("방어할 수 없습니다.");
				enemyAttack(enemy);
			}
		}else {			
			//적도 방어 나도 방어일때 턴을 넘긴다.
			defChance--;
			System.out.println("아무일도 일어나지 않았습니다.");
		}
	}
	
	private boolean enemyTurn(Character enemy) {
		boolean attack = true; //true이면 공격, false면 방어
		int num = (int)(Math.random() * 3); //0 ~ 2중에 랜덤으로 뽑아서 공격,방어를 랜덤으로 정한다.
		if(num == 0) {
			//num = 0이면 방어한다
				attack = false;
		}
		return attack;
	}
	
	private void enemyAttack(Character enemy) {
		int enemySkillNum = (int)(Math.random() * skills.length);
		Skill enemySkill = enemy.skills[enemySkillNum];
		if(enemySkillNum == 2) {
			if(enemySkill.numOfskil == 0) {
				//적의 공격이 필살기이고 사용횟수가 0일경우에 공격을 남은 2개 기술중에서 재설정
				enemySkillNum = (int)(Math.random() * skills.length - 1);
				enemySkill = enemy.skills[enemySkillNum];
			}
		}
		enemySkill.numOfskil--;
		this.hp -= enemySkill.att;
		this.hp = this.hp <= 0 ? 0 : this.hp;
		System.out.println("적 " + enemy.name + "이(가)" + enemySkill.name + "을 사용했습니다. > 남은 사용횟수 : " + enemySkill.numOfskil );
		System.out.println ("내" + this.name + "의 남은체력 : " + this.hp );
	}
	
	
	void status() {
		 System.out.println("이름 : " + name);
		 System.out.println("================ 기술 ================");
		 for(int i = 0; i < skills.length; i++) {
				 System.out.println(skills[i].name + "> 위력 : " + skills[i].att + " 사용횟수 : " + skills[i].numOfskil);
		 }
		 System.out.println("=======================================");
	}
	
	private void attackMiss(Skill skill, Character enemy) {
		skill.numOfskil = 0;
		System.out.println("사용횟수가 소진하였습니다. 턴을 넘깁니다.");
		if(enemyTurn(enemy)) {
			//적이 공격일때
			enemyAttack(enemy);
		}else {					
			//적이 방어일때
			System.out.println("아무일도 일어나지 않았습니다.");
		}
	}
	
	private void attackSucc(Skill skill, Character enemy) {
		enemy.hp -= skill.att;
		enemy.hp = enemy.hp <= 0 ? 0 : enemy.hp;
		System.out.println(skill.name + "을 사용했습니다. > 남은 사용횟수 : " + skill.numOfskil);
		System.out.println("적 " + enemy.name + "의 남은체력 : " + enemy.hp );
	}
}
```
