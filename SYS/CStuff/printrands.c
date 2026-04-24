#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int min, max;

void printRandoms(int num, int minin, int maxin){
    printf("Random Number Generator for %i random numbers\n", num);

    for (int i = 0; i < num; i++){
        // int rand = rand();// % (maxin - minin + 1) + minin;

        printf("%d\n",rand() % (maxin - minin + 1) + minin);

    }
}

int main(){
    min = 0;
    max = 1000;
    printRandoms(10, min, max);

    return 1;
}