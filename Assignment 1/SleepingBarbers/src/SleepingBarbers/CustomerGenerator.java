package SleepingBarbers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustomerGenerator implements Runnable {

	// intervals for customer
	int mc = 500;
	int sdc = 150;
	
	SleepingBarbers shop;
	
	CustomerGenerator ( SleepingBarbers shop ) {
		this.shop = shop;
	}
	
	public void run() {
		int count = 0;
		while ( true ) {
			Customer customer = new Customer( count++, shop );
			Thread custThread = new Thread( ( Runnable ) customer );
			custThread.start();
			Random r = new Random(); 
			// wait some time until next customer enters
			try {
				TimeUnit.MILLISECONDS.sleep( (long) (r.nextGaussian() * sdc + mc) );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}