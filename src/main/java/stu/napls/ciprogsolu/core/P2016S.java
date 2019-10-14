package stu.napls.ciprogsolu.core;

import java.util.HashMap;
import java.util.Map;

public class P2016S {
    public void doQ1() {
        // Input
        String quaternaryNumber = "123";

        // base-4
        int base = 4;

        // Output
        int decimalNumber = toDecimal(quaternaryNumber, base);

        System.out.println("The decimal representation of quaternary number " + quaternaryNumber + " is " + decimalNumber);
    }

    /**
     * @param number number to be transformed to decimal
     * @param base   base of number
     * @return decimal representation
     */
    private int toDecimal(String number, int base) {
        int result = 0;
        for (int i = 0; i < number.length(); i++) {
            result += (int) (Integer.parseInt(String.valueOf(number.charAt(i))) * Math.pow(base, number.length() - 1 - i));
        }
        return result;
    }


    public void doQ2() {
        // Input
        String input = "bcd";

        // Transform input to octal number
        StringBuilder stringBuilder = new StringBuilder();
        String alphabet = "abcdefgh";
        for (int i = 0; i < input.length(); i++) {
            stringBuilder.append(alphabet.indexOf(input.charAt(i)));
        }
        String octalNumber = stringBuilder.toString();

        // base-8
        int base = 8;

        int decimalNumber = toDecimal(octalNumber, base);

        System.out.println("The decimal representation of " + input + " (" + octalNumber + ") " + "is " + decimalNumber);

    }

    public void doQ3() {
        String input = "2015";
        System.out.println("Decimal number " + input + " in Roman numerals is " + toRoman(input));
    }

    private String[] romanLetter = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private int[] romanValue = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    /**
     * @param letter
     * @return the index of this letter in romanLetter array
     */
    private int getIndexOfRomanLetter(String letter) {
        int result = 0;
        for (int i = 0; i < romanLetter.length; i++) {
            if (letter.equals(romanLetter[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * @param input A String number
     * @return roman numerals
     */
    private String toRoman(String input) {
        int number = Integer.parseInt(input);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < romanValue.length; i++) {
            while (number >= romanValue[i]) {
                stringBuilder.append(romanLetter[i]);
                number -= romanValue[i];
            }
        }

        return stringBuilder.toString();
    }

    public void doQ4() {
        // Input: a number in Roman numerals (0, 4000)
        String input = "MMXV";

        // Two methods for Q4 here
        int result = q4Method1(input);
//        int result = q4Method2(input);

        System.out.println("Decimal representation of " + input + " is " + result);
    }

    private int q4Method1(String input) {
        int result = 0;
        int currentLetterIndex;

        for (int i = 0; i < input.length(); i++) {
            currentLetterIndex = getIndexOfRomanLetter(String.valueOf(input.charAt(i)));
            // The former letter has lower value than the latter one (e.g: IV)
            if ((i + 1) < input.length() &&
                    currentLetterIndex > getIndexOfRomanLetter(String.valueOf(input.charAt(i + 1)))) {
                // Combine them together, get corresponding value according to the index of the roman letter
                result += romanValue[getIndexOfRomanLetter(String.valueOf(input.charAt(i)) + input.charAt(i + 1))];
                i++;
            } else {
                result += romanValue[currentLetterIndex];
            }
        }
        return result;
    }

    private int q4Method2(String input) {
        int result = 0;
        int currentLetterIndex;
        int oldLetterIndex = getIndexOfRomanLetter(String.valueOf(input.charAt(0)));
        for (int i = 1; i < input.length(); i++) {
            currentLetterIndex = getIndexOfRomanLetter(String.valueOf(input.charAt(i)));
            if (currentLetterIndex >= oldLetterIndex) {
                result += romanValue[oldLetterIndex];
            } else {
                // If the former letter has lower value than the latter one, then minus the value of the former.
                result -= romanValue[oldLetterIndex];
            }
            oldLetterIndex = currentLetterIndex;
        }
        result += romanValue[oldLetterIndex];
        return result;
    }

    public void doQ5() {
        // Input: a number (0, 4000)
        String input = "19";
        System.out.println("Decimal number " + input + " in Roman numerals is " + toRoman(input));
    }

    private String[] romanLetterX = {"M", "IM", "XM", "CM", "D", "ID", "XD", "CD", "C", "IC", "XC", "L", "IL", "XL", "X", "IX", "V", "IV", "I"};
    private int[] romanValueX = {1000, 999, 990, 900, 500, 499, 490, 400, 100, 99, 90, 50, 49, 40, 10, 9, 5, 4, 1};

    /**
     * todo: This question is ambiguous: whether letters {D, L, V} can be the suffix? e.g. 'DM'
     * The solution below considers legal set of suffixes is { C, X, I }
     */
    public void doQ6() {
        // Input: a number (0, 4000)
        String input = "149";
        System.out.println("Decimal number " + input + " in Roman numerals extension is " + toRomanX(input));
    }

    /**
     * @param input A String number
     * @return roman numerals
     */
    private String toRomanX(String input) {
        int number = Integer.parseInt(input);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < romanValueX.length; i++) {
            while (number >= romanValueX[i]) {
                stringBuilder.append(romanLetterX[i]);
                number -= romanValueX[i];
            }
        }
        return stringBuilder.toString();
    }

    private Map<String, Integer> alphabet;

    public void doQ7() {
        // Construct the mapping between words and values
        initAlphabet();

        // Input
        String input = "fifty four thousand three hundred twelve";
        String[] inputArray = input.split(" ");

        int result[] = new int[2];
        int current;
        for (int i = 0; i < inputArray.length; i++) {
            current = alphabet.get(inputArray[i]);
            if (current == 1000) {
                // Store high order result in result[1]
                result[1] = result[0] * 1000;
                result[0] = 0;
            } else if (current == 100) {
                result[0] *= 100;
            } else {
                result[0] += current;
            }
        }
        System.out.println(result[0] + result[1]);
    }

    private void initAlphabet() {
        alphabet = new HashMap<>();
        alphabet.put("one", 1);
        alphabet.put("two", 2);
        alphabet.put("three", 3);
        alphabet.put("four", 4);
        alphabet.put("five", 5);
        alphabet.put("six", 6);
        alphabet.put("seven", 7);
        alphabet.put("eight", 8);
        alphabet.put("nine", 9);
        alphabet.put("ten", 10);
        alphabet.put("eleven", 11);
        alphabet.put("twelve", 12);
        alphabet.put("thirteen", 13);
        alphabet.put("fourteen", 14);
        alphabet.put("fifteen", 15);
        alphabet.put("sixteen", 16);
        alphabet.put("seventeen", 17);
        alphabet.put("eighteen", 18);
        alphabet.put("nineteen", 19);
        alphabet.put("twenty", 20);
        alphabet.put("thirty", 30);
        alphabet.put("forty", 40);
        alphabet.put("fifty", 50);
        alphabet.put("sixty", 60);
        alphabet.put("seventy", 70);
        alphabet.put("eighty", 80);
        alphabet.put("ninety", 90);
        alphabet.put("hundred", 100);
        alphabet.put("thousand", 1000);
    }
}
