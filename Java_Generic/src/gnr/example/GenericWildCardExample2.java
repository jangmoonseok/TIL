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
		 * �Ű����� Ÿ���� ���׸��� Car�� �� ���� Ÿ�Ը� �� �� �����Ƿ� 
		 * ���� ����
		 */
		list.add(new Car("Avante"));
		/*
		 * Bus�� Car�� ���� �̱� ������ ���� ����
		 */
		list.add(new Bus("802")); 
		
		/*
		 * �Ű������� Ÿ���� List<Objet>, List<Car> �� �� �ƹ��ų� �´ٸ�
		 * ���� �� �ֻ����� Object�� �����Ͽ� ������ �ƹ� ������ ����.
		 */
		Object object = list.get(0);
		System.out.println(list.toString());
	}
}
