package MatrixMultiplication;

import java.util.concurrent.*;

public class MatrixMultiplication {

	static int MAX_THREADS = 2;
	static final int N = 256;
	static int [][] matA = new int[N][N];
	static int [][] matB = new int[N][N];
	static int[][] matC = new int[N][N];


	static class Multiplier implements Runnable {
		int i;

		Multiplier ( int i ) {
			this.i = i;
		}

		public void run () {
			for ( int j = 0; j < 2; j++ ) {
				for ( int k = 0; k < 2; k++ ) {
					matC[ i ][ j ] += matA[ i ][ k ] * matB[ k ][ j ];
				}
			}
		}
	}

	public static void main( String [] args ) {		


		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();

		System.out.println( "Number of cores available: " + numCores );

		// starting multicore fixed threads
		final ExecutorService executor = Executors.newFixedThreadPool( numCores );

		
		executor.execute(() -> {
			for ( int i = 0; i < MAX_THREADS; i++ ) {
				Thread multiplier = new Thread( new Multiplier( i ) );
				multiplier.start();
			}

		});

		executor.shutdown();

		try {
			if ( executor.awaitTermination( 1, TimeUnit.DAYS ) ) {

			} else {
				executor.shutdownNow();
			}
		} catch ( InterruptedException ex ) {
			ex.printStackTrace();
		}

		System.out.println("Contents of result matrix");
		for(int i = 0; i < 2; i++) {
			System.out.println("[" + matC[ i ][ 0 ] +","+ matC[ i ][ 1 ] +"]" );

		}
	}


}