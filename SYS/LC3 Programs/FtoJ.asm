    .orig x3000
    LEA R0, PROMPT
    PUTS
    JSR GETFtoJ
    HALT
PROMPT  .stringz "Enter a character from F to J: "   
    
    
GETFtoJ    
    ;Save registers
    ST R3, GFJ_SR3
    ST R4, GFJ_SR4
    ST R7, GFJ_SR7

    ;Load constants
    LD R3, GFJ_F  ; Load -F into R3
    LD R4, GFJ_J  ; Load -J into R4

GFJ_TOP
    GETC            ;Get a character from keyboard into R0
    
    ADD R0, R3, R0  ; Check if R0 < 'F'
    BRN GFJ_TOP     ; If it is, it is bad, go to TOP to get new character.
    
    ADD R0, R4, R0  ;Check to see if R0 <= 'J'
    BRZ GFJ_PRINT   ;If R0 is <= 'J' it is good, print and return.
    
    BRNZP GFJ_TOP   ;Wasnt in range F to J so go get another character.

GFJ_PRINT   
    OUT             ;Print what is in R0
GFJ_END
    
    ;Restore registers
    LD R3, GFJ_SR3
    LD R4, GFJ_SR4
    LD R7, GFJ_SR7
    RET    
    
GFJ_SR3 .fill 0    
GFJ_SR4 .fill 0    
GFJ_SR7 .fill 0    
GFJ_F .fill -70        
GFJ_J .fill -74            
    
    .end