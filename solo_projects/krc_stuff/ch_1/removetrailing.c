#include <stdio.h>
#define MAXLEN 8

int getlen( char line[], int lim);
char fixlen(char s[]);

/* removes trailing blanks and tabas from each line of input, and deletes entirely blank lines*/
int main(){
		int c;
		int len, max;

		char line[1000];
		// todo: fixj
		char newline[1000];
		
		while((len = getlen(line, 1000)) > 0)
		{
				newline = *fixlen(line);
				//todo: fix
				printf("%s", newline);
		}

		return 0;
}

char fixlen(char s[]){
		int c, i;
		for (i = 0; (c = getchar())!= EOF && c != '\n' && c != '\t' && c != ' '; i++){
				s[i] = c;
		}

		//for (i = 0; i < 999; i++){
				//s[i] = 'f';}
		return *s;
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

