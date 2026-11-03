# List of Programs in this directory
All programs are from Kernighan and Ritchie's C Programming book

CHAPTER 1: A TUTORIAL INTRODUCTION
-------------------------------------------------------------------------------
- [x] Exercise 1-5 - revtemp -  create the temperature conversion program to print the table in reverse order

- [ ] Exercise 1-6 - getcharverify - verify that the expression `getchar() != EOF` is 0 or 1

- [x] Exercise 1-7 - printeofchar - print out the expression `EOF` character valum

- [x] Exercise 1-8 - counthidden - Write a program to count blanks, tabs, and newlines

- [x] Exercise 1-9 - removeblanks - Write a program to copy its input to its output, replacing each string with one or more blanks by a single blank

- [x] Exercise 1-10 - visiblehidden -  write a program to copy it's input to its output, replacing each tab with `\t`, each backspace with `\b`, and each backslash by `\\`, making tabs and backspaces visible in an unambiguous way.

- [x] Exercise 1-12 - oneword - Write a program that prints its input one word per line

- [x] Exercise 1-13 - histogramwordlen - Write a program to print a histogram of the lengths of words in its input. It is easy to draw the histogram with the bars horizontal: a vertical orientation is more challenging

- [x] Exercise 1-14 - histogramfreq - Write a program to print a histogram of the frequencies of different characters in its input.

- [x] Exercise 1-15 - tempfunc - rewrite the temperature conversion program of 1.2 to use a function for conversion
Note: had some issues with this one. TODO: return and fix

- [ ] Exercise 1-16 - longestlinearbit - revise the main routine of the longest-line program so it will correctly print the length of arbitrarily long input lines, and as much as possible of the text.

- [ ] Exercise 1-17 - printall80 - Write a program to print all input lines that are longer than 80 characters

- [ ] Exercise 1-18 - removetrailing - Write a program to remove trailing blanks and tabs from each line of input, and to delete entirely blank lines

- [ ] Exercise 1-19 - reversestring - Write a function reverse(s) that reverses the character string s. Use it to write a program that reverses its input a line at a time.

- [ ] Exercise 1-20 - detab - Write a program detab that replaces tabs in the input with the proper number of blanks to space to the next tab stop. Assume a fixed set of tab stops, say every n columns. Should n be a variable or a symbolic parameter?

- [ ] Exercise 1-21 - entab - Write a program entab that replaces strings of blanks by the minimum number of tabs and blanks to achieve the same spacing. USe the same tab stops as for detab.

- [ ] Exercise 1-22. Write a program to "fold" long input lines into two or more 
shorter lines after the last non-blank character that occurs before the n-th 
column of input. Make sure your program does something intelligent with very 
long lines, and if there are no blanks or tabs before the specified column.

- [ ] Exercise 1-23. Write a program to remove all comments from a C program. Don't 
forget to handle quoted strings and character constants properly. C comments 
do not nest.

- [ ] Exercise 1-24. Write a program to check a C program for rudimentary syntax 
errors like unbalanced parentheses, brackets and braces. Don't forget about 
quotes, both single and double, escape sequences, and comments. (This program 
is hard if you do it in full generality.)

CHAPTER 2: TYPES, OPERATORS AND EXPRESSIONS
-------------------------------------------------------------------------------
- [ ] Exercise 2-1. Write a program to determine the ranges of char, short, int, and 
long variables, both signed and unsigned, by printing appropriate values from 
standard headers and by direct computation. Harder if you compute them: 
determine the ranges of the various floating-point types.

- [ ] Exercise 2-2. Write a loop equivalent to the for loop above without using && 
or ||.
[Loop context: for (i=0; i < lim-1 && (c=getchar()) != '\n' && c != EOF; ++i)]

- [ ] Exercise 2-3. Write the function htoi(s), which converts a string of hexadecimal 
digits (including an optional 0x or 0X) into its equivalent integer value. The 
allowable digits are 0 through 9, a through f, and A through F.

- [ ] Exercise 2-4. Write an alternate version of squeeze(s1,s2) that deletes each 
character in s1 that matches any character in the string s2.

