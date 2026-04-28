#include <stdio.h>

/*Simple program to generate and then print the fibonnaci sequence*/
#define ARRLIM  100 /*array limit size*/
    double array[ARRLIM];
    double duparray[ARRLIM];

void printArrays(void){
    printf("Count\tArray 1\tArray 2\n");

    for(int i = 0; i < ARRLIM; i++){
        
        printf("%i\t%G\t%G\n",i,array[i],duparray[i]);

    }
}

int main(){

    // double array[ARRLIM];
    // double duparray[ARRLIM];

    array[0]= 0.0;
    array[1]= 1.0;

    // double duparray[] *= array;
    for(int i = 2; i < ARRLIM; i++){
        array[i] = array[i - 1] + array[i - 2];
    }

    for(int i = 0; i < ARRLIM; i++){
        duparray[i] = array[i] *.05;

    }
    

    printArrays();


    return 1;

}