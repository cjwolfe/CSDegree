#include <stdio.h>
#include <string.h>
void swap(int a, int b);

char * foo1(char str[])
{
		int n = strlen(str);
		int i;
		for (i = 0; i < n; i++, n--)
		{
		str[i] = str[n-1];
		}
		return str;
}

char * foo2(char str[])
{
		int n = strlen(str);
		int i;
		for (i = 0; i < n; i++, n--)
		{
		char c = str[i];
		str[i] = str[n-1];
		str[n-1] = c;
		}
		return str;
}

char * foo3(char str[])
{
		int n = strlen(str);
		int i;
		for (i = 0; i < n; i++)
		{
		if (i < n/2)
		str[i] = str[n-1];
		}
		return str;
}
int main(){
	char str[] = "abcdefg";
	char *resultStr = foo1(str);
	printf("foo 1: %s\n", resultStr);	
	resultStr = foo2(str);
	printf("foo 2: %s\n", resultStr);	
	resultStr = foo3(str);
	printf("foo 3: %s\n", resultStr);	
	int n = 3, m = 4;
	swap (n,m);
	printf("n = %d, m = %d\n", n, m);

	return 0;

}

void swap(int a, int b){
		int t = a;
		a = b;
		b = t;
}
