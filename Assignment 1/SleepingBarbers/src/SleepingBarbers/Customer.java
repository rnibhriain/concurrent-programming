package SleepingBarbers;

import java.util.Date;

public class Customer implements Runnable {

	private int id;
	SleepingBarbers shop;

	Customer ( int id, SleepingBarbers shop ) {
		this.id = id;
		this.shop = shop;
	}

	public int getId () {
		return id;
	}

	public void run () {
		getHaircut();
	}

	private synchronized void getHaircut  () {
		System.out.println( "Starting customer " + id );
		
		shop.addCust( this );
		
		
	}

}