- [ ] Exercise 2-5. Write the function any(s1,s2), which returns the first location 
in the string s1 where any character from the string s2 occurs, or -1 if s1 
contains no characters from s2. (The standard library function strpbrk does 
the same job but returns a pointer to the location.)

- [ ] Exercise 2-6. Write a function setbits(x,p,n,y) that returns x with the n bits 
that begin at position p set to the rightmost n bits of y, leaving the other 
bits unchanged.

- [ ] Exercise 2-7. Write a function invert(x,p,n) that returns x with the n bits 
that begin at position p inverted (i.e., 1 changed into 0 and vice versa), 
leaving the others unchanged.

- [ ] Exercise 2-8. Write a function rightrot(x,n) that returns the value of the 
integer x rotated to the right by n bit positions.

- [ ] Exercise 2-9. In a two's complement number system, x &= (x-1) deletes the 
rightmost 1-bit in x. Explain why. Use this observation to write a faster 
version of bitcount.

- [ ] Exercise 2-10. Rewrite the function lower, which converts upper case letters 
to lower case, with a conditional expression instead of if-else.


CHAPTER 3: CONTROL FLOW
-------------------------------------------------------------------------------
- [ ] Exercise 3-1. Our binary search makes two tests inside the loop, when one 
would suffice (at the price of more tests outside). Write a version with only 
one test inside the loop and measure the difference in run-time.

- [ ] Exercise 3-2. Write a function escape(s,t) that converts characters like newline 
and tab into visible escape sequences like \n and \t as it copies the string t 
to s. Use a switch. Write a function for the other direction as well, converting 
escape sequences into the real characters.

- [ ] Exercise 3-3. Write a function expand(s1,s2) that expands shorthand notations 
like a-z in the string s1 into the equivalent complete list abc...xyz in s2. 
Allow for letters of either case and digits, and be prepared to handle cases 
like a-b-c and a-z0-9 and -a-z. Arrange that a leading or trailing - is taken 
literally.

- [ ] Exercise 3-4. In a two's complement number representation, our version of itoa 
does not handle the largest negative number, that is, the value of n equal to 
-(2^(wordsize-1)). Explain why not. Modify it to print that value correctly, 
regardless of the machine on which it runs.

- [ ] Exercise 3-5. Write the function itob(n,s,b) that converts the integer n into a 
base b character representation in the string s. In particular, itob(n,s,16) 
formats n as a hexadecimal integer in s.

- [ ] Exercise 3-6. Write a version of itoa that accepts three arguments instead of 
two. The third argument is a minimum field width; the converted number must 
be padded with blanks on the left if necessary to make it wide enough.


CHAPTER 4: FUNCTIONS AND PROGRAM STRUCTURE
-------------------------------------------------------------------------------
- [ ] Exercise 4-1. Write the function strrindex(s,t), which returns the position 
of the rightmost occurrence of t in s, or -1 if there is none.

- [ ] Exercise 4-2. Extend atof to handle scientific notation of the form 
123.45e-6 where a floating-point number may be followed by e or E and an 
optionally signed exponent.

- [ ] Exercise 4-3. Given the basic framework, it's straightforward to extend the 
calculator. Add the modulus (%) operator and provisions for negative numbers.

- [ ] Exercise 4-4. Add commands to print the top element of the stack without 
popping, to duplicate it, and to swap the top two elements. Add a command to 
clear the stack.

- [ ] Exercise 4-5. Add access to library functions like sin, exp, and pow. See 
<math.h> in Appendix B, Section 4.

- [ ] Exercise 4-6. Add commands for handling variables. (It's easy to provide 
twenty-six variables with single-letter names.) Add a variable for the most 
recently printed value.

- [ ] Exercise 4-7. Write a routine ungets(s) that will push back an entire string 
onto the input. Should ungets know about buf and bufp, or should it just use 
ungetch?

- [ ] Exercise 4-8. Suppose that there will never be more than one character of pushback. 
Modify getch and ungetch accordingly.

- [ ] Exercise 4-9. Our getch and ungetch do not handle a pushed-back EOF correctly. 
Decide what their properties ought to be if an EOF is pushed back, then implement 
your design.

