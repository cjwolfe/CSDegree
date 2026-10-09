public class BigInteger
{
    public static class Node
    {
        public Node next;
        public int digit;

        public Node(int digit, Node next)
        {
            this.digit = digit;
            this.next = next;
        }

        public String toString()
        {
            if (next == null) return ""+digit;
            return next.toString() + digit;
        }
    }

    private Node head;

    public BigInteger(String input)
    {
        for (int i = 0; i < input.length(); i++)
        {
            char ch = input.charAt(i);
            if (ch < '0' || ch > '9')
                throw new IllegalArgumentException("Bad input! " + input);
            head = new Node(ch - '0', head);
        }
    }

    public BigInteger(int input)
    {
        this(""+input);
    }

    public BigInteger()
    {
        this(0);
    }

    public static BigInteger add(BigInteger a, BigInteger b)
    {
        BigInteger c = new BigInteger();
        int carry = 0;

        Node ai = a.head;
        Node bi = b.head;
        Node ci = c.head;

        while (ai != null || bi != null || carry != 0)
        {
            int av = 0;
            if (ai != null) av = ai.digit;

            int bv = 0;
            if (bi != null) bv = bi.digit;

            int sum = av + bv + carry;
            carry = sum / 10;
            ci.next = new Node(sum % 10, null);

            ci = ci.next;

            if (ai != null) ai = ai.next;
            if (bi != null) bi = bi.next;
        }

        c.head = c.head.next;
        return c;
    }

    private static BigInteger mul(BigInteger a, int b)
    {
        BigInteger c = new BigInteger();
        if (b == 0) return c;
        int carry = 0;

        Node ai = a.head;
        Node ci = c.head;

        while (ai != null || carry != 0)
        {
            int av = 0;
            if (ai != null) av = ai.digit;

            int sum = av * b + carry;
            carry = sum / 10;
            ci.next = new Node(sum % 10, null);

            ci = ci.next;

            if (ai != null) ai = ai.next;
        }

        c.head = c.head.next;
        return c;
    }

    public static BigInteger mul(BigInteger a, BigInteger b)
    {
        BigInteger c = new BigInteger();

        int d = 0;
        for (Node bi = b.head; bi != null; bi = bi.next, d++)
        {
            BigInteger p = BigInteger.mul(a, bi.digit);
            for (int i = 0; i < d; i++)
                p.head = new Node(0, p.head);
            c = BigInteger.add(c, p);
        }
        return c;
    }

    public String toString()
    {
        return head.toString();
    }
}
