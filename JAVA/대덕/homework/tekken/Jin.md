```java
package e_oop.homework;

class Jin extends Character{

	String name = "Jin";
	
	Jin(){
		super.name = name;
		super.skills[0] = new Skill("나락쓸기", 15, 5);
		super.skills[1] = new Skill("풍신권", 25, 5);
		super.skills[2] = new Skill("진.귀신멸렬", 40, 1);
	}
	
	Jin(int a){
		super.name = name;
		super.skills[0] = new Skill("나락쓸기", 15 + a, 5);
		super.skills[1] = new Skill("풍신권", 25, 5);
		super.skills[2] = new Skill("진.귀신멸렬", 40, 1);
	}
	
	Jin(int a, int b){
		super.name = name;
		super.skills[0] = new Skill("나락쓸기", 15 + a, 5);
		super.skills[1] = new Skill("풍신권", 25 + b, 5);
		super.skills[2] = new Skill("진.귀신멸렬", 40, 1);
	}
}

class Paul extends Character{

	String name = "Paul";
	
	Paul(){
		super.name = name;
		super.skills[0] = new Skill("낙엽", 15, 5);
		super.skills[1] = new Skill("붕권", 25, 5);
		super.skills[2] = new Skill("만성용왕권", 40, 1);
	}
	
	Paul(int a){
		super.name = name;
		super.skills[0] = new Skill("낙엽", 15 + a, 5);
		super.skills[1] = new Skill("붕권", 25, 5);
		super.skills[2] = new Skill("만성용왕권", 40, 1);
	}
	
	Paul(int a, int b){
		super.name = name;
		super.skills[0] = new Skill("낙엽", 15 + a, 5);
		super.skills[1] = new Skill("붕권", 25 + b, 5);
		super.skills[2] = new Skill("만성용왕권", 40, 1);
	}

}

class Heihachi extends Character{

	String name = "Heihachi";
	
	Heihachi(){
		super.name = name;
		super.skills[0] = new Skill("나락쓸기", 15, 5);
		super.skills[1] = new Skill("풍신권", 25, 5);
		super.skills[2] = new Skill("귀와", 40, 1);
	}
	
	Heihachi(int a){
		super.name = name;
		super.skills[0] = new Skill("나락쓸기", 15 + a, 5);
		super.skills[1] = new Skill("풍신권", 25, 5);
		super.skills[2] = new Skill("귀와", 40, 1);
	}
	
	Heihachi(int a, int b){
		super.name = name;
		super.skills[0] = new Skill("나락쓸기", 15 + a, 5);
		super.skills[1] = new Skill("풍신권", 25 + b, 5);
		super.skills[2] = new Skill("귀와", 40, 1);
	}
}

class Hwoarang extends Character{

	String name = "Hwoarang";
	
	Hwoarang(){
		super.name = name;
		super.skills[0] = new Skill("더블 잽 로우킥", 15, 5);
		super.skills[1] = new Skill("바쥬카킥", 25, 5);
		super.skills[2] = new Skill("힐 익스플로전", 40, 1);
	}
	
	Hwoarang(int a){
		super.name = name;
		super.skills[0] = new Skill("더블 잽 로우킥", 15 + a, 5);
		super.skills[1] = new Skill("바쥬카킥", 25, 5);
		super.skills[2] = new Skill("힐 익스플로전", 40, 1);
	}
	
	Hwoarang(int a, int b){
		super.name = name;
		super.skills[0] = new Skill("더블 잽 로우킥", 15 + a, 5);
		super.skills[1] = new Skill("바쥬카킥", 25 + b, 5);
		super.skills[2] = new Skill("힐 익스플로전", 40, 1);
	}
}



```
