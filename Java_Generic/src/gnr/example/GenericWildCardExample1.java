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
		 * �Ű����� Ÿ���� ���� List<Bus>�� ���´ٸ� 
		 * ���� ��ü�� Car�� add�� �� ���� ������ ������ ���� �߻�
		 */
//		list.add(new Car("Avante"));
		/*
		 * �Ű������� List<Car>, List<Bus> �� �� �ƹ��ų� ���´ٸ�
		 * Ÿ�� ������ ������ ���� ���� ���������� ������ ���� �߻� 
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