#include <stdio.h>
void question1(){
	int x = 5;
	while(x == 5)
	{
		x ++;
	}

	printf("%d",x);
}
void question2(){
	//prints 4
	int i = 3;
	if(i == 2)
		printf("%d",i);
	else
		printf("%d", i+1);
}

void question3(){
	// prints 0
	int count = 0;
	int i = 0;
	while(i < count)
		i++;
	printf("%d\n",i);
}

void question4(){
	// prints 3
	int count = 3;
	int i;
	//for( i = 0; i < count; i++);
	do{
		i++;
	} while (i<count);
	printf("%d\n",i);
}

void question45(){
	//prints 0
	int count = 0;
	int i = 0;
	for(i = 0; i < count; i++)
		i++;
	printf("%d\n",i);
}
void question5(){
	// prints WXYZ
	char i;
	for(i = 'A'; i <= 'Z'; i++)
	{
		if (i < 'W') continue;
		printf("%c",i);
	}
}
void question6(){
	//prints ABC
	int score = 95;
	int grade = score > 90? 1:score > 80? 2: 3;
	switch (grade){
		case 1:
			printf("A");
		case 2:
			printf("B");
		default:
			printf("C");
	}
}
int main(){
	
	//question1();
	//printf("\n");
	question2();
	//printf("\n");
	//question3();
	//printf("\n");
	//question4();
	//question45();
	//printf("\n");
	//question5();
	//question6();
	return 0;
}
