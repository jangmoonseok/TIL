```java
package j_collection;

import java.util.ArrayList;
import java.util.Arrays;

public class Score {

	public static void main(String[] args) {
		/*
		 * 우리반 모두의 국어, 영어, 수학, 사회, 과학, Oracle, Java 점수를 0~100사이의 랜덤한 점수로 생성하고, 아래와 같이 출력해주세요.
		 * 이름   	국어		영어		수학		사회   	과학   	Oracle	java   합계  평균   석차
		 * 홍길동  	90		90		90    	90     	90     	90     	90    630  90.0  1
		 * 홍길동  	90		90		90    	90     	90     	90     	90    630  90.0  1
		 * 홍길동  	90		90		90    	90     	90     	90     	90    630  90.0  1
		 * 홍길동  	90		90		90    	90     	90     	90     	90    630  90.0  1
		 * 홍길동  	90		90		90    	90     	90     	90     	90    630  90.0  1
		 * 과목합계 	450   	450  	450  	450    	450   	450     450 
		 * 과목평균	90.00	90.00	90.00	90.00	90.00	90.00	90.00
		 */
		
		ArrayList<String> students = new ArrayList<String>(
				Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y")
				);
		
		ArrayList<String> subject = new ArrayList<String>(
				Arrays.asList("국어","영어","수학","사회","과학","Oracle","Java")
				);
		ArrayList<ArrayList<Integer>> score = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> studentSum = new ArrayList<Integer>();
		ArrayList<Double> studentAvg = new ArrayList<Double>();
		ArrayList<Integer> subSum = new ArrayList<Integer>();
		ArrayList<Double> subAvg = new ArrayList<Double>();
		ArrayList<Integer> rank = new ArrayList<Integer>();
		
		//1. 학생의 과목당 점수, 합계, 평균 구하기
		for(int i = 0; i < students.size(); i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			score.add(list);
			int sum = 0;
			for(int j = 0; j < subject.size(); j++) {
				
				int ranScore = (int)(Math.random() * 101);
				list.add(ranScore);
				sum += ranScore;
			}
			studentSum.add(sum);
			studentAvg.add(Math.round((double)sum / subject.size() * 100) / 100.0);
		}
		//2.과목당 합계, 과목당 평균 구하기
		for(int i = 0; i < subject.size(); i++) {
			int sum = 0;
			for(int j = 0; j < students.size(); j++) {
				sum += score.get(j).get(i);
			}
			subSum.add(sum);
			subAvg.add(Math.round((double)sum / students.size() * 100) / 100.0);
		}
		
		//3.석차 구하기
		for(int i = 0; i < students.size(); i++) {
			rank.add(1);
			for(int j = 0; j < students.size(); j++) {
				if(studentSum.get(i) < studentSum.get(j)) {
					rank.set(i, rank.get(i) + 1);
				}
			}
		}
		
		//4.석차순으로 정렬
		for(int i = 0; i < rank.size(); i++) {
			int min = i;
			for(int j = i + 1; j < rank.size(); j++) {
				if(rank.get(min) > rank.get(j)) {
					min = j;
				}
			}
			rank.set(i, rank.set(min, rank.get(i)));
			students.set(i, students.set(min, students.get(i)));
			studentSum.set(i, studentSum.set(min, studentSum.get(i)));
			studentAvg.set(i, studentAvg.set(min, studentAvg.get(i)));
			score.set(i, score.set(min, score.get(i)));
		}
		
		
		System.out.print("이름\t");
		for(int i = 0; i < subject.size(); i++) {
			System.out.print(subject.get(i) + "\t");
		}
		System.out.println("합계\t평균\t석차");
		for(int i = 0; i < students.size(); i++) {
			System.out.print(students.get(i) + "\t");
			for(int j = 0; j < subject.size(); j++) {
				System.out.print(score.get(i).get(j) + "\t");
			}
			System.out.println(studentSum.get(i) + "\t" + studentAvg.get(i) + "\t" + rank.get(i));
		}
		System.out.print("과목합계\t");
		for(int i = 0; i < subSum.size(); i++) {
			System.out.print(subSum.get(i) + "\t");
		}
		System.out.println();
		System.out.print("과목평균\t");
		for(int i = 0; i < subAvg.size(); i++) {
			System.out.print(subAvg.get(i) + "\t");
		}
		
	}

}
```
