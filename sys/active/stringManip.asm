.ORIG   x3000
.sub SWITCHCASE
.sub COUNTCHAR
        JSR    COUNTCHAR 
        HALT

SWITCHCASE
        ST      R0, SW_SAVE_R0
        ST      R1, SW_SAVE_R1
        ST      R2, SW_SAVE_R2
        ST      R3, SW_SAVE_R3

SW_LOOP
        LDR     R1, R0, #0
        BRz     SW_DONE

        LD      R2, SW_NEG_A
        ADD     R2, R1, R2
        BRn     SW_NEXT

        LD      R2, SW_NEG_Z
        ADD     R2, R1, R2
        BRp     SW_CHECK_LOWER

        LD      R3, SW_DIFF
        ADD     R1, R1, R3
        STR     R1, R0, #0
        BRnzp   SW_NEXT

SW_CHECK_LOWER
        LD      R2, SW_NEG_LA
        ADD     R2, R1, R2
        BRn     SW_NEXT

        LD      R2, SW_NEG_LZ
        ADD     R2, R1, R2
        BRp     SW_NEXT

        LD      R3, SW_NEG_DIFF
        ADD     R1, R1, R3
        STR     R1, R0, #0

SW_NEXT
        ADD     R0, R0, #1
        BRnzp   SW_LOOP

SW_DONE
        LD      R0, SW_SAVE_R0
        LD      R1, SW_SAVE_R1
        LD      R2, SW_SAVE_R2
        LD      R3, SW_SAVE_R3
        RET

SW_SAVE_R0     .BLKW   #1
SW_SAVE_R1     .BLKW   #1
SW_SAVE_R2     .BLKW   #1
SW_SAVE_R3     .BLKW   #1

SW_NEG_A       .FILL   #-65
SW_NEG_Z       .FILL   #-90
SW_NEG_LA      .FILL   #-97
SW_NEG_LZ      .FILL   #-122
SW_DIFF        .FILL   #32
SW_NEG_DIFF    .FILL   #-32


COUNTCHAR
        ST      R1, CC_SAVE_R1
        ST      R2, CC_SAVE_R2
        ST      R3, CC_SAVE_R3
        ST      R4, CC_SAVE_R4
        ST      R5, CC_SAVE_R5

        AND     R2, R2, #0

        LD      R4, CC_NEG_A
        ADD     R4, R0, R4
        BRn     CC_NOT_UPPER

        LD      R4, CC_NEG_Z
        ADD     R4, R0, R4
        BRp     CC_NOT_UPPER

        ADD     R3, R0, #0
        LD      R4, CC_DIFF
        ADD     R4, R0, R4
        BRnzp   CC_PREP_DONE

CC_NOT_UPPER
        LD      R4, CC_NEG_LA
        ADD     R4, R0, R4
        BRn     CC_NOT_ALPHA

        LD      R4, CC_NEG_LZ
        ADD     R4, R0, R4
        BRp     CC_NOT_ALPHA

        ADD     R4, R0, #0
        LD      R3, CC_NEG_DIFF
        ADD     R3, R0, R3
        BRnzp   CC_PREP_DONE

CC_NOT_ALPHA
        ADD     R3, R0, #0
        ADD     R4, R0, #0

CC_PREP_DONE
        NOT     R3, R3
        ADD     R3, R3, #1

        NOT     R4, R4
        ADD     R4, R4, #1

CC_LOOP
        LDR     R5, R1, #0
        BRz     CC_DONE

        ADD     R0, R5, R3
        BRz     CC_MATCH

        ADD     R0, R5, R4
        BRz     CC_MATCH

        BRnzp   CC_NEXT

CC_MATCH
        ADD     R2, R2, #1

CC_NEXT
        ADD     R1, R1, #1
        BRnzp   CC_LOOP

CC_DONE
        ADD     R0, R2, #0

        LD      R1, CC_SAVE_R1
        LD      R2, CC_SAVE_R2
        LD      R3, CC_SAVE_R3
        LD      R4, CC_SAVE_R4
        LD      R5, CC_SAVE_R5
        RET

CC_SAVE_R1     .BLKW   #1
CC_SAVE_R2     .BLKW   #1
CC_SAVE_R3     .BLKW   #1
CC_SAVE_R4     .BLKW   #1
CC_SAVE_R5     .BLKW   #1

CC_NEG_A       .FILL   #-65
CC_NEG_Z       .FILL   #-90
CC_NEG_LA      .FILL   #-97
CC_NEG_LZ      .FILL   #-122
CC_DIFF        .FILL   #32
CC_NEG_DIFF    .FILL   #-32

        .END
