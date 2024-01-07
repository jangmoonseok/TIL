package gnr.example;

import java.util.ArrayList;
import java.util.List;

public class GenericWildCardExample1 {
	public static void main(String[] args) {
		Car car = new Car("Sonata");
		Bus bus = new Bus("605");
		
		List<Car> list1 = new ArrayList<>();
		list1.add(car);
		list1.add(bus);
		
		List<Bus> list2 = new ArrayList<>();
		list2.add(bus);
		
		GenericWildCardExample1 class1 = new GenericWildCardExample1();
		class1.boundedWildCardMethod(list1);
		class1.boundedWildCardMethod(list2);
	}
	
	//  Upper Bounded Wildcards
	void boundedWildCardMethod(List<? extends Car> list) {
		/* 
		 * 매개변수 타입이 만약 List<Bus>로 들어온다면 
		 * 상위 객체인 Car는 add할 수 없기 때문에 컴파일 에러 발생
		 */
//		list.add(new Car("Avante"));
		/*
		 * 매개변수가 List<Car>, List<Bus> 둘 중 아무거나 들어온다면
		 * 타입 문제는 없지만 위와 같은 오류때문에 컴파일 에러 발생 
		 */
//		list.add(new Bus("802")); 
		
		Car car = list.get(0);
		System.out.println(list.toString());
	}
}

class Car{
	String name;

	public Car(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}
}

class Bus extends Car{

	public Bus(String name) {
		super(name);
	}

	public String toString() {
		return "Bus [name=" + name + "]";
	}
}