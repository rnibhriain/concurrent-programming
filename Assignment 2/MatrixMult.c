#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <sys/time.h>

#define N 256

int matA[ N ][ N ];
int matB[ N ][ N ];
int matC[ N ][ N ];

void printMat ( int mat [N][N] ) {
    int i, j; 

    for ( i = 0; i < N; i++ ) {
        for ( j = 0; j < N; j++ ) {
            printf( "%d,", mat[ i ][ j ] );
        }
        printf( "\n" );
	}
}

int main ( int argc, char * argv [ ] )
{

    int i, j, k;

    for ( i = 0; i < N; i++ ) {
        for ( j = 0; j < N; j++ ) {
            matA[ i ][ j ] = rand() % 10 + 1;
            matB[ i ][ j ] = rand() % 10 + 1;
        }
	}

    struct timeval t0, t1;
	gettimeofday(&t0, 0);

    /* 
    * private values are i, j, k
    * Shared values are the three matrices A, B, C
    * num threads are the number of cores (can be changed)
    */
    #pragma omp parallel for private( i, j, k ) shared( matA, matB, matC ) num_threads( omp_get_num_procs() )
    for ( i = 0; i < N; ++i ) {
        for ( j = 0; j < N; ++j ) {
            for ( k = 0; k < N; ++k ) {
                matC[ i ][ j ] += matA[ i ][ k ] * matB[ k ][ j ];
            }
        }
    }

    gettimeofday(&t1, 0);
	double elapsed = (t1.tv_sec-t0.tv_sec) * 1.0f + (t1.tv_usec - t0.tv_usec) / 1000000.0f;

    printf("Parallel time for N is %d: %f seconds\n", N, elapsed );

    printf( "Done!\n" );

    printf( "\nMatrix A\n" );
    printMat( matA );
    printf( "\nMatrix B\n" );
    printMat( matB );
    printf( "\nResult! C!!\n" );
    printMat( matC );

    return 0; 
}