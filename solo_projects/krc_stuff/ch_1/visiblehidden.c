#include <stdio.h>
/* prints the character the user input to the screen, but replaces \ts with \t, etc */
int main(){
	int c;
	while(c != EOF){
		c = getchar();
		if(c == '\t'){
			   putchar('\\');
		   putchar('t');}		
		if(c == '\b'){
			   putchar('\\');
		   putchar('b');}	   
		if(c == '\''){
				putchar('\\');}
		putchar(c);
	}
	return 0;
}
