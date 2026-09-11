.orig 3000 ;begin at 3000
LD R0, #6	;
ld r1, #6;
not r1, r1;
add r1, r1, #1;
add r0,r0, r1;
ld r1, #6;
not r1,r1;
add r1, r1, #1;
add r0, r0, r1;
ld r1, #6;
not r1, r1;
add r1, r1, #1;
add r0, r0, r1;
; print to output
HALT ; f025


10 ; start with this value
4 ; subtract this value
2 ; subtract this value
1 ; subtract this value
0 ; store the result here
