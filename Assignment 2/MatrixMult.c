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
    int i, j, k;

    for ( i = 0; i < N; i++ )
        for ( j = 0; j < N; j++ ) {
            matA[ i ][ j ] = 2;
            matB[ i ][ j ] = 2;
	}

    clock_t begin_time = clock();
    printf("Hello\n" );

    /* 
    *
    * Shared values are the three matrices A, B, C
    */
    #pragma omp parallel for private( i, j, k ) shared( matA, matB, matC ) num_threads( omp_get_num_procs() * numThreadsPerCore )
    for ( i = 0; i < N; ++i ) {
        for ( j = 0; j < N; ++j ) {
            for ( k = 0; k < N; ++k ) {
                matC[ i ][ j ] += matA[ i ][ k ] * matB[ k ][ j ];
            }
        }
    }

    printf("Parallel time for %d: %d\n", N, clock () - begin_time  / CLOCKS_PER_SEC );

}

int main ( int argc, char * argv [ ] )
{
    
    multMatrix( atoi( argv[ 1 ] ) );

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