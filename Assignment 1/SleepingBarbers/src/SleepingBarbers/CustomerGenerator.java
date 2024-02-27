package SleepingBarbers;

import java.util.Random;

public class CustomerGenerator implements Runnable {

	// intervals for customer
	private int mc = 500;
	private int sdc = 150;
	
	private SleepingBarbers shop;
	
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
				Thread.sleep( (long) (r.nextGaussian() * sdc + mc) );
			} catch ( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
		
	}
	
	

}
