package pttn.factory.ship;

public class Ship {

	public String name;
	public String color;
	public String capacity;

	@Override
	public String toString() {
		return String.format("Ship {name : '%s', color : '%s', capacity : '%s'", name, color, capacity);
	}
}

class ContainerShip extends Ship{
	public ContainerShip(String name, String color, String capacity) {
		this.name = name;
		this.color = color;
		this.capacity = capacity;
	}
}

class OilTankerShip extends Ship{

	public OilTankerShip(String name, String color, String capacity) {
		this.name = name;
		this.color = color;
		this.capacity = capacity;
	}
	
}
