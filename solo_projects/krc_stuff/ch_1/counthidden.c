#include <stdio.h>

int main(){
	int c;
	int t = 0, s = 0;
	while((c = getchar()) != EOF){
	if(c == '\t'){
			t++;
	} else if (c == ' '){
			s++;
	}

	}
	printf("The number of tabs and spaces are %i and %i respectively\n",t,s);
	printf("The EOF character is %d",EOF);
	return 0;
}
