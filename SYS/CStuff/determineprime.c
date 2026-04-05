#include <stdio.h>
#include <string.h>
#include <math.h>

//todo: fix why the arrays only go up to 6/7
// figure out how to determine prime
// figure out how to get math cos(n) to work

const int primes[] = 
        {2, 3,	5, 7,	11, 13,	17,	19,	23,	29,	
        31,	37,	41,	43,	47,	
        53,	59,	61,	67,	71};


int factorial(int n){
    if(n == 0) return 1;
    else
    return n * factorial(n-1);
}

int factorit(int n){
    return (factorial(n + 1));
}

int min(int i, int j){
    if(i < j){
        return i;
    } else return j;
}




void printArray(int *arr1, int len1){
    printf("Count\tmine\tPrimes\n");

    int primesLen = sizeof(primes) / sizeof(primes[0]);

    int length = (len1 < primesLen) ? len1 : primesLen;

    // length = sizeof(arr1);
    // length = sizeof(arr2);
    
    for(int i = 0; i < length; i++){
        
        printf("%i\t%i\t%i\n",i + 1,arr1[i],primes[i]);
        // printf("length == %i\n",length);

    }
}



int main(){

    int array[20];
    int numElements = sizeof(array)/sizeof(array[0]);
    for(int i = 0; i < numElements; i++){
        array[i] = i;
    }
    // memset(array, 1, sizeof(array));






    for(int i = 0; i < numElements; i++){
        // array[i] = factorial(array[i]);
        array[i] = array[i] * array[i];
    }


    // for(int i = 0; i < 20; i++){

    // }
    printArray(array, numElements);

    // printf("length = %i",length);

    return 0;
}