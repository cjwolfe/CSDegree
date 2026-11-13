#include <stdio.h>

int checkprime(int pr){
		// returns the numbers that the number is divisible by...
		// maybe by taking the number and figuring out if it modulos by itself by some number?
		// example - 6. 2 * 3 = 6
		// could go at it from 6 * 1 = 6
		// 5 * 1.2~ = 6
		// 4 * 1.5 = 6
		// 3 * 2 = 6
		// stop here, we have determined that 6 is not prime. Escape and return -1
		// example - 5. 5 is prime
		// 4 * 1.25 = 5
		// 3 * 1.45~ = 5
		// 2 * 2.5 = 5
		// 1 * 5 = 5
		// stop here, we reached 1
		// thus
		// check if passed int is 1
		// if it is, we need to exit
		// then call checkprime
		// 
		// what is pr-1 * n approaching? What is n approaching?
		// (n is approaching the sqrt of the number)
		//
		return -1;
		// sieve of erasthemis v1
		// pass a number and have access to a global list of primes (the sieve)
		// check if the number (prompted by recursion) is in the sieve
		// check each number in the sieve % by the passed number
		//
		//
}
char* isPrime(int i){
		return "yes";
}
int main(){
		int top = 10;

		printf("Number\tIs it Prime?\n");

		for(int i = 0; i < top; i++){
			printf("%i\t%s\n", i, isPrime(i));
		}

		return 0;
}
