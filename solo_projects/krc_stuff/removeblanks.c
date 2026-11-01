#include <stdio.h>
/* remove blanks will remove blanks from character input and return it to the console.*/
int main(){
	int c;
	while(c != EOF){
		c = getchar();
		if(c == ' ' | c == '\t'){

		} else {
				putchar(c);
		}
	}
	return 0;
}
