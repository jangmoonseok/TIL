package oop.solid.srp.nonsrp;

/*
 *  SRP�� �����ϴ� �ڵ��� Ŭ����
 *  �� Ŭ�������� ������, ��, ��������� ���� å���� ��� ������ �ִ�.
 *  drive()�޼ҵ�� refillFuel()�޼ҵ�� ������, �𵨿� ���� ������ ����ȴ�.
 *  ��, ������, ���� ���� �߰��� ���� drive()�޼ҵ�� refillFuel()�޼ҵ忡 ���� ������ �Ͼ��.
 *  ���� ������ ������ ������, �� 2�����̱� ������ SRP�� �����Ѵ�.
 *  �߻�ȭ�� ���� ������� ���� �и��ϰ� ��������� ���� å���� ������ Ŭ������ �и��غ���
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
		System.out.println(distance + "km�� �����߽��ϴ�.");
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
		System.out.println(money + "����ġ ���Ḧ �����մϴ�.");
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
		System.out.println(make + " " + model + "| ����: " + fuel + "%");
	}
	
}
