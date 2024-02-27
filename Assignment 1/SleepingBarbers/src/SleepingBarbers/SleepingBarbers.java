package SleepingBarbers;

import java.util.concurrent.*;
import java.util.*;


public class SleepingBarbers {

	private static ArrayList < Customer > list;

	private static int numBarbers = 2;
	private static int chairs = 3;

	public void cutHair ( int id, int mh, int sdh ) {

		Customer customer;

		System.out.println( "Barber " + id + " waiting to cut hair." );

		synchronized ( list ) {
			while ( list.size() == 0 ) {
				
				System.out.println( "Barber " + id + " is sleeping in his chair" );
				
				try
				{
					// waiting for customer to be added
					list.wait();
				}
				catch( InterruptedException ex )
				{
					ex.printStackTrace();
				}
			}
			customer = list.remove( 0 );
			System.out.println( "Barber " + id + " found customer " + customer.getId() + " in the waiting room." );
		}

		long timeToCut = 0;
		System.out.println( "Cutting hair of  " + customer.getId() );
		try
		{    
			Random r = new Random();

			// making sure length to cut hair has mean mh and standard deviation sdh 
			timeToCut = ( long ) ( r.nextGaussian() * sdh + mh );
			Thread.sleep( timeToCut );
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}

		System.out.println( "Customer " + customer.getId() + " had their hair cut in " + timeToCut + " seconds." );

	}

	public void addCust ( Customer customer ) {

		System.out.println( "Customer " + customer.getId() + " is entering the shop" );

		synchronized ( list ) {
			if ( list.size() >= chairs ) {
				System.out.println( "There are no free seats. Customer " + customer.getId() + " is leaving the shop" );
			} else {
				
				// presume all barbers are busy
				list.add( customer );
				System.out.println( "Customer " + customer.getId() + " is waiting in a chair" );
				
				// check if there are any waiting barbers
				if ( list.size() >= 1 ) {
					list.notify();
				}
				
			}
		}


	}

	public static void main( String [] args ) {		

		list = new ArrayList< Customer >();
		
		SleepingBarbers shop = new SleepingBarbers();
		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();

		System.out.println( "Number of cores available: " + numCores );
		
		// starting multicore fixed threads
		final ExecutorService executor = Executors.newFixedThreadPool( numCores );
		CustomerGenerator generator = new CustomerGenerator( shop );
		
		executor.execute(() -> {
			for ( int i = 0; i < numBarbers; i++ ) {
				Thread barberThread = new Thread( new Barber( i, shop ) );
				barberThread.start();
			}
			Thread generatorThread = new Thread( generator );
			generatorThread.start();
			
		});
		
		executor.shutdown();
		
		try {
			if ( executor.awaitTermination( 1, TimeUnit.DAYS ) ) {
			} else {
			    executor.shutdownNow();
			}
		} catch ( InterruptedException ex ) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		System.out.println( "Sleeping Barbershop has ShutDown............" );

	}

}
