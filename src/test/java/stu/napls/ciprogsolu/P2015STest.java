package stu.napls.ciprogsolu;

import org.junit.Test;

import java.util.Arrays;

public class P2015STest {

    @Test
    public void testLevenshteinAlgorithm() {
        String a = "abc";
        String b = "abe";
        int[][] matrix = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], -1);
        }

        System.out.println(lev(matrix, a.length(), b.length(), a, b));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Levenshtein distance algorithm
    private int lev(int[][] matrix, int i, int j, String a, String b) {
        if (matrix[i][j] != -1) {
            // The matrix has the value
            return matrix[i][j];
        } else if (Math.min(i, j) == 0) {
            // Save to the matrix
            int max = Math.max(i, j);
            matrix[i][j] = max;
            return max;
        } else {
            int upper = lev(matrix, i - 1, j, a, b) + 1;
            int left = lev(matrix, i, j - 1, a, b) + 1;
            int diag = lev(matrix, i - 1, j - 1, a, b) + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1);
            // Save to the matrix
            matrix[i][j] = Math.min(Math.min(upper, left), diag);
            return matrix[i][j];
        }
    }
}
