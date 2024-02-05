package pttn.factory.ship;

import pttn.factory.ship.ContainerShip;
import pttn.factory.ship.Ship;

public class ContainerShipFactory extends ShipFactory{

	@Override
	protected Ship createShip() {
		return new ContainerShip("ContainerShip", "green", "20t");
	}

}