- [ ] Exercise 4-10. An alternate organization uses getline to read an entire input 
line; this makes getch and ungetch unnecessary. Revise the calculator to use 
this approach.

- [ ] Exercise 4-11. Modify getop so that it doesn't need to use ungetch. Hint: use 
an internal static variable.

- [ ] Exercise 4-12. Adapt the ideas of printd to write a recursive version of itoa; 
that is, convert an integer into a string by calling a recursive routine.

- [ ] Exercise 4-13. Write a recursive version of the function reverse(s), which 
reverses the string s in place.

- [ ] Exercise 4-14. Define a macro swap(t,x,y) that interchanges two arguments of 
type t. (Block structure will help.)


CHAPTER 5: POINTERS AND ARRAYS
-------------------------------------------------------------------------------
- [ ] Exercise 5-1. As written, getint treats a + or - not followed by a digit as a 
valid representation of zero. Fix it to push such a character back on the input.

- [ ] Exercise 5-2. Write getfloat, the floating-point analog of getint. What type 
does getfloat return as its function value?

- [ ] Exercise 5-3. Write a pointer version of the function strcat that we showed in 
Chapter 2: strcat(s,t) copies the string t to the end of s.

- [ ] Exercise 5-4. Write the function strend(s,t), which returns 1 if the string t 
occurs at the end of the string s, and zero otherwise.

- [ ] Exercise 5-5. Write versions of the library functions strncpy, strncat, and 
strncmp, which operate on at most the first n characters of their argument 
strings. For example, strncpy(s,t,n) copies at most n characters of t to s. 
Full descriptions are in Appendix B.

- [ ] Exercise 5-6. Rewrite appropriate programs from earlier chapters and exercises 
with pointers instead of array indexing. Good possibilities include getline 
(Chapters 1 and 4), atoi, itoa, and their variants (Chapters 2, 3, and 4), 
reverse (Chapter 3), and strindex and getop (Chapter 4).

- [ ] Exercise 5-7. Rewrite readlines to store lines in an array supplied by main, 
rather than calling alloc to maintain storage. How much faster is the program?

- [ ] Exercise 5-8. There is no error checking in day_of_year or month_day. Remedy 
this defect.

- [ ] Exercise 5-9. Rewrite the routines day_of_year and month_day with pointers 
instead of indexing.

- [ ] Exercise 5-10. Write the program expr, which evaluates a reverse Polish 
expression from the command line, where each operator or operand is a separate 
argument. For example, "expr 2 3 4 + *" evaluates 2 * (3 + 4).

- [ ] Exercise 5-11. Modify the programs entab and detab (written as exercises in 
Chapter 1) to accept a list of tab stops as arguments. Use the default tab 
settings if there are no arguments.

- [ ] Exercise 5-12. Extend entab and detab to accept the shorthand "entab -m +n" to 
mean tab stops every n columns, starting at column m. Choose convenient (for 
the user) default behavior.

- [ ] Exercise 5-13. Write the program tail, which prints the last n lines of its 
input. By default, n is 10, let us say, but it can be changed by an optional 
argument, so that "tail -n" prints the last n lines. The program should behave 
rationally no matter how unreasonable the input or the value of n. Write the 
program so it makes the best use of available storage; lines should be stored 
as in the sorting program of Section 5.6, not in a two-dimensional array of 
fixed size.

- [ ] Exercise 5-14. Modify the sort program to handle a -r flag, which indicates 
sorting in reverse (decreasing) order. Be sure that -r works with -n.

- [ ] Exercise 5-15. Add the option -f to fold upper and lower case together, so 
that case distinctions are not made during sorting; for example, a and A compare 
equal.

- [ ] Exercise 5-16. Add the -d ("directory order") option, which makes comparisons 
only on letters, numbers and blanks. Make sure it works in conjunction with -f.

- [ ] Exercise 5-17. Add a field-handling capability, so sorting may be done on fields 
within lines, each field sorted according to an independent set of options. 
(The index for this book was sorted with -df for the index category and -n for 
the page numbers.)

- [ ] Exercise 5-18. Make dcl recover from input errors.

- [ ] Exercise 5-19. Modify undcl so that it does not add redundant parentheses to 
declarations.

