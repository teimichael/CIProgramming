package stu.napls.ciprogsolu;

import org.junit.Test;
import stu.napls.ciprogsolu.util.PrintToolbox;

import java.util.Arrays;

public class SimpleTest {

    // Arrays
    @Test
    public void testArrays() {
        // Clone array
        int[] a = {3, 2, 1, 4, 5, 6};
        int[] b = a.clone();
        b[0] = 100;
        System.out.println(a[0]);
        System.out.println(b[0]);

        // Sort array
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

        int[][] array = {
                {1, 2, 3},
                {4, 5, 6}
        };

        PrintToolbox.print(array);
    }


    @Test
    public void testInt2Byte() {
        int a = 100;
        System.out.println((byte) a);
    }

    @Test
    public void testSth() {
        char a = 'a';
        char b = 'b';
        System.out.println(String.valueOf(a) + (b));

    }
}
