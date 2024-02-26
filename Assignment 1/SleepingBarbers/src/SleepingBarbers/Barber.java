package SleepingBarbers;

public class Barber implements Runnable {

	int id;
	SleepingBarbers shop;

	// Cutting hair time
	int mh = 2000;
	int sdh = 275;

	Barber ( int id,  SleepingBarbers shop) {
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
