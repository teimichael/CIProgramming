package stu.napls.ciprogsolu.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class P2014W {

    public void doQ1() {
        int x = 10;
        System.out.println(f(x));

    }

    private int f(int x) {
        if (x <= 2) {
            return 1;
        } else {
            return f(x - 1) + f(x - 2);
        }
    }

    public void doQ2() {
        int x = 50;
        long[] matrix = new long[x + 1];
        System.out.println(System.currentTimeMillis());
        System.out.println(f(matrix, x));
        System.out.println(System.currentTimeMillis());
    }

    private long f(long[] matrix, int x) {
        if (matrix[x] != 0L) {
            return matrix[x];
        } else if (x <= 2) {
            matrix[x] = 1;
            return matrix[x];
        } else {
            matrix[x] = f(matrix, x - 1) + f(matrix, x - 2);
            return matrix[x];
        }
    }

    public void doQ3() {
        String s1 = "00123456789012345678901234567890";
        String s2 = "00987654321098765432109876543210";
        BigInteger bi1 = new BigInteger(s1);
        BigInteger bi2 = new BigInteger(s2);
        System.out.println(bi1.add(bi2));
    }

    public void doQ4() {
        int x = 50;
        BigInteger[] matrix = new BigInteger[x + 1];
        System.out.println(System.currentTimeMillis());
        System.out.println(f(matrix, x));
        System.out.println(System.currentTimeMillis());
    }

    private BigInteger f(BigInteger[] matrix, int x) {
        if (matrix[x] != null) {
            return matrix[x];
        } else if (x <= 2) {
            matrix[x] = new BigInteger("1");
            return matrix[x];
        } else {
            matrix[x] = f(matrix, x - 1).add(f(matrix, x - 2));
            return matrix[x];
        }
    }

    public void doQ5() {
        String s1 = "12345678901234567890123456789012 04";
        String s2 = "98765432109876543210987654321098 09";

        StringBuilder s2Builder = new StringBuilder();
        BigDecimal bd1 = string2BigDecimal(s1);
        BigDecimal bd2 = string2BigDecimal(s2);
        System.out.println(bd1.multiply(bd2));
    }

    private BigDecimal string2BigDecimal(String s) {
        String[] sArray = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        int dotIndex = Integer.parseInt(sArray[1]) + 1;
        stringBuilder.append(sArray[0].substring(0, dotIndex));
        stringBuilder.append(".");
        stringBuilder.append(sArray[0].substring(dotIndex));
        return new BigDecimal(stringBuilder.toString());
    }

    public void doQ6() {
        System.out.println(getPhi());
    }

    private BigDecimal getPhi() {
        // Set precision
        MathContext mathContext = new MathContext(32);
        BigDecimal root5 = new BigDecimal("5").sqrt(mathContext);
        BigDecimal phi = root5.add(new BigDecimal("1"));
        return phi.divide(new BigDecimal("2"), mathContext);
    }

    public void doQ7() {
        int x = 140;
        System.out.println(g(x));

    }

    private BigDecimal g(int x) {
        MathContext mathContext = new MathContext(32);
        BigDecimal root5 = new BigDecimal("5").sqrt(mathContext);
        BigDecimal phi = getPhi();
        return phi.pow(x).divide(root5, mathContext);
    }

    public void doQ8() {
        BigInteger[] matrix = new BigInteger[141];
        BigDecimal max = new BigDecimal(f(matrix, 1)).subtract(g(1)).abs();
        for (int x = 2; x <= 140; x++) {
            max = new BigDecimal(f(matrix, x)).subtract(g(x)).abs().max(max);
        }
        System.out.println("The maximum value is: " + max);
    }

}
