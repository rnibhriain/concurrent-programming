package SleepingBarbers;

public class Barber implements Runnable {

	private int id;
	private SleepingBarbers shop;

	// Cutting hair time
	private int mh = 5000;
	private int sdh = 275;

	Barber ( int id,  SleepingBarbers shop ) {
		this.id = id;
		this.shop = shop;
	}

	public void run () {
		System.out.println( "Starting Barber: " + id );
		
		while ( true ) {
			shop.cutHair( id, mh, sdh );
		}

	}


}
