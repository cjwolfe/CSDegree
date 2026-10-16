.orig x3000
ld r1, six
ld r2, number
and r3, r3, #0 ;clear r3 it will contain the product
again add r3, r3, r2
add r1, r1, #-1 ;r1 tracks iterations
brp again
halt
number .blkw 1
six .fill x0006
.end
