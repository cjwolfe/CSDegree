.orig x3000
ld r1, six
ld r2, number
and r3, r3, #0
again add r3,r3,r2
add r1,r1, #-1
brp again
halt
number .fill x0003
six .fill x0006
.end
