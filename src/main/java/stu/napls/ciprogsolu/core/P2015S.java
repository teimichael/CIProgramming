package stu.napls.ciprogsolu.core;

import javafx.util.Pair;
import stu.napls.ciprogsolu.util.FileToolbox;

import java.io.IOException;
import java.util.*;

public class P2015S {

    private List<String> contentList;

    public P2015S() throws IOException {
        // Read file as an ArrayList
        this.contentList = FileToolbox.readFileAsList(this.getClass().getClassLoader().getResource("p2015s/file/program.txt").getPath());
    }

    public void doQ1() {
        // Read file as an ArrayList
        int counter = 0;
        for (int i = 0; i < this.contentList.size(); i++) {
            if (this.contentList.get(i).contains(";")) {
                counter++;
            }
        }
        System.out.println("The number of semi-colons is: " + counter);
    }

    // All the lines containing in the *main* function.
    public void doQ2() {
        int mainStart = -1;
        int mainEnd = -1;
        int bracketCounter = 0;
        String line;
        for (int i = 0; i < this.contentList.size(); i++) {
            line = this.contentList.get(i);
            if (line.contains("main")) {
                mainStart = i;
            }

            // Counter the brackets
            if (line.contains("{")) {
                bracketCounter++;
            } else if (line.contains("}")) {
                bracketCounter--;
            }

            // If the brackets match and still in the main function
            if (bracketCounter == 0 && mainStart != -1) {
                mainEnd = i;
                break;
            }
        }


        System.out.println("The main function starts from line " + (mainStart + 1) + " and ends in line " + (mainEnd + 1));
        for (int i = mainStart; i <= mainEnd; i++) {
            System.out.println("Line " + (i + 1) + ": ");
            System.out.println(this.contentList.get(i));
            System.out.println();
        }
    }

    public void doQ3() {
        // Store the lines to be printed
        int[] printLine = new int[this.contentList.size()];
        String lineTemp = this.contentList.get(0);
        for (int i = 1; i < this.contentList.size(); i++) {
            if (lineTemp.equals(this.contentList.get(i)) && printLine[i - 1] == 0) {
                printLine[i] = 1;
            }
            lineTemp = this.contentList.get(i);
        }
        for (int i = 0; i < printLine.length; i++) {
            if (printLine[i] == 1) {
                System.out.println(this.contentList.get(i));
            }
        }
    }

    public void doQ4() {
        // Store the lines to be printed
        int[] printLine = new int[this.contentList.size()];
        Set<String> duplicates = new HashSet<>();
        for (int i = 0; i < this.contentList.size(); i++) {
            if (duplicates.contains(this.contentList.get(i))) {
                printLine[i] = 1;
            }
            duplicates.add(this.contentList.get(i));
        }
        for (int i = 0; i < printLine.length; i++) {
            if (printLine[i] == 1) {
                System.out.println(i + 1);
            }
        }
    }

    public void doQ5() {
        // List<Pair<LinePair, LinePair>> := A list of line pairs.
        // Pair<Integer, String> := Pair<LineNumber, LineCode>
        List<Pair<Pair<Integer, String>, Pair<Integer, String>>> similarLinePairList = new ArrayList<>();

        String currentLine, nextLine;
        for (int i = 0; i < this.contentList.size(); i++) {
            currentLine = this.contentList.get(i);
            if (currentLine.length() >= 20) {
                for (int j = i + 1; j < this.contentList.size(); j++) {
                    nextLine = this.contentList.get(j);
                    if (!currentLine.equals(nextLine) && isSimilarForQ5(currentLine, nextLine)) {
                        similarLinePairList.add(new Pair<>(new Pair<>(i + 1, currentLine), new Pair<>(j + 1, nextLine)));
                    }
                }
            }
        }

        // Print the result
        printSimilarLinePairList(similarLinePairList);
    }

