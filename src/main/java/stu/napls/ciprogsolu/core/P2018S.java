package stu.napls.ciprogsolu.core;

import javafx.util.Pair;
import stu.napls.ciprogsolu.util.FileToolbox;

import java.io.IOException;
import java.util.*;

public class P2018S {

    public void doQ1() {
        int m = 3;
        int n = 2;
        int[][] a = new int[m][n];
        int[][] b = new int[n][m];
        int[][] c = matrixMultiply(a, b);

        System.out.println("2*m*m*n");
    }

    private int[][] matrixMultiply(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        int[][] c = new int[m][m];
        // m times
        for (int i = 0; i < m; i++) {
            // m times
            for (int j = 0; j < m; j++) {
                int d = 0;
                // n times
                for (int k = 0; k < n; k++) {
                    d += a[i][k] * b[k][j];
                }
                c[i][j] = d;
            }
        }
        return c;
    }

    public void doQ2() throws IOException {
        // Read file as String
        int[][] matrix = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat1.txt").getPath());

        System.out.println("The number of rows is: " + matrix.length);
        System.out.println("The number of columns is: " + matrix[0].length);
    }

    private int[][] getMatrixFromFile(String path) throws IOException {
        // Read file as String
        String content = FileToolbox.readFileAsString(path);

        // Delete '.' at last
        content = content.substring(0, content.length() - 1);

        String[] rows = content.split(",");
        String[] column = rows[0].split(" ");
        int[][] matrix = new int[rows.length][column.length];
        for (int i = 0; i < rows.length; i++) {
            column = rows[i].split(" ");
            for (int j = 0; j < column.length; j++) {
                matrix[i][j] = Integer.parseInt(column[j]);
            }
        }
        return matrix;
    }

    public void doQ3() throws IOException {
        int[][] m1 = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat1.txt").getPath());
        int[][] m2 = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat2.txt").getPath());
        int[][] productM = matrixMultiply(m1, m2);
        printMatrix(productM);

        int trace = 0;
        for (int i = 0; i < productM.length; i++) {
            trace += productM[i][i];
        }
        System.out.println("The trace of the product is: " + trace);
    }

    public void doQ4() {
        int m = 2;
        int n = 2;
        int s = 4;

        LinkedList<String> cache = new LinkedList<>();
        Map<String, Integer> cacheValue = new HashMap<>();
        int counter = 0;

        int[][] a = new int[m][n];
        int[][] b = new int[n][m];

        int[][] c = new int[m][m];

        int p1Value, p2Value;
        String p1Key, p2Key;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int d = 0;
                for (int k = 0; k < n; k++) {
                    p1Key = "a-" + i + "-" + k;
                    p2Key = "b-" + k + "-" + j;
                    if (cacheValue.containsKey(p1Key)) {
                        cache.remove(p1Key);
                        cache.offerLast(p1Key);
                        p1Value = cacheValue.get(p1Key);
                    } else {
                        p1Value = a[i][k];
                        counter++;
                        // Try to add to cache
                        if (cache.size() < s) {
                            cache.offerLast(p1Key);
                            cacheValue.put(p1Key, p1Value);
                        } else {
                            String key = cache.poll();
                            cacheValue.remove(key);
                        }
                    }
                    if (cacheValue.containsKey(p2Key)) {
                        cache.remove(p2Key);
                        cache.offerLast(p2Key);
                        p2Value = cacheValue.get(p2Key);
                    } else {
                        p2Value = b[k][j];
                        counter++;
                        // Try to add to cache
                        if (cache.size() < s) {
                            cache.offerLast(p2Key);
                            cacheValue.put(p2Key, p2Value);
                        } else {
                            String key = cache.poll();
                            cacheValue.remove(key);
                        }
                    }
                    d += p1Value * p2Value;
                }
                c[i][j] = d;
            }
        }
        System.out.println(counter);
    }

    public void doQ5() throws IOException {
        int m = 3;
        int n = 4;
        int p = 1;
        int[][] a = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat1.txt").getPath());
        int[][] b = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat2.txt").getPath());
        int[][] c = new int[m][m];

        for (int u = 0; u < m; u += p) {
            for (int v = 0; v < m; v += p) {
                for (int w = 0; w < n; w += p) {
                    for (int i = u; i < u + p; i++) {
                        for (int j = v; j < v + p; j++) {
                            int d = 0;
                            for (int k = w; k < w + p; k++) {
                                d += a[i][k] * b[k][j];
                            }
                            c[i][j] += d;
                        }
                    }
                }
            }
        }

        printMatrix(c);
    }

    public void doQ6() {

    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
