package SleepingBarbers;

public class Customer implements Runnable {

	private int id;
	private SleepingBarbers shop;

	Customer ( int id, SleepingBarbers shop ) {
		this.id = id;
		this.shop = shop;
	}

	public int getId () {
		return id;
	}

	public void run () {
		System.out.println( "Starting Customer: " + id );
		shop.addCust( this );
	}

}
