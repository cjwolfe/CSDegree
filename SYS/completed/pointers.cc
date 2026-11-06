#include <stdio.h>

int countCharRange(char *str, char c1, char c2){
		int count = 0;
		char min = c1;
		char max = c2;
		if (c1 > c2){ min = c2; max = c1;}
		while(*str){
				if(*str >= min && *str <= max){
						count++;
				}
				str++;
		}
		return count;
}

char *findStringEnd(char *str) {
    while (*str) {
        str++;
    }
    return str;
}

void doubleString(char *str) {
    char *src = str;
    char *end = findStringEnd(str);
    char *dst = end;

    while (src < end) {
        *dst = *src;
        src++;
        dst++;
    }

    *dst = '\0';
}

void palindrome(char *str) {
    char *end = findStringEnd(str);
    char *dst = end;
    char *src = end - 1;

    while (src >= str) {
        *dst = *src;
        src--;
        dst++;
    }

    *dst = '\0';
}
