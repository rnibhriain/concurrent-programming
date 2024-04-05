package MatrixMultiplication;

import java.util.concurrent.*;

public class MatrixMultiplication {

	static int MAX_THREADS = 4;
	static final int N = 100;
	static int [][] matA = new int[N][N];
	static int [][] matB = new int[N][N];
	static int[][] matC = new int[N][N + 1];

	static void waitFor ( int mat [][] ) {

		for (int i = 0; i < N; i++ ) {
			while (mat[i][N] != 1)  {
				try {
					Thread.sleep(10);
					// System.out.printf( "Row %d not finished ...\n", i);
				} catch (Exception e) {
					System.out.println( "exception sleeping " + e);
				}
			}
		}
		System.out.printf( "All done\n" );
	}

	static void printMat ( int mat [][] ) {
		int i, j; 

		for ( i = 0; i < N; i++ ) {
			for ( j = 0; j < N; j++ ) {
				System.out.print( mat[ i ][ j ] );
				if ( j < N - 1 ) { 
					System.out.print( "," );
				}
			}
			System.out.print( "\n" );
		}
	}

	static void generateMat ( int mat [] [] ) {
		for ( int i = 0; i < N; i++ ) {
			for ( int j = 0; j < N; j++ ) {
				//mat[ i ][ j ] = i+1+(j+1)*(+1); 

				mat[ i ][ j ] = (int) ( Math.random() * 10 ); 
			}
		}
	}


	public static void main( String [] args ) {	

		MatrixMultiplication mat = new MatrixMultiplication();

		generateMat( matA );

		generateMat( matB );

		System.out.print( "\nMatrix A\n" );
		printMat( matA );
		System.out.print( "\nMatrix B\n" );
		printMat( matB );

		//get number of CPUs available
		int numCores = Runtime.getRuntime().availableProcessors();

		System.out.println( "Number of cores available: " + numCores );

		// starting multicore fixed threads
		final ExecutorService executor = Executors.newFixedThreadPool( 8 );

		long startTime = System.nanoTime();

		executor.execute(() -> {
			for ( int i = 0; i < MAX_THREADS; i++ ) {
				Thread multiplier = new Thread( new Multiplier( i, mat ) );
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
		
		waitFor(matC);
		
		
		
		long endTime = System.nanoTime();
		double timeTaken =  ( double ) ( endTime - startTime ) / 1_000_000_000 ;
		
		System.out.printf( "Parallel time for N is %d: %f seconds\n", N, timeTaken );

		System.out.println( "Contents of result matrix" );
		
		printMat( matC );
	}

}

class Multiplier implements Runnable {
	int i;
	MatrixMultiplication mats;

	Multiplier ( int i, MatrixMultiplication mats ) {
		this.i = i;
		this.mats = mats;
	}


	public void run () {
		for ( int l = i; l < mats.N; l += mats.MAX_THREADS ) {
			for ( int j = 0; j < mats.N; j++ ) {
				for ( int k = 0; k < mats.N; k++ ) {
					mats.matC[ l ][ j ] += mats.matA[ l ][ k ] * mats.matB[ k ][ j ];
				}
			}

			mats.matC[ l ][ mats.N ] = 1;
		}
		System.out.println( "thread " + i + " finished");
	}
}