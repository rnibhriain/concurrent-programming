package SleepingBarbers;

import java.util.concurrent.*;
import java.util.*;


public class SleepingBarbers {

	static ArrayList <Customer> list;

	public final static int barbers = 4;
	public static int chairs = 10;

	public void cutHair ( int id, int md, int sdh ) {

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
			Random r = new Random();

			// making sure length to cut hair has mean md and standard deviation sdh 
			timeToCut = (long) (r.nextGaussian() * sdh + md);
			TimeUnit.SECONDS.sleep(timeToCut);
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}

		System.out.println( "Customer " + customer.getId() + " had their hair cut in " + timeToCut + " seconds.");

	}

	public void addCust ( Customer customer ) {

		System.out.println( "Customer " + customer.getId() + " is entering the shop");

		synchronized ( list ) {
			if ( list.size() == chairs ) {
				System.out.println( "There are no free seats. Customer " + customer.getId() + " is leaving the shop");
			} else {
				list.add( customer );
			}
		}


	}

	public static void main(String[] args) {		

		list = new ArrayList<Customer>();
		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();

		System.out.println( "Number of cores available: " + numCores );


	}

}
