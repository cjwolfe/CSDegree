## Calculating Two's complement

1. Starting with the absolute binary representation of a number
2. Invert/flip all bits, changing every 0 to 1, 1 to 0
3. Add 1 to the entire inverted number, ignoring overflow. Accounting for overflow will produce the wrong number as a result.

Ex. Calculate decimal -6 in binary from the number 6
1. +6 in decimal is 0110 (0,4,2,1), the leftmost significant bit (0) is the sign. (just 110 in binary would be -2 in decimal).
2. FLip all bits in 0110 to 1001
3. Add the place value 1 to the flipped number 1001, giving 1010

To verify that 1010 indeed has a value of −6, add the place values together, but subtract the sign value from the final calculation. Because the most significant value is the sign value, it must be subtracted to produce the correct result: 1010 = −(1×23) + (0×22) + (1×21) + (0×20) = 1×−8 + 0 + 1×2 + 0 = −6.

Steps 2 and 3 together are a valid method to compute the additive inverse (-n) of any positive or negative integer n, where both input and output are in two's complement format. Analternative to compute -n is to use subtraction (0 - n).

A two's-complement number system encodes positive and negative numbers in a binary number representation. The weight of each bit is a power of two, except for the most significant bit, whose weight is the negative of the corresponding power of two.

