#include <stdio.h>
#include <cstdio>

int highLow(int hi,int lo){
	// three lines
	// this might actually work?
	int ans = hi >> 4;
	int ans2 = lo << 4;
	return ans << 4 | ans2 >> 4;	
}

int countPairs(int pair){
	//nine lines
	// this is also junk
	return pair--;
}

void printDashOct(int input){
	//thirteen lines
	// this is just junk
	int mask = 0x01;
	for (int i = 0; i < 8; i++){
		int result = input & mask;
		printf("-%d", result);
		input = input >> 1;
	}

}

