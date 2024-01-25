package oop.capsule;

public class Student {
	private String name;
	
	private int grade;

	private int score;

	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	@Override
	public String toString() {
		return String.format("%s 학생의 성적은 %s 등급입니다.", this.name , this.grade);
	}

	public void setGradeByScore() {
		if(this.score >= 90) {
			this.grade = 1;
		}else if(this.score >= 80) {
			this.grade = 2;
		}else {
			this.grade = 3;
		}
	}
	
	// Getter/Setter는 완벽히 캡슐화를 하지 못한다.
//	public int getScore() {
//		return score;
//	}
//
//	public void setGrade(int grade) {
//		this.grade = grade;
//	}
}
