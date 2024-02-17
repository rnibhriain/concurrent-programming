package SleepingBarbers;

import java.util.Date;

public class Customer implements Runnable {
	
	int id;
	Date time;
	
	Customer ( int id ) {
		this.id = id;
	}
	 
	public void setTime ( Date time ) {
		this.time = time;
	}
	
	
	public void run () {
		getHaircut();
	}
	
	private synchronized void getHaircut  () {
		
	}

}
