package stu.napls.ciprogsolu;

import org.junit.Test;
import stu.napls.ciprogsolu.util.FileToolbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class P2017STest {

    private String[][] alphabet = {
            {"****", "   *", "****", "****", "*  *", "****", "*   ", "****", "****", "****"},
            {"|  |", "   |", "   |", "   |", "|  |", "|   ", "|   ", "   |", "|  |", "|  |"},
            {"*  *", "   *", "****", "****", "****", "****", "****", "   *", "****", "****"},
            {"|  |", "   |", "|   ", "   |", "   |", "   |", "|  |", "   |", "|  |", "   |"},
            {"****", "   *", "****", "****", "   *", "****", "****", "   *", "****", "   *"}
    };


    @Test
    public void p2017sRender() throws IOException {
        int number = 813;
        FileToolbox.writeFileFromString("out1.txt", render(number));
    }

    private String render(int num) {
        StringBuilder result = new StringBuilder();
        // Space between two characters
        String space = "  ";
        String number = String.valueOf(num);

        // Each character has 5 rows
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < number.length(); j++) {
                result.append(alphabet[i][Integer.parseInt(String.valueOf(number.charAt(j)))]);
                // No space in the end
                if (j != number.length() - 1) {
                    result.append(space);
                }
            }
            // Better to use \r\n for new line
            result.append("\r\n");
        }
        return result.toString();
    }
}
