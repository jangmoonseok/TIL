```java
package j_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListClass {

	public static void main(String[] args) {
		/*
		 * Collection Framwork
		 * - List : 배열과 비슷하다
		 * - Map : key에 데이터를 저장한다.
		 * - Set : 배열과 비슷하지만 중복된 값은 저장되지 않는다.
		 * 
		 * 
		 * List - ArrayList
		 * - 저장된 값을 읽는 속도가 빠르다. 단, 수정과 삭제 속도는 느리다.
		 * - boolean add(Object obj) : 마지막 위치에 객체를 추가 후 성공여부를 반환한다.
		 * - void add(int index, Object obj) : 지정된 위치에 객체를 추가한다.
		 * - Object set(int index, Object obj) : 지정된 위치에 객체를 저장 후 기존 객체를 반환한다.
		 * - Object get(int index) : 지정된 위치의 객체를 반환한다.
		 * - int size() : 저장된 객체의 수를 반환한다.
		 * - Object remove(int index) : 지정된 위치의 객체를 제거한다.
		 */
		
		ArrayList sample = new ArrayList();
		
		//add
		sample.add("abc");
		sample.add(100);
		sample.add(new Scanner(System.in));

		
		ArrayList<Integer> list = new ArrayList<Integer>(); //generic : ArrayList에 저장할 타입을 정하는것
		list.add(10);
//		list.add("abc"); : Compile Error
		list.add(new Integer(20)); //Integer는 클래스이기 때문에 객체를 생성해야한다.
		
		/*
		 * Wrapper 클래스 : 기본형 타입을 대신하기위한 클래스 
		 * * ArrayList에는 객체만 저장할 수 있으므로 generic으로 Wrapper클래스를 지정하고 해당 타입으로만 저장 가능하다
		 * - byte : Byte
		 * - short : Short
		 * - int : Integer
		 * - long : Long
		 * - float : Float
		 * - double : Double
		 * - char : Character
		 * - boolean : Boolean
		 */
		System.out.println(list.add(30)); // true
		System.out.println(list); // [10, 20, 30]
		
		
		list.add(1,40);
		System.out.println(list); // [10, 40, 20, 30]
		
		//set
		int before = list.set(2, 50); //기존에 있던 list에서 2번 index에 50을 저장하고 기존에 2번 index에있던 데이터를 반환
		System.out.println(before); // 20
		System.out.println(list);// [10, 40, 50, 30] : 20이 사라지고 50이 저장됨
		
		//get
		int get = list.get(2);
		System.out.println(get);
		
		//size
//		for(int i = 0; i < list.size(); i++) {
//			//데이터가 지워지면서 뒤에있는 객체들의 index가 땡겨지기때문에 2번 돌면서 2번이 당겨진다.
//			System.out.println(i + " : " + list.remove(i));
//		}
//		System.out.println(list); //[40, 30]
		
		for(int i = list.size() - 1; i >= 0; i--) {
			//마지막 index부터 돌면 index가 땡겨지는 일이 없기 때문에 모든 데이터를 지울 수 있다.
			System.out.println(i + " : " + list.remove(i));
		}
		System.out.println(list); //[]
		
		//list에 1~100사이의 랜덤값을 10개 저장해주세요.
		for(int i = 0; i < 10; i++) {
			list.add((int)(Math.random() * 100) + 1);
		}
		System.out.println(list);
		
		//list에 저장된 값의 합계와 평균을 구해주세요.
		int sum = 0;
		double avg;
		for(int i = 0; i < list.size(); i++) {
			sum+=list.get(i);
		}
		avg = (double)sum / list.size();
		System.out.println(sum);
		System.out.println(avg);
		
		//list에서 최솟값과 최대값을 구해주세요.
//		int min = list.get(0);
//		int max = list.get(0);
//		for(int i = 0; i < list.size(); i++) {
//			if(min > list.get(i)) {
//				min = list.get(i);
//			}
//			if(max < list.get(i)) {
//				max = list.get(i);
//			}
//			
//		}
//		System.out.println("최솟값  : " + min + ", 최대값 : " + max);
		
		//list를 오름차순으로 정렬
		//선택정렬
		for(int i = 0; i < list.size(); i++) {
			int min = i;
			for(int j = i + 1; j < list.size(); j++) {
				if(list.get(min) > list.get(j)) {
					min = j;
				}
			}
//			int temp = list.get(i);
//			list.set(i, list.get(min));
//			list.set(min, temp);
			
			list.set(i, list.set(min, list.get(i)));
		}
		//버블정렬
//		for(int i = list.size() - 1; i > 0; i--) {
//			for(int j = 0; j < i; j++) {
//				if(list.get(j) > list.get(j + 1)) {
//					int temp = list.get(j);
//					list.set(j, list.get(j + 1));
//					list.set(j + 1, temp);
//				}
//			}
//		}
		System.out.println(list);
		
		//2차원
		ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
		
		list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		
		list2.add(list);
		
		list = new ArrayList<Integer>();
		list.add(40);
		list.add(50);
		list.add(60);
		
		list2.add(list);
		
		System.out.println(list2);
		
		for(int i = 0; i < list2.size(); i++) {
			ArrayList<Integer> list3 = list2.get(i);
			for(int j = 0; j < list3.size(); j++) {
				System.out.println(list3.get(j));
			}
		}
		
		//학생 3명의 5과목에 대한 0~100점의 랜덤한 점수를 2차원 ArrayList에 저장해주세요.
		list2 = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 3; i++) {
			list = new ArrayList<Integer>();
			for(int j = 0; j < 5; j++) {
				list.add((int)(Math.random() * 101));
			}
			list2.add(i, list);
		}
		System.out.println(list2);
		
		//각 학생별 합계와 평균을 구해주세요.
		ArrayList<Integer> listSum = new ArrayList<Integer>();
		ArrayList<Double> listAvg = new ArrayList<Double>();
		
		for(int i = 0; i < list2.size(); i++) {
			list = list2.get(i);
			sum = 0;
			for(int j = 0; j < list.size(); j++) {
				sum += list.get(j);
			}
			listSum.add(sum);
			listAvg.add((double)sum / list.size());
		}
		System.out.println("합계 : " + listSum + " 평균 : " + listAvg);
		ArrayList<Integer> list3 = new ArrayList<Integer>(10);
		System.out.println(list3.size());
	}

}
```
