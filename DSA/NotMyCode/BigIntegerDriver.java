public class BigIntegerDriver
{
    public static void main(String[] args)
    {
        System.out.println(factorial(40));
    }

    public static BigInteger factorial(int n)
    {
        if (n <= 1) return new BigInteger(1);
        return BigInteger.mul(new BigInteger(n), factorial(n-1));
    }
}
