#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <sys/time.h>


int main ( int argc, char * argv [ ] )
{

    int nthreads = atoi( argv[ 1 ] );
    
    #pragma omp parallel num_threads ( nthreads )
    printf( "Hello from thread %d of %d \n", omp_get_thread_num( ), omp_get_num_threads( ) ) ;

    printf( "Done!\n" );

    return 0; 
}