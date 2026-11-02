#include <stdio.h>

/* copy input to output */
int main(){
	int c;

	while ((c = getchar()) !=EOF){
	printf("character read: %c\n",c);
	
	}
	/* sending Ctrl+D to the command line will give the EOF*/
	printf("the eof character is: %d",EOF);
	return 0;
}
