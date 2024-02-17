package SleepingBarbers;

import java.util.concurrent.*;
import java.util.*;


public class SleepingBarbers {
	
	LinkedList list;
	
	static int barbers;
	static int chairs;

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner( System.in );
		
		System.out.println( "How many barbers would you like?" );

		if ( scanner.hasNext() ) {
			while ( !scanner.hasNextInt() ) { 
				System.out.println( "How many barbers would you like?" );
				scanner.next();
			}
		}
		
		barbers = scanner.nextInt();
		
		System.out.println( "How many waiting chairs are there?" );
		
		if ( scanner.hasNext() ) {
			while ( !scanner.hasNextInt() ) { 
				System.out.println( "How many waiting chairs are there?" );
				scanner.next();
			}
		}
		
		chairs = scanner.nextInt();
		
		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();
		
		System.out.println( "Number of cores available: " + numCores );
		
		scanner.close();
		
	}

}
