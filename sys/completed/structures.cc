#include <stdio.h>
#include <string.h>
#include "structures.h"

void getOnePerson(person_t *p) {
    printf("Enter full name: ");
    scanf("%79s", p->name);
    
    printf("Enter ssn: ");
    scanf("%12s", p->ssn);
    
    printf("Enter year of birth: ");
    scanf("%d", &p->yearOfBirth);
}

void printOnePerson(person_t p) {
    printf("%s:%s:%d\n", p.name, p.ssn, p.yearOfBirth);
}

void getPeople(person_t people[], int numPeople) {
    for (int i = 0; i < numPeople; i++) {
        getOnePerson(&people[i]);
    }
}

void printPeople(person_t people[], int numPeople) {
    for (int i = 0; i < numPeople; i++) {
        printOnePerson(people[i]);
    }
}
