package MatrixMultiplication; 

class Multiplier1 implements Runnable {
	int i;
	MatrixMultiplication mats;

	Multiplier1 ( int i, MatrixMultiplication mats ) {
		this.i = i;
		this.mats = mats;
	}

	public void run () {
		for ( int l = i; l < mats.N; l += mats.MAX_THREADS ) {
			for ( int j = 0; j < mats.N; j++ ) {
				for ( int k = 0; k < mats.N; k++ ) {

					//System.out.println( "thread=" + i + ":l=" + l + " (j,k)=(" + j + "," +k+ ")");
					mats.matC[ l ][ j ] += mats.matA[ l ][ k ] * mats.matB[ k ][ j ];
				}
			}


		}
		System.out.println( "thread " + i + " finished");
	}
}