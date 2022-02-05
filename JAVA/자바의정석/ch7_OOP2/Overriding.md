```java
package ch7;

public class ch7_7_Overriding {

	public static void main(String[] args) {
		/*
		 * 오버라이딩(Overriding)
		 * - 덮어 씌우는 뜻
		 * - 상속받은 조상의 메서드를 자신에 맞게 변경하는 것
		 * - 선언부는 변경X, 내용만 변경가능
		 * 
		 * 오버라이딩 조건
		 * 1. 선언부가 조상 클래스의 메서드와 일치해야 한다.
		 * 2. 접근 제어자를 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다.
		 * 3. 예외는 조상 클래스의 메서드보다 많이 선언할 수 없다.
		 * 
		 * 오버로딩 vs 오버라이딩
		 * 오버로딩 : 이름이 같을 뿐 기존에 없는 새로운 메서드를 정의하는 것
		 * 오버라이딩 : 상속받은 기존의 메서드의 내용을 변경하는 것
		 */
	}

}

class Point{
	int x;
	int y;
	
	String getLocation() {
		return "x: " + x + "y: " + y;
	}
}

class Point3D extends Point{
	int z;
	
	String getLocation() { //오버라이딩 내용만 변경
		return "x: " + x + "y: " + y + "z: " + z; // 상속받은 메서드를 그대로 쓰면 z좌표를 얻을 수 없기 때문에 오버라이딩으로 내용만 변경해서 사용
	}
}
