//You shouldn't need any prototypes, #defines, or 
//typedefs in main.cc.  They should all be in structures.h
//and the following line will automatically bring them
//into main.cc.
#include "structures.h"
#include <stdio.h>
#include <string.h>
//#include <structures.h>
//person_t getOnePerson(person_t in);
//void printOnePerson(person_t input);
//person_t getPeople(person_t input[], int num);
//void printPeople(person_t *in, int num);

int main()
{
	person_t p0, p1, p2;
	person_t people[NUM_PEOPLE]; //Big enough for 10

	//Getting first person into p0.
	getOnePerson(&p0);

	//Getting next two p1 and p2 people using an array.
	people[0] = p1;
	people[1] = p2;
	getPeople(people, 2);
	
	//Printing the first person.
	printOnePerson(p0);

	//printing from the array.
	printPeople(people, 2);

	return 0;
}

person_t getOnePerson(person_t input){
		return input;
}
void printOnePerson(person_t input){
		printf("this is sample output");
}
person_t getPeople(person_t input[], int num){
		return *input;
}
void printPeople(person_t p, int num){
		printf("this is more sample output");
}
