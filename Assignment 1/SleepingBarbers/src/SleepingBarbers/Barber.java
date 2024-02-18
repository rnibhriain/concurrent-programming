package SleepingBarbers;

public class Barber implements Runnable {
	
	int id;
	
	// Cutting hair time
	int mh = 2000;
	int sdh = 275;
	
	Barber ( int id ) {
		this.id = id;
	}
	
	public void run () {
		System.out.println( "Starting Barber: " + id );
		
	}
	

}
