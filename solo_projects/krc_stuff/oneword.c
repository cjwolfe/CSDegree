#include <stdio.h>

/* one shot, one kill */
/* prints input to output but replaces spaces with newlines , aka prints each word to a new line.*/
int main(){
	int c;
	while (c != EOF){
			c = getchar();
		if(c == ' '){
			c = '\n';
		}
		putchar(c);
	}
	return 0;
}
