package pttn.factory.ship;


public abstract class ShipFactory {
	
	/*
	 * 객체의 생성과 전,후처리를 템플릿화한 메소드
	 * 자식 클래스에서 재정의 할 수 없도록 함 
	 */
	final Ship orderShip(String email) {
		validate(email);
		
		Ship ship = createShip();
		
		sendEmail(email, ship);
		
		return ship;
	}
	
	// ShipFactory의 자식 클래스만 접근할 수 있는 추상 메소드
	abstract protected Ship createShip();
	
	private void validate(String email) {
		if(email == null) {
			throw new IllegalArgumentException("이메일을 남겨주세요");
		}
	}
	
	private void sendEmail(String email, Ship ship) {
		System.out.println(ship.name + "이 만들어져 " + email + "로 메일을 보냈습니다.");
	}
}
