package kr.or.ddlt.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		//Collection들은 자바의 객체만 저장할 수 있다.
		
		//객체 생성
		Vector v1 = new Vector();
		
		System.out.println("처음 크기 : " + v1.size());
		
		//데이터 추가하기 : add(추가할 데이터) -> 반환값:성공(true),실패(false)
		
		v1.add("aaaa");
		v1.add(123); // 기본 자료형을 자동으로 객체화 시켜준다 : 오토박싱 <==> 오토 언박싱
		v1.add(true);
		boolean r = v1.add(3.14);
		System.out.println("반환값 r => " + r);
		
		//데이터 추가하기2 : addElement(추가할 데이터) ==> 이전 버전에서부터 지원하는 메서드
		v1.addElement(1234);
		v1.addElement('a');
		System.out.println("v1 => " + v1);
		
		//데이터 꺼내오기 : get(index) ==> 'index'번째의 데이터를 반환한다.
		String temp = (String)v1.get(0); // 0번 인덱스에는 문자열 "aaaa"를 담은 Object객체가 저장돼있으므로 String타입의 변수에는 캐스팅이 필요하다 
		System.out.println("꺼내온 값 : " + temp);
		int temp2 = (int)v1.get(1); // 원래는 객체형태인 Integer로 캐스팅을 해야하지만 int로 캐스팅해도 자동으로 int로 변환되어 꺼내진다 ==> 오토 언박싱
		System.out.println("꺼내온 값 : " + temp2);
		
		//데이터 수정하기 : set(index, 새로운 데이터) ==> 'index'번째의 데이터를 '새로운 데이터'로 덮어쓴다 ==> 반환값 : 원래의 데이터
		String temp3 = (String)v1.set(0, "ZZZZ");
		System.out.println("v1 => " + v1);
		System.out.println("temp3 : " + temp3);
		
		//데이터 삭제하기 : remove(index) ==> 'index'번쨰의 자료를 삭제한다. ==> 반환값 : 삭제된 데이터
		String temp4 = (String)v1.remove(0);
		System.out.println("v1 => " + v1);
		System.out.println("삭제된 데이터 : " + temp4);
		
		//데이터 삭제하기2 : remove(삭제할 데이터) ==> '삭제할 데이터'를 찾아서 삭제한다. ==> '삭제할 데이터'가 여러개이면 앞에서부터 삭제된다. 
		// ==> 반환값 : 삭제성공(true), 삭제실패(false)
		boolean r2 = v1.remove(true);
		System.out.println("v1 => " + v1);
		System.out.println("반환값 r2 => " + r2);
		
		//v1.remove(123); // 123이라는 데이터를 삭제할 목적이지만 int형이므로 remove(index)메서드가 실행이되며 에러가 발생한다.
		v1.remove(new Integer(123)); // int형 데이터를 삭제하려면 객체화를 해야한다.
		System.out.println("v1 => " + v1);
		
		//v1.remove('a'); // 문자형데이터는 int타입의 문자코드로 인식돼 remove(index)메서드가 실행이되며 에러가 발생한다.
		v1.remove(new Character('a'));
		System.out.println("v1 => " + v1);
		
		//모든 데이터 삭제하기 : clear()
		v1.clear();
		System.out.println("v1 => " + v1);
		System.out.println("------------------------------------------------------");
		/*
		 * 제네릭타입(Generic Type) 
		 * ==> 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법으로 객체를 생성할 때 <> 괄호 안에 그 객체의 내부에서 사용할 데이터 타입을 지정해 주는 것을 말한다.
		 * ==> 제네릭 타입으로 지정한 종류의 데이터 이외의 다른 데이터는 저장할 수 없다.
		 * ==> 제네릭 타입으로 지정할 수 있는 데이터 타입은 '클래스형'이어야 한다. (Integer, Boolean, Character, String 등)
		 * ==> 제네릭 타입으로 생성하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
		 */
		
		Vector<String> v2 = new Vector<>();
		Vector<Integer> v3 = new Vector<>();
		Vector<String> v4 = new Vector<String>();
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		v4.add("BBBB");
		v4.add("EEEE");
		v4.add("FFFF");
		v3.add(1);
		System.out.println("v2 => " + v2);
		System.out.println("v4 => " + v4);
		
		//데이터 삭제하기3 : removeAll(Collection객체) ==> 'Collection객체'가 가지고 있는 데이터를 모두 삭제한다. ==> 반환값 : ture,false
		v2.removeAll(v4);
		System.out.println("v2에서 v4와 중복된 데이터 제거 => " + v2);
		
		//Vector의 데이터를 순서대로 모두 가져와 처리하기 ==> 주로 for문 사용
		for(int i = 0; i < v2.size(); i++) {
			System.out.println(i + "번째 자료 : " + v2.get(i));
		}
	}

}
