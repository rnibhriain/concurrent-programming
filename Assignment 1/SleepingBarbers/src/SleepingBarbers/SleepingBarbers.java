package SleepingBarbers;

import java.util.concurrent.*;
import java.util.*;


public class SleepingBarbers {
	
	// intervals for customer
	int mc = 1000;
	int sdc = 150;
	
	LinkedList list;
	
	public final static int barbers = 4;
	public static int chairs = 10;

	public static void main(String[] args) {		
		
		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();
		
		System.out.println( "Number of cores available: " + numCores );

		
	}

}
