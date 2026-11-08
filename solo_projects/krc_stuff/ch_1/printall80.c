#include <stdio.h>
#define MAXLEN 8

int getlen( char line[], int lim);

/* Prints all input lines that are longer than 8 characters*/
int main(){
		int len, max;

		char line[1000];
		
		max = 0;
		while((len = getlen(line, 1000)) > 0)
				if (len > MAXLEN)
						printf("%s",line);

		return 0;
}

int getlen(char s[], int lim)
{
		int c, i;
		for (i = 0; i < lim-1 && (c = getchar())!= EOF && c != '\n'; ++i)
				s[i] = c;
		if(c == '\n') {
				s[i] = c;
				++i;
		}
		s[i] = '\0';
		return i;
}

