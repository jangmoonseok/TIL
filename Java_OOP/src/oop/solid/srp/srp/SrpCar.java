package oop.solid.srp.srp;

/*
 *
 * 
 */
public class SrpCar {

	private String make;
	private String model;
	private double consumeRate;
	private double refillRate;
	private FuelSystem fuelSystem;
	
	public SrpCar(String make, String model, double consumeRate, double refillRate) {
		this.make = make;
		this.model = model;
		this.consumeRate = consumeRate;
		this.refillRate = refillRate;
		this.fuelSystem = new FuelSystem();
	}
	
	public void drive(double distance) {
		System.out.println(distance + "km를 주행했습니다.");
		fuelSystem.consumeFuel(distance, consumeRate);
	}
	
	public void refillFuel(int money) {
		System.out.println(money + "원어치 연료를 충전합니다.");
		fuelSystem.refillFuel(money, refillRate);
	}
	
	public void displayCarInfo() {
		System.out.println(make + " " + model + "| 연료: " + fuelSystem.getFuelPercentage() + "%");
	}
	
}

