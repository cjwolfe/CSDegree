#include <stdio.h>
/*Print a histogram of the characters of words in its input*/
int main (){
		const int length = 128;
		int c, n;
		int hist[length];
		for(int i = 0; i < length; i++){hist[i] = 0;}
		while((c = getchar()) != EOF)
		{
				if(c == ' '){
						//new word
						++n;
				} else {
						if(c < length){
								++hist[c];
						}
				}
		}
		printf("Char\t: Count");
		for(int i = 0; i < length; i++){
				if(hist[i] != 0){
						printf("%c \t: %i\n",i,hist[i]);
				}
		}
		return 0;
}
