	.ORIG	x3000
	JSR	GETHD
	JSR	GETHD
	HALT

GETHD
        ST      R1, GD_SAVER1
        ST      R2, GD_SAVER2
        ST      R7, GD_SAVER7

GD_LOOP
	GETC
	LD	R1, GD_NEG48
	ADD	R2, R0, R1
	BRn	GD_LOOP

	ADD	R1, R1, #-9
	BRp	GD_CHK_UPPER
	
	OUT
	ADD	R0, R2, #0
	BRnzp	GD_DONE

GD_CHK_UPPER
	LD	R1, GD_NEG65
	ADD	R2, R0, R1
	BRn	GD_LOOP
	
	ADD	R1, R2, #-5
	BRp	GD_CHK_LOWER

	OUT 
	ADD	R0, R2, #10
	BRnzp	GD_DONE

GD_CHK_LOWER
	LD R1, GD_NEG97
	ADD	R2, R0, R1
	BRn	GD_LOOP

	ADD	R1, R2, #-5
	BRp	GD_LOOP

	OUT
	ADD	R0, R2, #10

GD_DONE
	LD	R1, GD_SAVER1
	LD	R2, GD_SAVER2
	LD	R7, GD_SAVER7
	RET

GD_SAVER1	.BLKW 1
GD_SAVER2	.BLKW 1
GD_SAVER7	.BLKW 1
GD_NEG48	.FILL #-48
GD_NEG65	.FILL #-65
GD_NEG97	.FILL #-97

;MULT
MULT
	ST	R1, ML_SAVER1
	ST	R2, ML_SAVER2
	ST	R3, ML_SAVER3
	ST	R4, ML_SAVER4

	AND	R0, R0, #0

	ADD	R1, R1, #0
	BRz	ML_DONE
	ADD	R2, R2, #0
	BRz	ML_DONE

	ADD	R3, R1, #0
	ADD	R4, R2, #0

	NOT	R2, R2
	ADD	R2, R2, #1
	ADD	R2, R1, R2
	BRzp	ML_LOOP

	LD	R3, ML_SAVER2
	LD	R4, ML_SAVER1

ML_LOOP
	ADD	R0, R0, R3
	ADD	R4, R4, #-1
	BRp	ML_LOOP

ML_DONE
	LD	R1, ML_SAVER1
	LD	R2, ML_SAVER2
	LD	R3, ML_SAVER3
	LD	R4, ML_SAVER4
	RET

ML_SAVER1	.BLKW1
ML_SAVER2	.BLKW1
ML_SAVER3	.BLKW1
ML_SAVER4	.BLKW1

GETH4
        ST      R1, G4_SAVER1
        ST      R2, G4_SAVER2
        ST      R3, G4_SAVER3
        ST      R7, G4_SAVER7

        ; --- Digit 1 (most significant) ---
        JSR     GETHD
        ADD     R3, R0, #0      ; R3 = d0

        ; --- Digit 2 ---
        ADD     R1, R3, #0
        AND     R2, R2, #0
        ADD     R2, R2, #16
        JSR     MULT            ; R0 = R3 * 16
        ADD     R3, R0, #0

        JSR     GETHD           ; R0 = d1
        ADD     R3, R3, R0      ; R3 = (d0 * 16) + d1

        ; --- Digit 3 ---
        ADD     R1, R3, #0
        AND     R2, R2, #0
        ADD     R2, R2, #16
        JSR     MULT            ; R0 = R3 * 16
        ADD     R3, R0, #0

        JSR     GETHD           ; R0 = d2
        ADD     R3, R3, R0      ; R3 = ((d0 * 16) + d1) * 16 + d2

        ; --- Digit 4 (least significant) ---
        ADD     R1, R3, #0
        AND     R2, R2, #0
        ADD     R2, R2, #16
        JSR     MULT            ; R0 = R3 * 16
        ADD     R3, R0, #0

        JSR     GETHD           ; R0 = d3
        ADD     R3, R3, R0      ; R3 = final 16-bit unsigned value

        ; Print newline (ASCII 10)
        LD      R0, G4_NEWLINE
        OUT

        ; Return final sum in R0
        ADD     R0, R3, #0

        LD      R1, G4_SAVER1
        LD      R2, G4_SAVER2
        LD      R3, G4_SAVER3
        LD      R7, G4_SAVER7
        RET

; Local data for GETH4
G4_SAVER1   .BLKW 1
G4_SAVER2   .BLKW 1
G4_SAVER3   .BLKW 1
G4_SAVER7   .BLKW 1
G4_NEWLINE  .FILL x000A

        .END
