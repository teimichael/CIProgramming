package stu.napls.ciprogsolu.core;

public class P2016W {
    public void doQ1() {
        int n = 100;
        System.out.println("The value of f(100) is: " + f(n));
    }

    private long f(int n) {
        long result = 1;
        if (n < 1) {
            return result;
        } else {
            result = (int) ((161 * f(n - 1) + 2457) % Math.pow(2, 24));
        }
        return result;
    }

    public void doQ2() {
        int counter = 0;
        for (int i = 0; i < 100; i++) {
            if (f(i) % 2 == 0) {
                counter++;
            }
        }
        System.out.println("The number of i is: " + counter);
    }

    public void doQ3() {
        int counter = 0;
        for (int i = 1; i < 100; i += 2) {
            if (f(i) % 2 == 0) {
                counter++;
            }
        }
        System.out.println("The number of i is: " + counter);

    }

    public void doQ4() {
        int n = 1000000;
        long tempValue = 1;
        for (int i = 0; i < n / 100; i++) {
            tempValue = f2(i * 100, i * 100 - 100 + 1, tempValue);
        }
        tempValue = f2(n, n / 100 * 100 + 1, tempValue);
        System.out.println("The value of f(1000000) is: " + tempValue);
    }

    private long f2(int n, int boundary, long value) {
        long result = value;
        if (n < boundary) {
            return result;
        } else {
            result = (int) ((161 * f2(n - 1, boundary, value) + 2457) % Math.pow(2, 24));
        }
        return result;
    }

    public void doQ5() {
        System.out.println("The value of g(2) is: " + g(2));
        System.out.println("The value of g(3) is: " + g(3));
        System.out.println(g2(3, 1, 1));
    }

    private long g(int n) {
        long result = 1;
        if (n < 1) {
            return result;
        } else {
            result = (long) ((1103515245 * g(n - 1) + 12345) % Math.pow(2, 26));
        }
        return result;
    }

    public void doQ6() {
        // Select any n
        int n = 2;
        System.out.println(getKFromG(n));
    }

    private int getKFromG(int n) {
        long gn = g(n);
        System.out.println(gn);
        int k = 1;
        long tempValue;
        while (true) {
            tempValue = 1;
            for (int i = 0; i < (n + k) / 100; i++) {
                tempValue = g2(i * 100, i * 100 - 100 + 1, tempValue);
            }
            tempValue = g2((n + k), (n + k) / 100 * 100 + 1, tempValue);
            if (tempValue == gn) {
                break;
            } else {
                k++;
            }
        }
        return k;
    }

    private long g2(int n, int boundary, long value) {
        long result = value;
        if (n < boundary) {
            return result;
        } else {
            result = (long) ((1103515245 * g2(n - 1, boundary, value) + 12345) % Math.pow(2, 26));
        }
        return result;
    }

    public void doQ7() {
        // Select any n
        int n = 2;
        System.out.println(getKFromH(n));
    }

    private int getKFromH(int n) {
        long hn = h2(n, 1, 1);
        int k = 1;
        long tempValue;
        while (true) {
            tempValue = 1;
            for (int i = 0; i < (n + k) / 100; i++) {
                tempValue = h2(i * 100, i * 100 - 100 + 1, tempValue);
            }
            tempValue = h2((n + k), (n + k) / 100 * 100 + 1, tempValue);
            if (tempValue == hn) {
                break;
            } else {
                k++;
            }
        }

        return k;
    }

    private long h2(int n, int boundary, long value) {
        long result = value;
        if (n < boundary) {
            return result;
        } else {
            result = (int) ((1103515245 * h2(n - 1, boundary, value) + 12345) % Math.pow(2, 26) % Math.pow(2, 10));
        }
        return result;
    }
}
