package stu.napls.ciprogsolu.core;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import stu.napls.ciprogsolu.util.FileToolbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class P2017S {

    // The problem of this year should be considered from the whole perspective

    private final int FONT_HEIGHT = 5;
    private final int FONT_WIDTH = 4;
    // The width of digit 1
    private final int FONT_WIDTH_1 = 1;

    public void doQ1() throws IOException {
        int number = 813;
        Canvas canvas = renderNormal(number);
        canvas.show();
        canvas.writeToFile("out1.txt");
    }

    private Canvas renderNormal(int num) {

        String number = String.valueOf(num);
        int place = number.length();

        // Space between two characters
        int spaceNumber = 2;

        // Obtain the number of digit 1
        int num1 = getNumberOf1(number);

        // eg: the canvas height for number 813 should be [5]
        // width should be [(3-1)*4+1*1+(3-1)*2)]
        Canvas canvas = new Canvas(FONT_HEIGHT, ((place - num1) * FONT_WIDTH + num1 * FONT_WIDTH_1 + (place - 1) * spaceNumber));

        // Cursor records the starting position and will move from left to right
        int cursorPosition = 0;

        // Render by place
        // PS: render by line is not a good choice in this problem
        int character;
        for (int i = 0; i < place; i++) {
            character = Integer.parseInt(String.valueOf(number.charAt(i)));
            if (character != 1) {
                for (int j = 0; j < FONT_HEIGHT; j++) {
                    System.arraycopy(alphabet[character][j].toCharArray(), 0, canvas.content[j], cursorPosition, FONT_WIDTH);
                }
                // If not rendering the last character, then push cursor from left to right
                if (i + 1 != place) {
                    cursorPosition += (FONT_WIDTH + spaceNumber);
                }
            } else {
                for (int j = 0; j < FONT_HEIGHT; j++) {
                    System.arraycopy(alphabet[character][j].toCharArray(), 0, canvas.content[j], cursorPosition, FONT_WIDTH_1);
                }
                // If not rendering the last character, then push cursor from left to right
                if (i + 1 != place) {
                    cursorPosition += (FONT_WIDTH_1 + spaceNumber);
                }
            }

        }
        return canvas;
    }

    private int getNumberOf1(String number) {
        int result = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '1') {
                result++;
            }
        }
        return result;
    }

    public void doQ2() throws IOException {
        Canvas canvas = new Canvas("out1.txt");
        System.out.println("The number is: " + getNumberFromCanvas(canvas));
    }

    // A more generic solution that can be used in later questions
    private int getNumberFromCanvas(Canvas canvas) {
        StringBuilder result = new StringBuilder();

        // Slide window
        char[][] window;

        int windowValue;

        // Get character list with indices of the start and end cursor.
        ArrayList<int[]> characterList = getCharacterList(canvas);
        int start, end, fontWidth;
        for (int i = 0; i < characterList.size(); i++) {
            start = characterList.get(i)[0];
            end = characterList.get(i)[1];
            fontWidth = end - start + 1;
            window = new char[FONT_HEIGHT][fontWidth];
            for (int j = 0; j <= canvas.height - FONT_HEIGHT; j++) {
                for (int k = 0; k < FONT_HEIGHT; k++) {
                    System.arraycopy(canvas.content[j + k], start, window[k], 0, fontWidth);
                }
                windowValue = getWindowValue(window);
                if (windowValue != -1) {
                    result.append(windowValue);
                }
            }
        }

        return Integer.parseInt(result.toString());
    }

    /*
        Split canvas by vertical space line to get character list
        ArrayList<[startOfCursor, endOfCursor]>
        [start, end]
     */
    private ArrayList<int[]> getCharacterList(Canvas canvas) {
        ArrayList<int[]> characterList = new ArrayList<>();

        // Calculate all indices of vertical space line
        ArrayList<Integer> verticalSpaceLineIndices = new ArrayList<>();
        boolean isVerticalSpaceLine;
        for (int i = 0; i < canvas.width; i++) {
            isVerticalSpaceLine = true;
            for (int j = 0; j < canvas.height; j++) {
                if (canvas.content[j][i] != ' ') {
                    isVerticalSpaceLine = false;
                    break;
                }
            }
            if (isVerticalSpaceLine) {
                verticalSpaceLineIndices.add(i);
            }
        }

        // Calculate the first and last element in character list
        int[] characterAtFirst = {0, verticalSpaceLineIndices.get(0) - 1};
        int[] characterAtLast = {verticalSpaceLineIndices.get(verticalSpaceLineIndices.size() - 1) + 1, canvas.width - 1};

        // Add the first element
        characterList.add(characterAtFirst);

        // Add the middle elements
        int[] character;
        for (int i = 1; i < verticalSpaceLineIndices.size() - 1; i++) {
            character = new int[2];
            character[0] = verticalSpaceLineIndices.get(i - 1) + 1;
            character[1] = verticalSpaceLineIndices.get(i);
            if (character[0] != character[1]) {
                character[1]--;
                characterList.add(character);
            }
        }

        // Add the last element
        characterList.add(characterAtLast);

        return characterList;
    }

    private int getWindowValue(char[][] window) {
        int windowValue = -1;
        // Transform window to String
        StringBuilder windowStringBuilder = new StringBuilder();
        for (int i = 0; i < window.length; i++) {
            windowStringBuilder.append(String.valueOf(window[i]));
        }
        String windowStr = windowStringBuilder.toString();

        // Obtain window value by judging whether window String is in the map of alphabet
        if (alphabetMapWithStringAsKey.containsKey(windowStr)) {
            windowValue = alphabetMapWithStringAsKey.get(windowStr);
        }
        return windowValue;
    }

    public void doQ3() throws IOException {
        String number = "813,0,4,1,3,2";
        Canvas canvas = renderSpecific(number);
        canvas.show();
        canvas.writeToFile("out3.txt");
    }

    private Canvas renderSpecific(String num) {
        String[] meta = num.split(",");
        String number = meta[0];
        int place = number.length();

        // Obtain the number of digit 1
        int num1 = getNumberOf1(number);

        // Calculate height and width of the canvas to generate a new canvas with right size
        int canvasHeight = 0;
        int canvasWidth = (place - num1) * FONT_WIDTH + num1 * FONT_WIDTH_1;
        for (int i = 1; i < meta.length; i++) {
            if (i % 2 != 0) {
                // Odd index means vertical position
                if (Integer.parseInt(meta[i]) > canvasHeight) {
                    canvasHeight = Integer.parseInt(meta[i]);
                }
            } else {
                // Even index means space
                canvasWidth += Integer.parseInt(meta[i]);
            }
        }
        canvasHeight += FONT_HEIGHT;

        Canvas canvas = new Canvas(canvasHeight, canvasWidth);

        int verticalPosition;

        // Cursor records the starting position and will move from left to right
        int cursorPosition = 0;

        // Render by place
        int character;
        for (int i = 0; i < place; i++) {
            character = Integer.parseInt(String.valueOf(number.charAt(i)));
            verticalPosition = Integer.parseInt(meta[i * 2 + 1]);
            if (character != 1) {
                for (int j = 0; j < FONT_HEIGHT; j++) {
                    System.arraycopy(alphabet[character][j].toCharArray(), 0, canvas.content[j + verticalPosition], cursorPosition, FONT_WIDTH);
                }

                // If not rendering the last character, then push cursor from left to right
                if (i + 1 != place) {
                    cursorPosition += (FONT_WIDTH + Integer.parseInt(meta[i * 2 + 2]));
                }
            } else {
                for (int j = 0; j < FONT_HEIGHT; j++) {
                    System.arraycopy(alphabet[character][j].toCharArray(), 0, canvas.content[j + verticalPosition], cursorPosition, FONT_WIDTH_1);
                }
                // If not rendering the last character, then push cursor from left to right
                if (i + 1 != place) {
                    cursorPosition += (FONT_WIDTH_1 + Integer.parseInt(meta[i * 2 + 2]));
                }
            }

        }

        return canvas;
    }

    public void doQ4() throws IOException {
        Canvas canvas = new Canvas("out3.txt");
        System.out.println("The number is: " + getNumberFromCanvas(canvas));
    }

    public void doQ5() throws IOException {
        Canvas canvas = new Canvas(this.getClass().getClassLoader().getResource("p2017s/file/out5.txt").getPath());
        System.out.println(canvas.width);
        System.out.println(canvas.height);
        System.out.println("The number is: " + recognizeNumberFromCanvas(canvas));
    }

    private int recognizeNumberFromCanvas(Canvas canvas) {
        StringBuilder result = new StringBuilder();

        // Slide window
        char[][] window;

        int windowValue;

        // Get character list with indices of the start and end cursor.
        ArrayList<int[]> characterList = getCharacterList(canvas);

        int start, end, fontWidth;
        for (int i = 0; i < characterList.size(); i++) {
            start = characterList.get(i)[0];
            end = characterList.get(i)[1];
            fontWidth = end - start + 1;
            window = new char[FONT_HEIGHT][fontWidth];
            for (int j = 0; j <= canvas.height - FONT_HEIGHT; j++) {
                for (int k = 0; k < FONT_HEIGHT; k++) {
                    System.arraycopy(canvas.content[j + k], start, window[k], 0, fontWidth);
                }
                showWindow(window);
                windowValue = getProbableWindowValue(window);
                if (windowValue != -1) {
                    result.append(windowValue);
                }
            }
        }

        return Integer.parseInt(result.toString());
    }

    private int getProbableWindowValue(char[][] window) {
        int windowValue = -1;

        // Check whether there is a horizontal line in window. If yes, then must not be a legal character.
        StringBuilder legacyChecker;
        boolean isLegal;
        if (window[0].length == FONT_WIDTH_1 || window[0].length == (FONT_WIDTH_1 + 1)) {
            for (int i = 0; i < FONT_HEIGHT; i++) {
                isLegal = false;
                for (int j = 0; j < window[i].length; j++) {
                    if (window[i][j] != ' ') {
                        isLegal = true;
                        break;
                    }
                }
                if (!isLegal) {
                    return windowValue;
                }
            }

            // Must be digit 1 if width of window is 1 or 2
            windowValue = 1;
        } else {
            int starNumber = 0;
            int verticalLineNumber = 0;

            // Transform window to String
            StringBuilder windowStringBuilder = new StringBuilder();

            for (int i = 0; i < FONT_HEIGHT; i++) {
                legacyChecker = new StringBuilder();
                isLegal = false;
                for (int j = 0; j < FONT_WIDTH; j++) {
                    windowStringBuilder.append(window[i][j]);
                    legacyChecker.append(window[i][j]);
                    if (window[i][j] == '*') {
                        starNumber++;
                    } else if (window[i][j] == '|') {
                        verticalLineNumber++;
                    }
                }
                for (int j = 0; j < legacyChecker.length(); j++) {
                    if (legacyChecker.charAt(j) != ' ') {
                        isLegal = true;
                        break;
                    }
                }
                if (!isLegal) {
                    return windowValue;
                }
            }

            String windowStr = windowStringBuilder.toString();

            // Find the character with min cost
            int cost;
            int minCost = FONT_WIDTH * FONT_HEIGHT;
            for (int i = 0; i < alphabetComponent.size(); i++) {
                // Don't need to judge whether is digit 1
                if (i == 1) {
                    continue;
                }

                // If both numbers of stars and vertical lines are equal, then it should be the right number
                if (starNumber == alphabetComponent.get(i)[0] && verticalLineNumber == alphabetComponent.get(i)[1]) {
                    windowValue = i;
                    break;
                }
                cost = 0;
                // Number of vertical lines should be the same
                if (verticalLineNumber == alphabetComponent.get(i)[1]) {
                    String character = alphabetMapWithIntegerAsKey.get(i);

                    for (int j = 0; j < windowStr.length(); j++) {
                        if (windowStr.charAt(j) != character.charAt(j)) {
                            cost++;
                        }
                    }
                    if (cost <= minCost) {
                        minCost = cost;
                        windowValue = i;
                    }
                }
            }
        }

        return windowValue;
    }

    class Canvas {
        int height;
        int width;
        // char[row][col]
        char[][] content;

        Canvas(int height, int width) {
            this.height = height;
            this.width = width;
            this.content = new char[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.content[i][j] = ' ';
                }
            }
        }

        // Read canvas from file
        Canvas(String path) throws IOException {
            ArrayList<String> fileContent = FileToolbox.readFileAsList(path);
            this.height = fileContent.size();
            this.width = fileContent.get(0).length();
            this.content = new char[height][width];
            for (int i = 0; i < height; i++) {
                this.content[i] = fileContent.get(i).toCharArray();
            }
        }

        private void writeToFile(String path) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < content.length; i++) {
                stringBuilder.append(content[i]);
                // Better to use \r\n for new line
                stringBuilder.append("\r\n");
            }

            FileToolbox.writeFileFromString(path, stringBuilder.toString());
        }

        private void show() {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print(content[i][j]);
                }
                System.out.println();
            }
        }
    }

    public P2017S() {
        // Initialize the alphabet map here for Q2 and later questions.
        this.alphabetMapWithStringAsKey = new HashMap<>();
        this.alphabetMapWithIntegerAsKey = new HashMap<>();
        StringBuilder character;
        for (int i = 0; i < alphabet.length; i++) {
            character = new StringBuilder();
            for (int j = 0; j < alphabet[i].length; j++) {
                character.append(alphabet[i][j]);
            }
            this.alphabetMapWithStringAsKey.put(character.toString(), i);
            this.alphabetMapWithIntegerAsKey.put(i, character.toString());
        }

        // Initialize the alphabet component list here for Q5
        this.alphabetComponent = new ArrayList<>();
        for (int i = 0; i < alphabet.length; i++) {
            int starNumber = 0;
            int verticalLineNumber = 0;
            for (int j = 0; j < alphabet[i].length; j++) {
                for (int k = 0; k < alphabet[i][j].length(); k++) {
                    if (alphabet[i][j].charAt(k) == '*') {
                        starNumber++;
                    } else if (alphabet[i][j].charAt(k) == '|') {
                        verticalLineNumber++;
                    }
                }
            }
            int[] component = {starNumber, verticalLineNumber};
            this.alphabetComponent.add(component);
        }
    }

    // Map of String and Value of each pictographic in alphabet
    private Map<String, Integer> alphabetMapWithStringAsKey;
    private Map<Integer, String> alphabetMapWithIntegerAsKey;

    // List of [number of stars, number of vertical lines] for each pictographic in alphabet
    private ArrayList<int[]> alphabetComponent;

    // String[digit index][row]
    private String[][] alphabet = {
            {
                    "****",
                    "|  |",
                    "*  *",
                    "|  |",
                    "****"
            },
            {
                    "*",
                    "|",
                    "*",
                    "|",
                    "*"
            },
            {
                    "****",
                    "   |",
                    "****",
                    "|   ",
                    "****"
            },
            {
                    "****",
                    "   |",
                    "****",
                    "   |",
                    "****"
            },
            {
                    "*  *",
                    "|  |",
                    "****",
                    "   |",
                    "   *"
            },
            {
                    "****",
                    "|   ",
                    "****",
                    "   |",
                    "****"
            },
            {
                    "*   ",
                    "|   ",
                    "****",
                    "|  |",
                    "****"
            },
            {
                    "****",
                    "   |",
                    "   *",
                    "   |",
                    "   *"
            },
            {
                    "****",
                    "|  |",
                    "****",
                    "|  |",
                    "****"
            },
            {
                    "****",
                    "|  |",
                    "****",
                    "   |",
                    "   *"
            },
    };

    /*
        Visualize the window
     */
    private void showWindow(char[][] window) {
        for (int i = 0; i < window.length; i++) {
            for (int j = 0; j < window[0].length; j++) {
                System.out.print(window[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
