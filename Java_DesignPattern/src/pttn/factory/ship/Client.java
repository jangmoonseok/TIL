package pttn.factory.ship;

import pttn.factory.ship.ContainerShipFactory;

public class Client {

	public static void main(String[] args) {
		Ship containerShip = new ContainerShipFactory().orderShip("aaa@aaa.com");
		System.out.println(containerShip);
		
		Ship oilTankerShip = new OilTankerShipFactory().orderShip("bbb@bbb.com");
		System.out.println(oilTankerShip);
		
	}
}
