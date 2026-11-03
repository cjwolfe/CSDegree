#include <stdio.h>

int convert(int f){
		return((5 * (f -32))/9);
}

int main(){
		int t;
		printf("Please input a temperature to convert to c\n");
		printf("Fahr\tCels\n");
		for(int i = 0; i < 300; i += 10){
				printf("%i\t%i\n",i, convert(i));
		}
		
		/*
		while((getchar()) != EOF){
				
				putchar(getchar());
				printf("char input:%i\n",t);
				t = getchar();
				printf("char input:%i\n",t);
				if(t != '\n' || t != 10){
				printf("Celsius\t:%i\tFahr\t:%i\n", convert(t),t);
				}
		}
		*/
		return 0;
}
