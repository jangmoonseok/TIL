```java
package ch7;

public class ch7_10_super {

	public static void main(String[] args) {
		/*
		 * 참조변수 super
		 * - 조상의 멤버와 자신의 멤버를 구별할 때 사용
		 * - 객체 자신을 가리키는 참조변수. 인스턴스 메서드(생성자)내에만 존재(static메서드에서 사용불가)
		 * 
		 * 조상의 생성자 super()
		 * - 조상의 생성자를 호출할 때 사용
		 * - 조상의 멤버는 조상의 생성자를 호출해서 초기화
		 * - 자손의 생성자에서는 자손의 멤버만 초기화해야한다.
		 * - ***생성자의 첫 줄에 반드시 생성자를 호출해야한다. 그렇지 않으면 컴파일러가 생성자의 첫 줄에 super()삽입***
		 */
		Child c = new Child();
		c.method();
	}
}

class Parent{
	int x = 10; //super.x
}

class Child extends Parent{
	int x = 20; //this.x
	void method() {
		System.out.println("this.x: " + this.x);
		System.out.println("super.x: " + super.x);
		
	}
}
```
```java
package ch7;

public class ch7_10_superTest {

	public static void main(String[] args) {
		Point3DTest p = new Point3DTest(1,2,3);
		p.method();
	}
}

class PointTest{
	int x;
	int y;
	
	PointTest(){
		this(0,0);
	}
	
	PointTest(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Point3DTest extends PointTest{
	int z;
	
	Point3DTest(int x, int y, int z){
		super(x,y); // 조상의 멤버인 x,y는 조상의 생성자를 통해 초기화
		this.z = z; // 자신의 멤버만 자신의 생성자로 초기화
	}
	
	void method() {
		System.out.println("x: " + x + ", y: " + y + ", z: " + z);
	}
}
```
