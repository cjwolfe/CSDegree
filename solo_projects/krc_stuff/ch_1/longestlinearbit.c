#include <stdio.h>
#define MAXLINE 1000

int getarbitraryline( char line[], int maxline);
void copy(char to[], char from[]);

/* Longest linear bit - the main routine of the longest line program so that it will correctly print the length of arbitrarily long input lines, and as much as possible of the text*/
int main(){
		int len;
		int max;
		char line[MAXLINE];
		char longest[MAXLINE];

		max = 0;
		while((len = getarbitraryline(line, MAXLINE)) > 0)
				if (len > max) {
						max = len;
						copy(longest, line);
				}
		if (max > 0){ 
				printf("Length of longest line: %i\t", max );
				printf("%s", longest);
		}
		return 0;
}

/* getarbitraryline: read a line into s, return length */
int getarbitraryline(char s[], int lim)
{
		int c, i;
		for (i = 0; i < lim-1 && (c=getchar()) !=EOF && c !='\n'; i++)
				s[i] = c;
		if (c == '\n') {
				s[i] = c;
				++i;
		}
		s[i] = '\0';
		return i;
}

/* copy: copy 'from' into 'to'; assume to is big enough */
void copy(char to[], char from[]){
		int i;
		i = 0;
		while((to[i] = from[i]) != '\0')
				++i;

}
