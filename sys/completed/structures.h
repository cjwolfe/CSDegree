#ifndef STRUCTURES_H
#define STRUCTURES_H

#define NAME_SIZE 80
#define SSN_SIZE 13
#define NUM_PEOPLE 10

typedef struct {
    char name[NAME_SIZE];
    char ssn[SSN_SIZE];
    int yearOfBirth;
} person_t;

void getOnePerson(person_t *p);
void printOnePerson(person_t p);
void getPeople(person_t people[], int numPeople);
void printPeople(person_t people[], int numPeople);

#endif
