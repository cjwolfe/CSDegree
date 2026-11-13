#ifndef STRUCTURES_H
#define STRUCTURES_H
#define NAME_SIZE 80
#define SSN_SIZE 13
#define NUM_PEOPLE 10

struct person_t{
		char name[NAME_SIZE];
		char ssn[SSN_SIZE];
		int yearOfBirth;
};

person_t getOnePerson(person_t in);
void printOnePerson(person_t input);
person_t getPeople(person_t input[], int num);
void printPeople(person_t in[], int num);

#endif
