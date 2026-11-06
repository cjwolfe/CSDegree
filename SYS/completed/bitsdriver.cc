#include <stdio.h>

int highLow(int hi, int lo);
int countPairs(int pair);
void printDashOct(int input);

int main(){
	int p1 = 0x045f3e47;
	int p2 = 0xab001f23;
	printf("highLow(0x%08x,0x%08x) returned 0x%08X\n",p1,p2, highLow(p1,p2));
	p1 = 0xD8000000;
	printf("countPairs(0x%08x) returned %d\n", p1, countPairs(p1));

	//p1 = 0x12345678;
	p1 = 0xC0000000;
	printf("printDashOct(0x%08X)printed \n", p1);
	printDashOct(p1);

	return 0;
}
