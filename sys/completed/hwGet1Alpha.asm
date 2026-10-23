; hwGet1Alpha.asm
; CS 2450 - Homework: Get 1 Alphabetic Character

        .ORIG   x3000
.sub GETALPHA
; MAIN PROGRAM
        LEA     R0, PROMPT
        PUTS
        JSR     GETALPHA        ; Get first character
        JSR     GETALPHA        ; Get second character
        HALT

PROMPT  .STRINGZ "Enter two letters: "


; =========================================================================
; SUBROUTINE: GETALPHA
; Reads a single valid character (A-Z, a-z, '_', or newline).
; - Prints and returns R0 for A-Z, a-z, and '_'
; - Does NOT print newline (x0A), but returns R0 = 10
; - Ignores any invalid input and loops until a valid character is entered
; Callee-saved: R1, R7 (R0 holds return value)
; =========================================================================
GETALPHA	
        ; 1. Save modified registers (preserve R7 for subroutine return)
        ST      R1, SAVE_R1
        ST      R7, SAVE_R7

        ; 2. Input loop: get a character from user
GET_LOOP	
        GETC                    ; Overwrites R7; character placed in R0

        ; 3. Check for Newline (ASCII 10) -> Return without printing
        ADD     R1, R0, #-10
        BRz     RESTORE_REGS

        ; 4. Check for Underscore (ASCII 95) -> Print and return
        LD      R1, NEG_UNDERSCORE
        ADD     R1, R0, R1
        BRz     PRINT_CHAR

        ; 5. Check if R0 < 'A' (ASCII 65) -> Invalid, retry
        LD      R1, NEG_A
        ADD     R1, R0, R1
        BRn     GET_LOOP

        ; 6. Check if R0 <= 'Z' (ASCII 90) -> In range ['A', 'Z'], valid
        LD      R1, NEG_Z
        ADD     R1, R0, R1
        BRnz    PRINT_CHAR

        ; 7. Check if R0 < 'a' (ASCII 97) -> Between 'Z' and 'a', invalid, retry
        LD      R1, NEG_LOWER_A
        ADD     R1, R0, R1
        BRn     GET_LOOP

        ; 8. Check if R0 <= 'z' (ASCII 122) -> In range ['a', 'z'], valid
        LD      R1, NEG_LOWER_Z
        ADD     R1, R0, R1
        BRnz    PRINT_CHAR

        ; 9. Anything after 'z' -> Invalid, retry
        BR      GET_LOOP

        ; 10. Print valid character
PRINT_CHAR
        OUT

        ; 11. Restore registers
RESTORE_REGS
        LD      R1, SAVE_R1
        LD      R7, SAVE_R7

        ; 12. Return to caller
        RET


; =========================================================================
; SUBROUTINE DATA & CONSTANTS
; =========================================================================
SAVE_R1         .BLKW   #1
SAVE_R7         .BLKW   #1

NEG_UNDERSCORE  .FILL   #-95
NEG_A           .FILL   #-65
NEG_Z           .FILL   #-90
NEG_LOWER_A     .FILL   #-97
NEG_LOWER_Z     .FILL   #-122

        .END
