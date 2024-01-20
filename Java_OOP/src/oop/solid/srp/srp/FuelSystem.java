package oop.solid.srp.srp;

/*
 * ��������� ���� å���� ������ ������ Ŭ����
 * �� �޼ҵ��� �Ű������� ���� å���� ���� ������ ������ fuel�� ���ؼ��� ���� å���� ������.
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
