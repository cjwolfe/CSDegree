#include <stdio.h>

void init(int array[], int len);

int main(){
	int array[5];
	init(array, 5);
	printf("%d", array[3]);
}
void init(int array[], int len){
	int i;
	for (i = 0; i < len; i++)
		array[i] = -1;
}
