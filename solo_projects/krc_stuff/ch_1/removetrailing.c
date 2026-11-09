#include <stdio.h>

int getlen( char line[], int lim);
char fixlen(char s[]);

/* removes trailing blanks and tabas from each line of input, and deletes entirely blank lines*/
// still broken
int main(){
		int c;
		int len, max;

		char line[1000];
		// todo: fixj
		char newline[1000];
		
		while((len = getlen(line, 1000)) > 0)
		{
				newline = *fixlen(line, 1000);
				//todo: fix
				printf("%s", newline);
		}

		return 0;
}

char fixlen(char s[], int len){
		int c;
		int i = 0;
		while(i < 1000){
				c = getchar();
		}

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
		for (i = 0; i < lim-1 && (c = getchar())!= EOF && c != '\n' && c != '\t'; ++i)
				s[i] = c;
		// what is this for?
		/*
		if(c == '\n') {
				s[i] = c;
				++i;
		}
		*/
		++i;
		s[i] = '\0';
		return i;
}