    private boolean isSimilarForQ5(String a, String b) {
        boolean result = false;
        // Fill the blank to balance the length
        if (a.length() >= b.length()) {
            StringBuilder bBuilder = new StringBuilder(b);
            for (int i = 0; i < a.length() - b.length(); i++) {
                bBuilder.append(" ");
            }
            b = bBuilder.toString();
        } else {
            StringBuilder aBuilder = new StringBuilder(a);
            for (int i = 0; i < b.length() - a.length(); i++) {
                aBuilder.append(" ");
            }
            a = aBuilder.toString();
        }

        // The number of different characters
        int diffCounter = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diffCounter++;
            }
        }

        // If less than 5, change the result to true
        if (diffCounter < 5) {
            result = true;
        }
        return result;
    }

    private void printSimilarLinePairList(List<Pair<Pair<Integer, String>, Pair<Integer, String>>> similarLinePairList) {
        Pair<Pair<Integer, String>, Pair<Integer, String>> linePair;
        Pair<Integer, String> lineA, lineB;
        for (int i = 0; i < similarLinePairList.size(); i++) {
            linePair = similarLinePairList.get(i);
            System.out.println("Pair " + i + ": ");
            lineA = linePair.getKey();
            System.out.println("Line " + lineA.getKey() + ": ");
            System.out.println(lineA.getValue());
            lineB = linePair.getValue();
            System.out.println("Line " + lineB.getKey() + ": ");
            System.out.println(lineB.getValue());
            System.out.println();
        }
        System.out.println("The number of pairs is: " + similarLinePairList.size());
    }

    public void doQ6() {
        // List<Pair<LinePair, LinePair>> := A list of line pairs.
        // Pair<Integer, String> := Pair<LineNumber, LineCode>
        List<Pair<Pair<Integer, String>, Pair<Integer, String>>> similarLinePairList = new ArrayList<>();

        String currentLine, nextLine;
        for (int i = 0; i < this.contentList.size(); i++) {
            currentLine = this.contentList.get(i);
            if (currentLine.length() >= 20) {
                for (int j = i + 1; j < this.contentList.size(); j++) {
                    nextLine = this.contentList.get(j);
                    if (!currentLine.equals(nextLine) && isSimilarForQ6(currentLine, nextLine)) {
                        similarLinePairList.add(new Pair<>(new Pair<>(i + 1, currentLine), new Pair<>(j + 1, nextLine)));
                    }
                }
            }
        }

        // Print the result
        printSimilarLinePairList(similarLinePairList);
    }

    private boolean isSimilarForQ6(String a, String b) {
        boolean result = false;

        int[][] matrix = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], -1);
        }

        if (lev(matrix, a.length(), b.length(), a, b) < 4) {
            result = true;
        }

        return result;
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

    public void doQ7() {
        // Pair<StartLineNumber, EndLineNumber>
        List<Pair<Integer, Integer>> similarBlockList = new ArrayList<>();

        String codeBlock;
        String blockWindow;
        // Window size
        for (int j = 4; j < this.contentList.size() / 2 + 1; j++) {
            for (int i = 0; i < this.contentList.size() - j; i++) {
                // Generate the block
                codeBlock = buildCodeBlock(i, j);
                // Move the window for matching
                for (int k = i + j; k < this.contentList.size() - j; k++) {
                    // Generate the block window
                    blockWindow = buildCodeBlock(k, j);
                    if (codeBlock.equals(blockWindow)) {
                        System.out.println(codeBlock);
                        System.out.println(blockWindow);
                        similarBlockList.add(new Pair<>(k + 1, k + j));
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < similarBlockList.size(); i++) {
            System.out.println(similarBlockList.get(i).getKey() + " " + similarBlockList.get(i).getValue());
        }
    }

    private String buildCodeBlock(int start, int size) {
        StringBuilder codeBlockBuilder = new StringBuilder();
        for (int i = start; i < i + size; i++) {
            codeBlockBuilder.append(this.contentList.get(i));
        }
        return codeBlockBuilder.toString();
    }


}
