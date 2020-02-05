package stu.napls.ciprogsolu.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintToolbox {

    public static void print(int x) {
        System.out.println(x);
    }

    public static void print(float x) {
        System.out.println(x);
    }

    public static void print(double x) {
        System.out.println(x);
    }

    public static void print(long x) {
        System.out.println(x);
    }

    public static void print(String x) {
        System.out.println(x);
    }

    public static void print(int[] x) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            list.add(x[i]);
        }
        System.out.print(list);
    }

    public static void print(float[] x) {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            list.add(x[i]);
        }
        System.out.print(list);
    }

    public static void print(double[] x) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            list.add(x[i]);
        }
        System.out.print(list);
    }

    public static void print(long[] x) {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            list.add(x[i]);
        }
        System.out.print(list);
    }

    public static void print(String[] x) {
        List<String> list = new ArrayList<>(Arrays.asList(x));
        System.out.print(list);
    }

    public static void print(int[][] x) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> subList;
        for (int i = 0; i < x.length; i++) {
            subList = new ArrayList<>();
            for (int j = 0; j < x[0].length; j++) {
                subList.add(x[i][j]);
            }
            list.add(subList);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void print(float[][] x) {
        List<List<Float>> list = new ArrayList<>();
        List<Float> subList;
        for (int i = 0; i < x.length; i++) {
            subList = new ArrayList<>();
            for (int j = 0; j < x[0].length; j++) {
                subList.add(x[i][j]);
            }
            list.add(subList);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void print(double[][] x) {
        List<List<Double>> list = new ArrayList<>();
        List<Double> subList;
        for (int i = 0; i < x.length; i++) {
            subList = new ArrayList<>();
            for (int j = 0; j < x[0].length; j++) {
                subList.add(x[i][j]);
            }
            list.add(subList);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void print(long[][] x) {
        List<List<Long>> list = new ArrayList<>();
        List<Long> subList;
        for (int i = 0; i < x.length; i++) {
            subList = new ArrayList<>();
            for (int j = 0; j < x[0].length; j++) {
                subList.add(x[i][j]);
            }
            list.add(subList);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void print(String[][] x) {
        List<List<String>> list = new ArrayList<>();
        List<String> subList;
        for (int i = 0; i < x.length; i++) {
            subList = new ArrayList<>();
            for (int j = 0; j < x[0].length; j++) {
                subList.add(x[i][j]);
            }
            list.add(subList);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


}
