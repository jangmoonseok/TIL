package oop.solid.srp.nonsrp;

/*
 *  SRP를 위반하는 자동차 클래스
 *  한 클래스에서 제조사, 모델, 연료관리에 대한 책임을 모두 가지고 있다.
 *  drive()메소드와 refillFuel()메소드는 제조사, 모델에 따라 로직이 변경된다.
 *  즉, 제조사, 모델이 새로 추가될 수록 drive()메소드와 refillFuel()메소드에 많은 변경이 일어난다.
 *  또한 변경의 이유가 제조사, 모델 2가지이기 때문에 SRP를 위반한다.
 *  추상화를 통해 제조사와 모델을 분리하고 연료관리에 대한 책임을 가지는 클래스를 분리해보자
 */
public class NonSrpCar {
	private String make;
	private String model;
	private double fuel;
	
	public NonSrpCar(String make, String model) {
		this.make = make;
		this.model = model;
		this.fuel = 100;
	}
	
	public void drive(double distance) {
		System.out.println(distance + "km를 주행했습니다.");
		if(this.make == "Hyundai") {
			if(this.model == "Sonata") {				
				this.fuel -= distance * 0.5;
			}else if(this.make == "Avante") {
				this.fuel -= distance * 0.4;
			}
		}else if(this.make == "KIA") {
			if(this.model == "Morning") {
				this.fuel -= distance * 0.3;
			}else if(this.model == "Sorento") {
				this.fuel -= distance * 0.6;
			}
		}
	}
	
	public void refillFuel(int money) {
		System.out.println(money + "원어치 연료를 충전합니다.");
		if(this.make == "Hyundai") {
			if(this.model == "Sonata") {				
				this.fuel += money * 0.002;
			}else if(this.make == "Avante") {
				this.fuel += money * 0.0025;
			}
		}else if(this.make == "KIA") {
			if(this.model == "Morning") {
				this.fuel += money * 0.003;
			}else if(this.model == "Sorento") {
				this.fuel += money * 0.0015;
			}
		}
	}
	
	public void displayCarInfo() {
		System.out.println(make + " " + model + "| 연료: " + fuel + "%");
	}
	
}
