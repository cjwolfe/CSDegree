#include <stdio.h>

/* fahrenheit/celsius table example from book */
int main(){
    int fahr, celsius;
    int lower, upper, step;

    lower = 0;
    upper = 240;
    step = 24;

    fahr = lower;
    while (fahr <= upper){
        celsius = 5 * (fahr-32) /9;
        printf("%d\t%d\n", fahr, celsius);
        fahr += step;
    }
    return 0;
}