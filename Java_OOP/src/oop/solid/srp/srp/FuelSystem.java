package oop.solid.srp.srp;

/*
 * 연료관리에 대한 책임을 온전히 가지는 클래스
 * 각 메소드의 매개변수에 대한 책임이 없기 때문에 오로지 fuel에 대해서만 단일 책임을 가진다.
 */
public class FuelSystem {
	private double fuel;
	
	public FuelSystem() {
		this.fuel = 100;
	}

	public void consumeFuel(double distance, double consumeRate) {
		this.fuel -= distance * consumeRate;
	}
	
	public void refillFuel(int money, double refillRate) {
		this.fuel += money * refillRate;
	}
	
	public double getFuelPercentage() {
		return this.fuel;
	}
}
