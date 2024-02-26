package SleepingBarbers;

import java.util.concurrent.*;
import java.util.*;


public class SleepingBarbers {
	
	// intervals for customer
	int mc = 1000;
	int sdc = 150;
	
	static ArrayList <Customer> list;
	
	public final static int barbers = 4;
	public static int chairs = 10;
	
	public void cutHair ( int id ) {
		
		Customer customer;
		
		System.out.println("Barber " + id + " waiting for lock.");
		
		synchronized ( list ) {
			while ( list.size() == 0 ) {
				System.out.println( "Barber " + id + " is waiting for a customer" );
				try
                {
					// waiting for customer to be added
                    list.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
			}
			customer = list.remove( 0 );
			System.out.println("Barber found customer " + customer.getId() + "  in the queue.");
		}
		
		long timeToCut = 0;
		System.out.println("Cutting hair of  " + customer.getId() );
		try
        {    
			timeToCut = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(timeToCut);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
		
		System.out.println( "Customer " + customer.getId() + " had their hair cut in " + timeToCut + " seconds.");
		
	}

	public static void main(String[] args) {		
		
		list = new ArrayList<Customer>();
		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();
		
		System.out.println( "Number of cores available: " + numCores );

		
	}

}
