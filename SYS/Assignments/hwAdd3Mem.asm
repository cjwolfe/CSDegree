;add 3 memory locations
.origin 3000	;starting address 3000
AND R3, #0
ADD R3, R0, R1	; 3000, 0001,0110,0000,0001 1601
ADD R3, R3, R2	; 3001, 0001,0110,1100,0010 16da? 16C2?
ST R3, #4 ; 3002 0011 011 000000100 3604
HALT ; f025 3003 trap x 25, act f019 
;3004 r0
;3005 r1
;3005 r2
;3006 r3
;3006 r4

.origin 3000 	; starting address 3000
LD R0, #6 	; Load first input into r0
LD R1, #6 	; load second input into r1
LD R2, #6 	; load third input into r2
ADD R3, R0, R1 	; add first two inputs into r3
ADD R3, R3, R2	; add third input and r3 into r3
ST R3, #4=	; store sum in 4th memory slot after halt
HALT		; HALT