- [ ] Exercise 5-20. Expand dcl to handle declarations with function argument types, 
qualifiers like const, and so on.


CHAPTER 6: STRUCTURES
-------------------------------------------------------------------------------
- [ ] Exercise 6-1. Our version of getword does not properly handle underscores, 
string constants, comments, or preprocessor control lines. Write a better 
version.

- [ ] Exercise 6-2. Write a program that reads a C program and prints in alphabetical 
order each group of variable names that are identical in the first 6 characters, 
but different somewhere thereafter. Don't count words within strings and 
comments. Make 6 a parameter that can be set from the command line.

- [ ] Exercise 6-3. Write a cross-referencer that prints a list of all words in a 
document, and, for each word, a list of the line numbers on which it occurs. 
Remove noise words like "the," "and," and so on.

- [ ] Exercise 6-4. Write a program that prints the distinct words in its input 
sorted into decreasing order of frequency of occurrence. Precede each word 
by its count.

- [ ] Exercise 6-5. Write a function undef that will remove a name and definition 
from the table maintained by lookup and install.

- [ ] Exercise 6-6. Implement a simple version of the #define processor (i.e., no 
arguments) suitable for use with C programs, based on the routines of this 
section. You may also find getch and ungetch helpful.


CHAPTER 7: INPUT AND OUTPUT
-------------------------------------------------------------------------------
- [ ] Exercise 7-1. Write a program that converts upper case to lower or lower case 
to upper, depending on the name it is invoked with, as found in argv[0].

- [ ] Exercise 7-2. Write a program that will print arbitrary input in a sensible 
way. As a minimum, it should print non-graphic characters in octal or hexadecimal 
according to local custom, and break long text lines.

- [ ] Exercise 7-3. Revise minprintf to handle more of the other facilities of printf.

- [ ] Exercise 7-4. Write a private version of scanf analogous to minprintf from the 
previous section.

- [ ] Exercise 7-5. Rewrite the postfix calculator of Chapter 4 to use scanf and/or 
sscanf to do the input and number conversion.

- [ ] Exercise 7-6. Write a program to compare two files, printing the first line 
where they differ.

- [ ] Exercise 7-7. Modify the pattern finding program of Chapter 5 to take its input 
from a set of named files or, if no files are named as arguments, from the 
standard input. Should the file name be printed when a matching line is found?

- [ ] Exercise 7-8. Write a program to print a set of files, starting each new one 
on a new page, with a title and a running page count for each file.

- [ ] Exercise 7-9. Functions like isupper can be implemented to save space or to save 
time. Explore both possibilities.


CHAPTER 8: THE UNIX SYSTEM INTERFACE
-------------------------------------------------------------------------------
- [ ] Exercise 8-1. Rewrite the program cat from Chapter 7 using read, write, open, 
and close instead of their standard library equivalents. Perform experiments 
to determine the relative speed of the two versions.

- [ ] Exercise 8-2. Rewrite fopen and _fillbuf with fields instead of explicit bit 
operations. Compare code size and execution speed.

- [ ] Exercise 8-3. Design and write _flushbuf, fflush, and fclose.

- [ ] Exercise 8-4. The standard library function "int fseek(FILE *fp, long offset, 
int origin)" is identical to lseek except that fp is a file pointer instead 
of a file descriptor and return value is an int status, not a position. Write 
fseek. Make sure that your fseek coordinates properly with the buffering done 
for the other functions of the library.

- [ ] Exercise 8-5. Modify the fsize program to print the other information contained 
in the inode entry.

- [ ] Exercise 8-6. The standard library function calloc(n, size) returns a pointer 
to n objects of size size, with the storage initialized to zero. Write calloc, 
by calling malloc or by modifying it.

- [ ] Exercise 8-7. malloc accepts a size request without checking its plausibility; 
free believes that the block it is asked to free contains a valid size field. 
Improve these routines so they take more pains with error checking.

- [ ] Exercise 8-8. Write a routine bfree(p, n) that will free any arbitrary block 
p of n characters into the free list maintained by malloc and free. By using 
bfree, a user can add a static or external array to the free list at any time.
