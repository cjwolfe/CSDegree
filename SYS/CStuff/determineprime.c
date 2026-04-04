#include <stdio.h>
#include <string.h>


int isprime(int n){
    if(n == 1){
        return 0;
    } else if(n %2 == 0){
        return 1;
    } else {
        int m = n-1;
        return isprime(m);
    }


}

int max(int i, int j){
    if(i < j){
        return j;
    } else return i;
}


void printArrays(int *arr1, int *arr2){
    printf("Count\tmine\tPrimes\n");

    int length = max(sizeof(arr1), sizeof(arr2));

    // length = sizeof(arr1);
    // length = sizeof(arr2);
    
    for(int i = 0; i < length; i++){
        
        printf("%i\t%i\t%i\n",i,arr1[i],arr2[i]);
        printf("length == %i\n",length);

    }
}



int main(){

    int array[20];
    // memset(array, 1, sizeof(array));


    int primes[20] = 
        {2, 3,	5, 7,	11,
        13,	17,	19,	23,	29,	
        31,	37,	41,	43,	47,	
        53,	59,	61,	67,	71};


    // for(int i = 0; i < sizeof(array)-1; i++){
    //     array[i] = i;
    // }



    printArrays(array,primes);

    // printf("length = %i",length);

    return 0;
}