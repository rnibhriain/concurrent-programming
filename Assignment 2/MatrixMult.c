#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <sys/time.h>

#define N 16

int A[N][N];
int B[N][N];
int C[N][N];

int main ( int argc, char * argv [ ] )
{
    int i,j,k;

    int nthreads = atoi( argv[ 1 ] );

    omp_set_num_threads(omp_get_num_procs());
    for (i= 0; i< N; i++)
        for (j= 0; j< N; j++)
	{
            A[i][j] = 2;
            B[i][j] = 2;
	}


    #pragma omp parallel for private(i,j,k) shared(A,B,C)
    for (i = 0; i < N; ++i) {
        for (j = 0; j < N; ++j) {
            for (k = 0; k < N; ++k) {
                C[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    printf( "Done!\n" );

    for ( i = 0; i < N; i++ ) {
        for ( j = 0; j < N; j++ ) {
            printf("[ %d ]", C[ i ][ j ] );
        }
        printf( "\n" );
    }

    return 0; 
}