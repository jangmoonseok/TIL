package gnr.example;

public class GenericExtendsExample1 {

	public static void main(String[] args) {
		GenericExtendsExampleClass1<Apple> class1 = new GenericExtendsExampleClass1<>();
		class1.servingFruit(new Apple());
		
		GenericExtendsExampleClass1<WaterMelon> class2 = new GenericExtendsExampleClass1<>();
		class2.servingFruit(new WaterMelon());
		
		
//		GenericExtendsExampleClass1<String> class2 = new GenericExtendsExampleClass1<>(); 컴파일 오류 발생
	}
}

class GenericExtendsExampleClass1<T extends Fruit>{
	void servingFruit(T fruit) {
		fruit.cut();	
		fruit.peel();
	}
}

interface Fruit{
	void cut();
	void peel();
}

class Apple implements Fruit{
	@Override
	public void cut() {
		System.out.println("Cut Apple");
	}

	@Override
	public void peel() {
		System.out.println("Peel Apple");
	}
}

class WaterMelon implements Fruit{

	@Override
	public void cut() {
		System.out.println("Cut WaterMelon");
	}

	@Override
	public void peel() {
		System.out.println("Peel WaterMelon");
	}
	
}
