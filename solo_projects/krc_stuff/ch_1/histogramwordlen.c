#include <stdio.h>
/* a program to print a histogram of the lengths of words in its input*/
// the last character may be triggering one additional but let's move on.
const int length = 100;

int main(){
		int c, n, g;
		int hist[length];
		n = 0;
		for(int i = 0; i < length; i++){
			hist[i] = 0;
		}
		while((c = getchar()) != EOF)
		{
				if(c == ' '){
				++n;
				// printf("%.0d\n",g);
				g = 0;
				} else {
						if(n < length){
						++hist[n];
						}
				}
		}
		//printf("%i\n", n);

		for(int i = 0; i < length; i++){
				if(hist[i] != 0){
						printf("%i\n",hist[i]);
				}
		}
		return 0;
}
