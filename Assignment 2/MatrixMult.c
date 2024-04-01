#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <sys/time.h>

#define N 10

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

    clock_t begin_time = clock();

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

    printf("Parallel time for N is %d: %d\n", N, clock () - begin_time  / CLOCKS_PER_SEC );

    printf( "Done!\n" );

    printf( "\nMatrix A\n" );
    printMat( matA );
    printf( "\nMatrix B\n" );
    printMat( matB );
    printf( "\nResult! C!!\n" );
    printMat( matC );

    return 0; 
}