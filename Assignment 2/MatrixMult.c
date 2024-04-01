#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <sys/time.h>

#define N 4096

int matA[ N ][ N ];
int matB[ N ][ N ];
int matC[ N ][ N ];

void multMatrix ( int numThreadsPerCore ) {
    

}

int main ( int argc, char * argv [ ] )
{

    int i, j, k;

    for ( i = 0; i < N; i++ )
        for ( j = 0; j < N; j++ ) {
            matA[ i ][ j ] = 2;
            matB[ i ][ j ] = 2;
	}

    clock_t begin_time = clock();

    /* 
    * private values are i, j, k
    * Shared values are the three matrices A, B, C
    * num threads are the number of cores by the number of threads entered by user
    */
    //#pragma omp parallel for private( i, j, k ) shared( matA, matB, matC ) num_threads( omp_get_num_procs() * atoi( argv[ 1 ] ) )
    for ( i = 0; i < N; ++i ) {
        for ( j = 0; j < N; ++j ) {
            for ( k = 0; k < N; ++k ) {
                matC[ i ][ j ] += matA[ i ][ k ] * matB[ k ][ j ];
            }
        }
    }

    printf("Parallel time for N is %d: %d\n", N, clock () - begin_time  / CLOCKS_PER_SEC );

    printf( "Done!\n" );
    /*
    for ( i = 0; i < N; i++ ) {
        for ( j = 0; j < N; j++ ) {
            //printf("%d ", matC[ i ][ j ] );
        }
       // printf( "\n" );
    }*/

    return 0; 
}