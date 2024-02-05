package pttn.factory.ship;

import pttn.factory.ship.OilTankerShip;
import pttn.factory.ship.Ship;

public class OilTankerShipFactory extends ShipFactory{

	@Override
	protected Ship createShip() {
		return new OilTankerShip("OilTankerShip", "15t", "blue");
	}

}
