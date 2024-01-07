package gnr.example;

import java.util.ArrayList;
import java.util.List;

public class GenericWildCardExample2 {
	public static void main(String[] args) {
		Car car = new Car("Sonata");
		Bus bus = new Bus("605");
		
		List<Car> list1 = new ArrayList<>();
		list1.add(car);
		list1.add(bus);
		
		
		GenericWildCardExample2 class1 = new GenericWildCardExample2();
		class1.boundedWildCardMethod(list1);
	}
	
	// Lower Bounded Wildcards
	void boundedWildCardMethod(List<? super Car> list) {
		/* 
		 * 매개변수 타입의 제네릭이 Car와 그 상위 타입만 올 수 있으므로 
		 * 문제 없음
		 */
		list.add(new Car("Avante"));
		/*
		 * Bus는 Car의 하위 이기 때문에 문제 없음
		 */
		list.add(new Bus("802")); 
		
		/*
		 * 매개변수의 타입이 List<Objet>, List<Car> 둘 중 아무거나 온다면
		 * 꺼낼 때 최상위인 Object로 선언하여 꺼내면 아무 문제가 없다.
		 */
		Object object = list.get(0);
		System.out.println(list.toString());
	}
}
