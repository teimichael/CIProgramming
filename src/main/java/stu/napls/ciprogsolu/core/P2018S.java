package stu.napls.ciprogsolu.core;

import stu.napls.ciprogsolu.util.FileToolbox;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class P2018S {

    public void doQ1() {
        int m = 3;
        int n = 2;
        int[][] a = new int[m][n];
        int[][] b = new int[n][m];
        int[][] c = multiplyMatrices(a, b);

        System.out.println("2*m*m*n");
    }

    private int[][] multiplyMatrices(int[][] a, int[][] b) {
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
        // Load matrices
        int[][] m1 = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat1.txt").getPath());
        int[][] m2 = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat2.txt").getPath());
        // Do multiplication
        int[][] productM = multiplyMatrices(m1, m2);
        // Print matrix on the console
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

        int[][] a = new int[m][n];
        int[][] b = new int[n][m];
        int[][] c = new int[m][m];

        // LinkedList is for the implementation of LRU and stores the cache key.
        LinkedList<String> cache = new LinkedList<>();
        // Map mocks the cache and constructs mapping between cache key and cache value.
        Map<String, Integer> cacheMap = new HashMap<>();
        // Counter for times of memory reading
        int counter = 0;

        // Temp variable to store current element key in matrix a and b respectively.
        String aKey, bKey;
        // Temp variable to store current element value in matrix a and b respectively.
        int aValue, bValue;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int d = 0;
                for (int k = 0; k < n; k++) {
                    // Define the format of cache key:
                    aKey = "a-" + i + "-" + k;
                    bKey = "b-" + k + "-" + j;
                    if (cacheMap.containsKey(aKey)) {
                        // Cache hit
                        // Change the position of hit cache (Remove from the first and add to the last)
                        cache.remove(aKey);
                        cache.offerLast(aKey);
                        // Obatin value from the cache
                        aValue = cacheMap.get(aKey);
                    } else {
                        // Cache miss
                        aValue = a[i][k];
                        // Increase the counter
                        counter++;
                        // Try to add to cache
                        if (cache.size() < s) {
                            // Cache is not full
                            cache.offerLast(aKey);
                            cacheMap.put(aKey, aValue);
                        } else {
                            // Remove the head element in the cache
                            String key = cache.poll();
                            cacheMap.remove(key);
                        }
                    }
                    // Below shares the same logic with the above
                    if (cacheMap.containsKey(bKey)) {
                        // Cache hit
                        cache.remove(bKey);
                        cache.offerLast(bKey);
                        bValue = cacheMap.get(bKey);
                    } else {
                        // Cache miss
                        bValue = b[k][j];
                        counter++;
                        // Try to add to cache
                        if (cache.size() < s) {
                            cache.offerLast(bKey);
                            cacheMap.put(bKey, bValue);
                        } else {
                            String key = cache.poll();
                            cacheMap.remove(key);
                        }
                    }

                    d += aValue * bValue;
                }
                c[i][j] = d;
            }
        }
        System.out.println("The total number of read operations is: " + counter);
        System.out.println("with m = " + m + ", n = " + n + ", s = " + s);
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

    public void doQ6() throws IOException {
        int m = 3;
        int n = 4;
        int p = 1;
        int s = 10;
        int[][] a = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat1.txt").getPath());
        int[][] b = getMatrixFromFile(this.getClass().getClassLoader().getResource("p2018s/file/mat2.txt").getPath());

        int counter = multiplyMatricesWithP(m, n, p, s, a, b, true);
        System.out.println("The total number of read operations is: " + counter);
        System.out.println("with m = " + m + ", n = " + n + ", p = " + p + ", s = " + s);
    }

    // Print the result directly and return the counter for memory reading.
    private int multiplyMatricesWithP(int m, int n, int p, int s, int[][] a, int[][] b, boolean enablePrint) {
        int[][] c = new int[m][m];

        // LinkedList is for the implementation of LRU and stores the cache key.
        LinkedList<String> cache = new LinkedList<>();
        // Map mocks the cache and constructs mapping between cache key and cache value.
        Map<String, Integer> cacheMap = new HashMap<>();
        // Counter for times of memory reading
        int counter = 0;

        // Temp variable to store current element key in matrix a and b respectively.
        String aKey, bKey;
        // Temp variable to store current element value in matrix a and b respectively.
        int aValue, bValue;
        for (int u = 0; u < m; u += p) {
            for (int v = 0; v < m; v += p) {
                for (int w = 0; w < n; w += p) {
                    for (int i = u; i < u + p; i++) {
                        for (int j = v; j < v + p; j++) {
                            int d = 0;
                            for (int k = w; k < w + p; k++) {
                                // Define the format of cache key:
                                aKey = "a-" + i + "-" + k;
                                bKey = "b-" + k + "-" + j;
                                if (cacheMap.containsKey(aKey)) {
                                    // Cache hit
                                    // Change the position of hit cache (Remove from the first and add to the last)
                                    cache.remove(aKey);
                                    cache.offerLast(aKey);
                                    // Obatin value from the cache
                                    aValue = cacheMap.get(aKey);
                                } else {
                                    // Cache miss
                                    aValue = a[i][k];
                                    // Increase the counter
                                    counter++;
                                    // Try to add to cache
                                    if (cache.size() < s) {
                                        // Cache is not full
                                        cache.offerLast(aKey);
                                        cacheMap.put(aKey, aValue);
                                    } else {
                                        // Remove the head element in the cache
                                        String key = cache.poll();
                                        cacheMap.remove(key);
                                    }
                                }
                                // Below shares the same logic with the above
                                if (cacheMap.containsKey(bKey)) {
                                    // Cache hit
                                    cache.remove(bKey);
                                    cache.offerLast(bKey);
                                    bValue = cacheMap.get(bKey);
                                } else {
                                    // Cache miss
                                    bValue = b[k][j];
                                    counter++;
                                    // Try to add to cache
                                    if (cache.size() < s) {
                                        cache.offerLast(bKey);
                                        cacheMap.put(bKey, bValue);
                                    } else {
                                        String key = cache.poll();
                                        cacheMap.remove(key);
                                    }
                                }
                                d += aValue * bValue;
                            }
                            c[i][j] += d;
                        }
                    }
                }
            }
        }
        if (enablePrint) {
            System.out.println("The result of multiplication is: ");
            printMatrix(c);
        }
        return counter;
    }

    // The simplest and brute-force method but works
    public void doQ7() {
        int m = 200;
        int n = 150;
        int s = 600;
        // Create any two matrices satisfying the size requirement for test.
        int[][] a = new int[m][n];
        int[][] b = new int[n][m];
        int p = 1;
        int minCount = multiplyMatricesWithP(m, n, p, s, a, b, false);

        // Iterate all the common dividers of a and b to calculate the minimum
        for (int i = 2; i <= m && i <= n; i++) {
            if (m % i == 0 && n % i == 0) {
                // p is the common divider
                if (multiplyMatricesWithP(m, n, i, s, a, b, false) <= minCount) {
                    p = i;
                }
            }
        }
        System.out.println("Such p is: " + p);
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
