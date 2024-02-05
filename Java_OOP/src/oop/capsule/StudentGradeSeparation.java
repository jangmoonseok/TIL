package oop.capsule;

import java.util.ArrayList;

public class StudentGradeSeparation {

	public static void main(String[] args) {
		Student student1 = new Student("홍길동", 80);
		Student student2 = new Student("강감찬", 74);
		Student student3 = new Student("성춘향", 91);
		
		ArrayList<Student> studentList = new ArrayList<>();
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		
		//Student객체 고유의 책임 영역을 StudentGradeSepartion이 침범한다.
//		for(Student student : studentList) {
//			if(student.getScore() >= 90) {
//				student.setGrade(1);
//			}else if(student.getScore() >= 80) {
//				student.setGrade(2);
//			}else {
//				student.setGrade(3);
//			}
//		}
		
		//Student객체 내부는 참견하지 않고 공개된 메소드만 호출하면 된다.
		for(Student student : studentList) {
			student.setGradeByScore();
			System.out.println(student.toString());
		}
	}
}
