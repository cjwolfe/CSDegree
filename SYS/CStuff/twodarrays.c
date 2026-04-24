#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*simple program to create a two d array and loop through it*/

#define arrsize 16
char arr1[arrsize][arrsize];
// #define arrsize sizeof(arr1)/sizeof(arr1[0])

// char arr2[arrsize];

const int min = 65;
const int max = 90;
#define randmod (max - min + 1)


int main(){

    srand(time(NULL));

    printf("Two Dimensional Arrays testing: Filling in random characters\n");


    for(int i = 0; i < arrsize; i++){
        printf("|");
        for (int j = 0; j < arrsize; j++){
            arr1[i][j] = rand() % randmod + min;
            // arr1[j] = rand() % randmod + min;

            // printf("j '%c'\t",arr2[j]);
            printf("-'%c'-",arr1[i][j]);


        }
        printf("|\n");

    }



    return 1;
}
