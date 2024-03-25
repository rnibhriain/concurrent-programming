package MatrixMultiplication;

import java.util.concurrent.*;

public class MatrixMultiplication {

	public static void main( String [] args ) {		


		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();

		System.out.println( "Number of cores available: " + numCores );
		
		// starting multicore fixed threads
		final ExecutorService executor = Executors.newFixedThreadPool( numCores );
		
		executor.execute(() -> {

			
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

	}


}
