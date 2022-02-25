package kr.or.ddlt.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 * 		이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 한다.
 * 		이 클래스는 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현한다.
 * 
 * 		이 Student객체는 List에 저장하여 관리한다.
 * 
 * 		List에 저장된 Student객체를 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이되는 외부 정렬 기준 클래스도 작성하시오.
 * 		(단, 등수는 List에 전체 데이터가 모두 저장된 후에 구한다.)
 */
public class StudentTest {

	//등수를 구하는 메서드
	public static void setRanking(List<Student> list) {
		//방법(1)
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setRank(1);
			for(int j = 0; j < list.size(); j++) {
				if(list.get(i).getTotal() < list.get(j).getTotal()) {
					list.get(i).setRank(list.get(i).getRank() + 1);
				}
			}
		}
		//방법(2)
//		// 기준이 되는 데이터를 위한 반복문( 등수를 구할 값)
//		for(Student std1 : list) {
//			int rank = 1; // rank를 1로 초기화한다.
//			
//			// 비교 대상을 찾기 위한 반복문
//			for(Student std2 : list) {
//				// 기준보다 큰 값을 만나면 rank를 증가
//				if(std1.getTotal() < std2.getTotal()) {
//					rank++;
//				}
//			}
//			// 구해진 등수를 Student객체의 rank변수에 저장
//			std1.setRank(rank);
//		}
	}
	
	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(20220006, "홍길동", 91, 65, 78));
		studentList.add(new Student(20220003, "성춘향", 87, 95, 77));
		studentList.add(new Student(20220004, "강감찬", 87, 95, 77));
		studentList.add(new Student(20220001, "일지매", 68, 73, 91));
		studentList.add(new Student(20220002, "변학도", 98, 90, 100));
		studentList.add(new Student(20220005, "이순신", 61, 72, 56));
		
		setRanking(studentList);
		
		System.out.println("정렬 전");
		for(Student student : studentList) {
			System.out.println(student);
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("내부 기준 정렬");
		Collections.sort(studentList);
		for(Student student : studentList) {
			System.out.println(student);
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("외부 정렬 기준 정렬");
		Collections.sort(studentList, new total());
		for(Student student : studentList) {
			System.out.println(student);
		}
		
		
	}

}

class Student implements Comparable<Student>{
	
	private int studentNum;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;
	
	
	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public String getName() {
		return name;
	}



	public int getRank() {
		return rank;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Student(int studentNum,  String name, int kor, int eng, int math) {
		this.studentNum = studentNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;
	}
	
	
	
	@Override
	public String toString() {
		return "Student [studentNum=" + studentNum + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math="
				+ math + ", total=" + total + ", rank=" + rank + "]";
	}



	@Override
	public int compareTo(Student student) {
		return Integer.compare(studentNum, student.studentNum);
	}
	
}

class total implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		//방법(1)
//		if(o1.getTotal() > o2.getTotal()) return -1;
//		else if(o1.getTotal() < o2.getTotal()) return 1;
//		else if(o1.getTotal() == o2.getTotal()) return o1.getName().compareTo(o2.getName());
//		else return 0;
		
		//방법(2)
		if(o1.getTotal() == o2.getTotal()) return o1.getName().compareTo(o2.getName());
		else return Integer.compare(o1.getTotal(), o2.getTotal()) * -1;
	}
	
}
