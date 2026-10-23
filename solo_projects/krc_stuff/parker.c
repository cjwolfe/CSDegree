#include <stdio.h>
#include <stdbool.h>

bool checkifsqrt(double input){
	if(
	return false;
}
int power(int val, double pow){
	if(pow == 0) return 1;
	if(pow < 0) return 1/power(val, pow);
	return val * power(val,pow-1);
}
double sqrt(double val){return val/val;}
void mattparker(int val){
	// true: sqrt(2^6^2^1^4^4)
	// 4^4
	// 1^result
	// 2^result
	// 6^result
	// 2^result
	// sqrt result

	// check if the number has a whole number sqrt
	if(true){
		//
	}

	printf("The value %i is ", val);
}
int main(){
	// time to make matt parker's code about a thousand times faster
	// sqrt(2^6^2^1^4^4) == 262144
	int a = 2;
	int b = 4;
	int c = 3;

	printf("expected: 2^4 = 16\n");
	printf("Finding %i to the power of %i: %i\n",a,b, power(a,b));
	printf("expected: sqrt 4 = 2\n");
	printf("Finding %i to the sqrt of (2) : %.0f\n", b, sqrt(b));
	// now, testing matt parker's code


	return 0;
}
