package MatrixMultiplication;

import java.util.concurrent.*;

public class MatrixMultiplication {

	static int MAX_THREADS = 2;
	static final int N = 4096;
	static int [][] matA = new int[N][N];
	static int [][] matB = new int[N][N];
	static int[][] matC = new int[N][N];

	static void printMat ( int mat [][] ) {
		int i, j; 

		for ( i = 0; i < N; i++ ) {
			for ( j = 0; j < N; j++ ) {
				System.out.print( mat[ i ][ j ] + " " );
			}
			System.out.print( "\n" );
		}
	}

	static void generateMat ( int mat [] [] ) {
		for ( int i = 0; i < N; i++ ) {
			for ( int j = 0; j < N; j++ ) {
				mat[ i ][ j ] = (int) ( Math.random() * 10 );
			}
		}
	}

	static class Multiplier implements Runnable {
		int i;

		Multiplier ( int i ) {
			this.i = i;
		}

		public void run () {
			for ( int j = 0; j < N; j++ ) {
				for ( int k = 0; k < N; k++ ) {
					matC[ i ][ j ] += matA[ i ][ k ] * matB[ k ][ j ];
				}
			}
		}
	}

	public static void main( String [] args ) {	

		generateMat( matA );

		generateMat( matB );

		System.out.print( "\nMatrix A\n" );
		//printMat( matA );
		System.out.print( "\nMatrix B\n" );
		//printMat( matB );

		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();

		System.out.println( "Number of cores available: " + numCores );

		// starting multicore fixed threads
		final ExecutorService executor = Executors.newFixedThreadPool( numCores );

		long startTime = System.currentTimeMillis();

		executor.execute(() -> {
			for ( int i = 0; i < N; i++ ) {
				Thread multiplier = new Thread( new Multiplier( i ) );
				multiplier.start();
			}

		});

		executor.shutdown();
		
		try {
			if ( executor.awaitTermination( 1, TimeUnit.DAYS ) ) {

				long endTime = System.currentTimeMillis();

				System.out.println("That took " + (endTime - startTime) + " milliseconds");
				
				//System.out.println("Contents of result matrix");
				//printMat( matC );
			} else {
				executor.shutdownNow();
			}
		} catch ( InterruptedException ex ) {
			ex.printStackTrace();
		}

	}


}